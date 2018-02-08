package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.ClazzDao;
import com.hust.studentmis.xin.entity.ClazzBean;

public class ClazzManager {

	private ClazzDao clazzDao = new ClazzDao();

	public boolean add(ClazzBean clazz){

		try {
			//��������
			clazz.setClazzNumber (clazz.getClazzNumber().trim());
			clazz.setClazzName(clazz.getClazzName().trim());
			 
				 
			return clazzDao.insert(clazz) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_CLAZZES") >= 0)
				throw new RuntimeException("�༶����Ѵ��ڣ�������ӣ�");
			else if (e.getMessage().indexOf("UQ_CLAZZES_NAME") >= 0)
				throw new RuntimeException("�༶�����Ѵ��ڣ���ʹ���������ƣ�");
			else if (e.getMessage().indexOf("UQ_CLAZZES_TEACHERNUMBER") >= 0)
				throw new RuntimeException("�ý�ʦ�Ѿ����ΰ����Σ����ܵ��ζ���༶�İ����Σ�");
			else if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("�ý�ʦ�����ڣ�������ӣ�");
			else
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
		}

	}



	public boolean remove(String clazzNumber){
		try {
			return clazzDao.delete(clazzNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("ORA-00001: Υ��ΨһԼ������ (SCOTT.PK_CLAZZES)") >= 0){
				throw new RuntimeException("�༶��Ų����ڣ�");
			}if( e.getMessage().indexOf("ORA-02292: Υ������Լ������ (SCOTT.FK_CLAZZES_STUDENTS)") >= 0){
				throw new RuntimeException("�ð༶��Ϣ�������ã�����ɾ����");
			} else {
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(ClazzBean clazz, String clazzNumber) {
		try {
			//��������
			clazz.setClazzNumber(clazz.getClazzNumber().trim());
			clazz.setClazzName(clazz.getClazzName().trim());
			 
			return clazzDao.update(clazz, clazzNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_CLAZZES") >= 0)
				throw new RuntimeException("�༶����Ѵ��ڣ�������ӣ�");
			else if (e.getMessage().indexOf("UQ_CLAZZES_NAME") >= 0)
				throw new RuntimeException("�༶�����Ѵ��ڣ���ʹ���������ƣ�");
			else if (e.getMessage().indexOf("UQ_CLAZZES_TEACHERNUMBER") >= 0)
				throw new RuntimeException("�ý�ʦ�Ѿ����ΰ����Σ����ܵ��ζ���༶�İ����Σ�");
			else if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("�ý�ʦ�����ڣ�������ӣ�");
			else  if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("�ð༶��Ϣ�������ã����ܱ��޸ģ�");
			else
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
			
		}

	}

	public ClazzBean search(String ClazzNumber){
		try {
			return clazzDao.selectOne(ClazzNumber) ;
		} catch (SQLException e) {
				throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}
	
	public List<ClazzBean> display(){
		try {
			return clazzDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
