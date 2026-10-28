package com.vj.dto;

/** Class-wide statistics shown at the top of the summary report. */
public class ClassStats {

	private final int studentCount;
	private final int gradeCount;
	private final double average;
	private final double highest;
	private final double lowest;
	private final String topStudent;
	private final String lowestStudent;

	public ClassStats(int studentCount, int gradeCount, double average, double highest, double lowest,
			String topStudent, String lowestStudent) {
		this.studentCount = studentCount;
		this.gradeCount = gradeCount;
		this.average = average;
		this.highest = highest;
		this.lowest = lowest;
		this.topStudent = topStudent;
		this.lowestStudent = lowestStudent;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public int getGradeCount() {
		return gradeCount;
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

	public String getTopStudent() {
		return topStudent;
	}

	public String getLowestStudent() {
		return lowestStudent;
	}

	public boolean isGraded() {
		return gradeCount > 0;
	}
}
