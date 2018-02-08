package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.TeachCourseDao;
import com.hust.studentmis.xin.entity.TeachCourseBean;

public class TeachCourseManager {

	private TeachCourseDao teachCourseDao = new TeachCourseDao();

	public boolean add(TeachCourseBean teachCourse){

		try {
			//数据清理
			teachCourse.setTeachCourseNumber (teachCourse.getTeachCourseNumber().trim());
			teachCourse.setTeacherNumber(teachCourse.getTeacherNumber().trim());
			teachCourse.setCourseNumber(teachCourse.getCourseNumber().trim());


			return teachCourseDao.insert(teachCourse) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课编号已存在，不能添加！");
			else if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课课程不存在，不能添加！");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课教师不存在，不能添加！");
			else
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
		}

	}



	public boolean remove(String teachCourseNumber){
		try {
			return teachCourseDao.delete(teachCourseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("授课编号不存在；");
			} else {
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(TeachCourseBean course, String teachCourseNumber) {
		try {
			//数据清理
			course.setTeachCourseNumber (course.getTeachCourseNumber().trim());
			course.setTeacherNumber(course.getTeacherNumber().trim());
			course.setCourseNumber(course.getCourseNumber().trim());

			return teachCourseDao.update(course, teachCourseNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课编号已存在，不能修改！");
			else if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课课程不存在，不能修改！");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("授课教师不存在，不能修改！");
			else
				throw new RuntimeException("修改出错，错误原因：" + e.getMessage());
		}

	}

	public TeachCourseBean search(String teachCourseNumber){
		try {
			return teachCourseDao.selectOne(teachCourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}

	public List<TeachCourseBean> display(){
		try {
			return teachCourseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
