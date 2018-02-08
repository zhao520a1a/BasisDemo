package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.CourseDao;
import com.hust.studentmis.xin.entity.CourseBean;

public class CourseManager {

	private CourseDao courseDao = new CourseDao();

	public boolean add(CourseBean course){

		try {
			//数据清理
			course.setCourseNumber(course.getCourseNumber().trim());
			course.setCourseName(course.getCourseName().trim());
			//数据验证
			if (course.getCredit() < 0)
				throw new RuntimeException("课程学分不能小于0！");
			if (course.getHours() < 0)
				throw new RuntimeException("课程课时不能小于0！");
			return courseDao.insert(course) > 0;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("课程编号已存在,不能添加！");
			} else {
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
			}
		}

	}


	public boolean remove(String courseNumber){
		try {
			return courseDao.delete(courseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("课程编号不存在；");
			}
			if (e.getMessage().indexOf("FK_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("该课程信息正被引用，不能删除！");
			if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("该课程信息正被引用，不能删除！");
			else {
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(CourseBean course, String courseNumber) {
		try {
			//数据清理
			course.setCourseNumber(course.getCourseNumber().trim());
			course.setCourseName(course.getCourseName().trim());
			//数据验证
			if (course.getCredit() < 0)
				throw new RuntimeException("课程学分不能小于0！");
			if (course.getHours() < 0)
				throw new RuntimeException("课程课时不能小于0！");
			return courseDao.update(course, courseNumber) > 0;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0) 
				throw new RuntimeException("课程编号已存在，不能修改！");
			if (e.getMessage().indexOf("FK_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("该课程信息正被引用，不能修改主键值！");
			if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("该课程信息正被引用，不能修改主键值！");
			else  
				throw new RuntimeException("修改出错，错误原因：" + e.getMessage());
		}

	}


	public CourseBean search(String CourseNumber){
		try {
			return courseDao.selectOne(CourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}
	
	public List<CourseBean> display(){
		try {
			return courseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
