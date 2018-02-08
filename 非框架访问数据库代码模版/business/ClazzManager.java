package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.ClazzDao;
import com.hust.studentmis.xin.entity.ClazzBean;

public class ClazzManager {

	private ClazzDao clazzDao = new ClazzDao();

	public boolean add(ClazzBean clazz){

		try {
			//数据清理
			clazz.setClazzNumber (clazz.getClazzNumber().trim());
			clazz.setClazzName(clazz.getClazzName().trim());
			 
				 
			return clazzDao.insert(clazz) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_CLAZZES") >= 0)
				throw new RuntimeException("班级编号已存在，不能添加！");
			else if (e.getMessage().indexOf("UQ_CLAZZES_NAME") >= 0)
				throw new RuntimeException("班级名称已存在，请使用其它名称！");
			else if (e.getMessage().indexOf("UQ_CLAZZES_TEACHERNUMBER") >= 0)
				throw new RuntimeException("该教师已经担任班主任，不能担任多个班级的班主任！");
			else if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("该教师不存在，不能添加！");
			else
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
		}

	}



	public boolean remove(String clazzNumber){
		try {
			return clazzDao.delete(clazzNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("ORA-00001: 违反唯一约束条件 (SCOTT.PK_CLAZZES)") >= 0){
				throw new RuntimeException("班级编号不存在；");
			}if( e.getMessage().indexOf("ORA-02292: 违反完整约束条件 (SCOTT.FK_CLAZZES_STUDENTS)") >= 0){
				throw new RuntimeException("该班级信息正被引用，不能删除！");
			} else {
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(ClazzBean clazz, String clazzNumber) {
		try {
			//数据清理
			clazz.setClazzNumber(clazz.getClazzNumber().trim());
			clazz.setClazzName(clazz.getClazzName().trim());
			 
			return clazzDao.update(clazz, clazzNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_CLAZZES") >= 0)
				throw new RuntimeException("班级编号已存在，不能添加！");
			else if (e.getMessage().indexOf("UQ_CLAZZES_NAME") >= 0)
				throw new RuntimeException("班级名称已存在，请使用其它名称！");
			else if (e.getMessage().indexOf("UQ_CLAZZES_TEACHERNUMBER") >= 0)
				throw new RuntimeException("该教师已经担任班主任，不能担任多个班级的班主任！");
			else if (e.getMessage().indexOf("FK_TEACHERS_CLASSES") >= 0)
				throw new RuntimeException("该教师不存在，不能添加！");
			else  if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("该班级信息正被引用，不能被修改！");
			else
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
			
		}

	}

	public ClazzBean search(String ClazzNumber){
		try {
			return clazzDao.selectOne(ClazzNumber) ;
		} catch (SQLException e) {
				throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}
	
	public List<ClazzBean> display(){
		try {
			return clazzDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
