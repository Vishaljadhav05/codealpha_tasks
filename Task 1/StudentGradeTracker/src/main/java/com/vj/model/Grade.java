package com.vj.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Subject is required")
	@Size(max = 60, message = "Subject must be 60 characters or fewer")
	@Column(nullable = false, length = 60)
	private String subject;

	@NotNull(message = "Score is required")
	@DecimalMin(value = "0.0", message = "Score cannot be below 0")
	@DecimalMax(value = "100.0", message = "Score cannot be above 100")
	@Column(nullable = false)
	private Double score;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", subject=" + subject + ", score=" + score + ", student=" + student + "]";
	}
	
	
}
