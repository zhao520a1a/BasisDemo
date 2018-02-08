package com.hust.studentmis.xin.entity;

import java.io.Serializable;
import java.sql.Date;

public class TeachCourseBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private TeacherBean teacher;
	private CourseBean course;
	
	private String teachCourseNumber = "";
	private String teacherNumber = "";
	private String courseNumber = "";
	private Date beginDate  = new Date(System.currentTimeMillis());
	private Date endDate  = new Date(System.currentTimeMillis());
	
	public String getTeachCourseNumber() {
		return teachCourseNumber;
	}
	public void setTeachCourseNumber(String teachCourseNumber) {
		this.teachCourseNumber = teachCourseNumber;
	}
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setCourse(CourseBean course) {
		this.course = course;
	}
	public CourseBean getCourse() {
		return course;
	}
	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}
	public TeacherBean getTeacher() {
		return teacher;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t",this.teachCourseNumber,this.teacher.getTeacherName(),this.course.getCourseName(),this.beginDate,this.endDate );
	}


}
