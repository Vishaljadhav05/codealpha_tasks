package com.vj.service;

import com.vj.dto.ClassStats;

import com.vj.dto.StudentSummary;
import com.vj.model.Grade;
import com.vj.model.Student;
import com.vj.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class StudentService {

	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	// ---------- CRUD ----------

	public Student get(Long id) {
		return repository.findWithGrades(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
	}

	public boolean isRollNumberTaken(String rollNumber, Long excludeId) {
		return repository.findByRollNumber(rollNumber.trim()).filter(s -> !Objects.equals(s.getId(), excludeId))
				.isPresent();
	}

	@Transactional
	public com.vj.model.Student create(Student student) {
		student.setRollNumber(student.getRollNumber().trim());
		student.setName(student.getName().trim());
		return repository.save(student);
	}

	@Transactional
	public void update(Long id, Student form) {
		Student existing = get(id);
		existing.setRollNumber(form.getRollNumber().trim());
		existing.setName(form.getName().trim());
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(get(id));
	}

	@Transactional
	public void addGrade(Long studentId, Grade grade) {
		grade.setSubject(grade.getSubject().trim());
		get(studentId).addGrade(grade);
	}

	@Transactional
	public void deleteGrade(Long studentId, Long gradeId) {
		get(studentId).getGrades().removeIf(g -> Objects.equals(g.getId(), gradeId));
	}

	// ---------- Reporting ----------

	public List<StudentSummary> summaries(String query) {
		List<StudentSummary> result = new ArrayList<>();
		for (Student s : repository.search(query == null ? "" : query.trim())) {
			result.add(summarize(s));
		}
		return result;
	}

	/**
	 * Computes total, average, highest and lowest using an ArrayList of scores and
	 * a primitive array.
	 */
	public StudentSummary summarize(Student s) {
		List<Double> scores = new ArrayList<>();
		for (Grade g : s.getGrades()) {
			scores.add(g.getScore());
		}
		if (scores.isEmpty()) {
			return new StudentSummary(s.getId(), s.getRollNumber(), s.getName(), 0, 0, 0, 0, 0, "-");
		}

		double[] values = new double[scores.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = scores.get(i);
		}

		double total = 0;
		double highest = values[0];
		double lowest = values[0];
		for (double v : values) {
			total += v;
			highest = Math.max(highest, v);
			lowest = Math.min(lowest, v);
		}
		double average = total / values.length;
		return new StudentSummary(s.getId(), s.getRollNumber(), s.getName(), values.length, total, average, highest,
				lowest, letterFor(average));
	}

	public ClassStats classStats(List<StudentSummary> summaries) {
		int grades = 0;
		double total = 0;
		double highest = 0;
		double lowest = 100;
		StudentSummary top = null;
		StudentSummary bottom = null;

		for (StudentSummary s : summaries) {
			if (!s.isGraded()) {
				continue;
			}
			grades += s.getSubjectCount();
			total += s.getTotal();
			highest = Math.max(highest, s.getHighest());
			lowest = Math.min(lowest, s.getLowest());
			if (top == null || s.getAverage() > top.getAverage())
				top = s;
			if (bottom == null || s.getAverage() < bottom.getAverage())
				bottom = s;
		}

		if (grades == 0) {
			return new ClassStats(summaries.size(), 0, 0, 0, 0, "-", "-");
		}
		return new ClassStats(summaries.size(), grades, total / grades, highest, lowest, top.getName(),
				bottom.getName());
	}

	static String letterFor(double average) {
		if (average >= 90)
			return "A";
		if (average >= 80)
			return "B";
		if (average >= 70)
			return "C";
		if (average >= 60)
			return "D";
		return "F";
	}
}
