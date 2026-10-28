package com.vj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Roll number is required")
	@Size(max = 20, message = "Roll number must be 20 characters or fewer")
	@Column(nullable = false, unique = true, length = 20)
	private String rollNumber;

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name must be 100 characters or fewer")
	@Column(nullable = false, length = 100)
	private String name;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id ASC")
	private List<Grade> grades = new ArrayList<>();

	public void addGrade(Grade grade) {
		grade.setStudent(this);
		grades.add(grade);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", rollNumber=" + rollNumber + ", name=" + name + ", grades=" + grades + "]";
	}

}
