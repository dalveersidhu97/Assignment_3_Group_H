package com.assignment3.groupH;

/**
 * 
 * @author Group H
 * @data 29/06/2021
 * @description Model for course data
 */


public class Course {

	private int id;
	private String name;
	private int numberOfStudents;
	private float averageScore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	
}
