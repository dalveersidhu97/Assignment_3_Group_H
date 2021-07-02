package com.assignment3.groupH;

/**
 * 
 * @author Group H
 * @data 29/06/2021
 * @description Model for student data
 */

public class Student {
	
	private int id, age, courseId;
	private String name, gender, courseName;
	private float courseScore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public float getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(float courseScore) {
		this.courseScore = courseScore;
	}
}
