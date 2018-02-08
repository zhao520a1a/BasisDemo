package com.hust.studentmis.xin.entity;

import java.io.Serializable;
import java.sql.Date;

public class ClazzBean implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	private String clazzNumber = "";
	private String clazzName = "";
	private String teacherNumber = "";
	private Date beginDate = new Date(System.currentTimeMillis());
	private Date endDate = new Date(System.currentTimeMillis());
	
	public String getClazzNumber() {
		return clazzNumber;
	}
	public void setClazzNumber(String clazzNumber) {
		this.clazzNumber = clazzNumber;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
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
	
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t",this.clazzNumber,this.clazzName,this.teacherNumber,this.beginDate,this.endDate);
	}
}
