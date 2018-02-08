package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.TeachCourseDao;
import com.hust.studentmis.xin.entity.TeachCourseBean;

public class TeachCourseManager {

	private TeachCourseDao teachCourseDao = new TeachCourseDao();

	public boolean add(TeachCourseBean teachCourse){

		try {
			//��������
			teachCourse.setTeachCourseNumber (teachCourse.getTeachCourseNumber().trim());
			teachCourse.setTeacherNumber(teachCourse.getTeacherNumber().trim());
			teachCourse.setCourseNumber(teachCourse.getCourseNumber().trim());


			return teachCourseDao.insert(teachCourse) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿα���Ѵ��ڣ�������ӣ�");
			else if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿογ̲����ڣ�������ӣ�");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿν�ʦ�����ڣ�������ӣ�");
			else
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
		}

	}



	public boolean remove(String teachCourseNumber){
		try {
			return teachCourseDao.delete(teachCourseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("�ڿα�Ų����ڣ�");
			} else {
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(TeachCourseBean course, String teachCourseNumber) {
		try {
			//��������
			course.setTeachCourseNumber (course.getTeachCourseNumber().trim());
			course.setTeacherNumber(course.getTeacherNumber().trim());
			course.setCourseNumber(course.getCourseNumber().trim());

			return teachCourseDao.update(course, teachCourseNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿα���Ѵ��ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿογ̲����ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ڿν�ʦ�����ڣ������޸ģ�");
			else
				throw new RuntimeException("�޸ĳ�������ԭ��" + e.getMessage());
		}

	}

	public TeachCourseBean search(String teachCourseNumber){
		try {
			return teachCourseDao.selectOne(teachCourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}

	public List<TeachCourseBean> display(){
		try {
			return teachCourseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
