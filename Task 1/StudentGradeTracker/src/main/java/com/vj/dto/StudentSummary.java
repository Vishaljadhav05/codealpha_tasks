package com.vj.dto;

/** Per-student statistics shown in the summary report. */
public class StudentSummary {

	private final Long id;
	private final String rollNumber;
	private final String name;
	private final int subjectCount;
	private final double total;
	private final double average;
	private final double highest;
	private final double lowest;
	private final String letter;

	public StudentSummary(Long id, String rollNumber, String name, int subjectCount, double total, double average,
			double highest, double lowest, String letter) {
		this.id = id;
		this.rollNumber = rollNumber;
		this.name = name;
		this.subjectCount = subjectCount;
		this.total = total;
		this.average = average;
		this.highest = highest;
		this.lowest = lowest;
		this.letter = letter;
	}

	public Long getId() {
		return id;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public String getName() {
		return name;
	}

	public int getSubjectCount() {
		return subjectCount;
	}

	public double getTotal() {
		return total;
	}

	public double getAverage() {
		return average;
	}

	public double getHighest() {
		return highest;
	}

	public double getLowest() {
		return lowest;
	}

	public String getLetter() {
		return letter;
	}

	public boolean isGraded() {
		return subjectCount > 0;
	}
}
