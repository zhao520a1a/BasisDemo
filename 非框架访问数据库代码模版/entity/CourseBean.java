package com.hust.studentmis.xin.entity;

import java.io.Serializable;

public class CourseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseNumber = "";
	private String courseName = "";
	private float credit = 0;
	private float hours = 0;
	private String description = "";
	
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getHours() {
		return hours;
	}
	public void setHours(float hours) {
		this.hours = hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%.2f\t%.2f\t%s\t",this.courseNumber,this.courseName,this.credit,this.hours,this.description);
	}
	
	
}
