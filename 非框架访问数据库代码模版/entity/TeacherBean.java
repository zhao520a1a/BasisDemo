package com.hust.studentmis.xin.entity;

import java.io.Serializable;
import java.sql.Date;

public class TeacherBean implements Serializable  {

 
	private static final long serialVersionUID = 1L;
	
	private String teacherNumber = "";
	private String teacherName = "";
	private String gender = "";
	private Date birthday = new Date(System.currentTimeMillis());
	private String phoneNumber = "";
	private String address = "";
	
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t",this.teacherNumber,this.teacherName,this.gender,this.birthday,this.phoneNumber,this.address );
	}
	
	
}
