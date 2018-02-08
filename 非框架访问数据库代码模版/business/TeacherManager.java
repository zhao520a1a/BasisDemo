package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.TeacherDao;
import com.hust.studentmis.xin.entity.TeacherBean;

public class TeacherManager {

	private TeacherDao teacherDao = new TeacherDao();

	public boolean add(TeacherBean teacher){

		try {
			//��������
			teacher.setTeacherNumber(teacher.getTeacherNumber().trim());
			teacher.setTeacherName(teacher.getTeacherName().trim());
			teacher.setGender(teacher.getGender().trim());
			teacher.setPhoneNumber(teacher.getPhoneNumber().trim());
			teacher.setAddress(teacher.getAddress().trim());
			return teacherDao.insert(teacher) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHERS") >= 0)
				throw new RuntimeException("��ʦ����Ѵ��ڣ�������ӣ�");
			else if (e.getMessage().indexOf("UQ_TEACHERS_TELE") >= 0)
				throw new RuntimeException("�ֻ�����ʹ�ã�����������ֻ���������ӣ�");
			else
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
			 
		}
	}



	public boolean remove(String teacherNumber){
		try {
			return teacherDao.delete(teacherNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_teacherS") >= 0){
				throw new RuntimeException("��ʦ��Ų����ڣ�");
			} else 	if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("�ý�ʦ��Ϣ�������ã�����ɾ����");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ý�ʦ��Ϣ�������ã�����ɾ����");
			else{
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(TeacherBean teacher, String teacherNumber) {
		try {
			//��������
			teacher.setTeacherNumber(teacher.getTeacherNumber().trim());
			teacher.setTeacherName(teacher.getTeacherName().trim());

			return teacherDao.update(teacher, teacherNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_TEACHERS") >= 0)
				throw new RuntimeException("��ʦ����Ѵ��ڣ������޸ģ�");
			else if (e.getMessage().indexOf("UQ_TEACHERS_TELE") >= 0)
				throw new RuntimeException("�ֻ�����ʹ�ã�������������������޸ģ�");
			else  if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("�ý�ʦ��Ϣ�������ã������޸�����ֵ��");
			else if (e.getMessage().indexOf("FK_TEACHERS_TEACHCOURSES") >= 0)
				throw new RuntimeException("�ý�ʦ��Ϣ�������ã������޸�����ֵ��");
			else {
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
			}
		}

	}


	public TeacherBean search(String teacherNumber){
		try {
			return teacherDao.selectOne(teacherNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}

	public List<TeacherBean> display(){
		try {
			return teacherDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
