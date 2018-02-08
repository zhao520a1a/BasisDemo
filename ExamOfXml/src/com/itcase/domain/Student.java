package com.itcase.domain;

/*
 * 封装学生信息
 * <student examid="111" idcard="111">
		<name>张三</name>
		<location>沈阳</location>
		<grade>89</grade>
	</student>
 */

public class Student {
	private String examid;
	private String idcard;
	private String name;
	private String location;
	private double grade;
	
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
}
