package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.TeacherDao;
import com.hust.studentmis.xin.entity.TeacherBean;

public class TeacherManager {

	private TeacherDao teacherDao = new TeacherDao();

	public boolean add(TeacherBean teacher){

		try {
			//数据清理
			teacher.setTeacherNumber(teacher.getTeacherNumber().trim());
			teacher.setTeacherName(teacher.getTeacherName().trim());
			teacher.setGender(teacher.getGender().trim());
			teacher.setPhoneNumber(teacher.getPhoneNumber().trim());
			teacher.setAddress(teacher.getAddress().trim());
			return teacherDao.insert(teacher) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHERS") >= 0)
				throw new RuntimeException("教师编号已存在，不能添加！");
			else if (e.getMessage().indexOf("UQ_TEACHERS_TELE") >= 0)
				throw new RuntimeException("手机号已使用，请更换其他手机号重新添加！");
			else
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
			 
		}
	}



	public boolean remove(String teacherNumber){
		try {
			return teacherDao.delete(teacherNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_teacherS") >= 0){
				throw new RuntimeException("教师编号不存在；");
			} else 	if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("该教师信息正被引用，不能删除！");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("该教师信息正被引用，不能删除！");
			else{
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(TeacherBean teacher, String teacherNumber) {
		try {
			//数据清理
			teacher.setTeacherNumber(teacher.getTeacherNumber().trim());
			teacher.setTeacherName(teacher.getTeacherName().trim());

			return teacherDao.update(teacher, teacherNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHERS") >= 0)
				throw new RuntimeException("教师编号已存在，不能修改！");
			else if (e.getMessage().indexOf("UQ_TEACHERS_TELE") >= 0)
				throw new RuntimeException("手机号已使用，请更换其他邮箱重新修改！");
			else  if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("该教师信息正被引用，不能修改主键值！");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("该教师信息正被引用，不能修改主键值！");
			else {
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
			}
		}

	}


	public TeacherBean search(String teacherNumber){
		try {
			return teacherDao.selectOne(teacherNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}

	public List<TeacherBean> display(){
		try {
			return teacherDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
