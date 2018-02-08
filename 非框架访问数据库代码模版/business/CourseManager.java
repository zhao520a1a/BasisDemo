package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.CourseDao;
import com.hust.studentmis.xin.entity.CourseBean;

public class CourseManager {

	private CourseDao courseDao = new CourseDao();

	public boolean add(CourseBean course){

		try {
			//��������
			course.setCourseNumber(course.getCourseNumber().trim());
			course.setCourseName(course.getCourseName().trim());
			//������֤
			if (course.getCredit() < 0)
				throw new RuntimeException("�γ�ѧ�ֲ���С��0��");
			if (course.getHours() < 0)
				throw new RuntimeException("�γ̿�ʱ����С��0��");
			return courseDao.insert(course) > 0;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("�γ̱���Ѵ���,������ӣ�");
			} else {
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
			}
		}

	}


	public boolean remove(String courseNumber){
		try {
			return courseDao.delete(courseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("�γ̱�Ų����ڣ�");
			}
			if (e.getMessage().indexOf("FK_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("�ÿγ���Ϣ�������ã�����ɾ����");
			if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ÿγ���Ϣ�������ã�����ɾ����");
			else {
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(CourseBean course, String courseNumber) {
		try {
			//��������
			course.setCourseNumber(course.getCourseNumber().trim());
			course.setCourseName(course.getCourseName().trim());
			//������֤
			if (course.getCredit() < 0)
				throw new RuntimeException("�γ�ѧ�ֲ���С��0��");
			if (course.getHours() < 0)
				throw new RuntimeException("�γ̿�ʱ����С��0��");
			return courseDao.update(course, courseNumber) > 0;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0) 
				throw new RuntimeException("�γ̱���Ѵ��ڣ������޸ģ�");
			if (e.getMessage().indexOf("FK_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("�ÿγ���Ϣ�������ã������޸�����ֵ��");
			if (e.getMessage().indexOf("FK_COURSES_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ÿγ���Ϣ�������ã������޸�����ֵ��");
			else  
				throw new RuntimeException("�޸ĳ�������ԭ��" + e.getMessage());
		}

	}


	public CourseBean search(String CourseNumber){
		try {
			return courseDao.selectOne(CourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}
	
	public List<CourseBean> display(){
		try {
			return courseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
