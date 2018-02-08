package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.TeacherBean;

public class TeacherDao {


	//单添加
	public int insert(TeacherBean teacher) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into teachers " + " 　values (?,?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  teacher.getTeacherNumber()   );
			pstmt.setString(2, teacher.getTeacherName()  );
			pstmt.setString(3,   teacher.getGender()    );
			pstmt.setDate(4,   teacher.getBirthday()  );
			pstmt.setString(5,  teacher.getPhoneNumber() );
			pstmt.setString(6,  teacher.getAddress()  );

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//主键删除
	public int delete(String teacherNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from teachers " + " 　where teacherNumber = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, teacherNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

 


	//按主键修改
	public int update(TeacherBean teacher,String teacherNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//拼接字符串
		String strSql = " UPDATE teachers " +
		" SET teacherNumber = ?, teacherName = ?, Gender = ?, Birthday = ?, PhoneNumber = ?,Address = ?   " +
		" WHERE teacherNumber = ? ";

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  teacher.getTeacherNumber() );
			pstmt.setString(2, teacher.getTeacherName()  );
			pstmt.setString(3,   teacher.getGender()    );
			pstmt.setDate(4,   teacher.getBirthday()   );
			pstmt.setString(5,  teacher.getPhoneNumber() );
			pstmt.setString(6,  teacher.getAddress() );
			pstmt.setString(7, teacherNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键查询一条
	public TeacherBean selectOne(String teacherNumber) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from teachers where teacherNumber=?";
		TeacherBean teacher = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, teacherNumber);
			rs = pstmt.executeQuery();
			while(rs.next()){
				teacher = new TeacherBean();
				teacher.setTeacherNumber(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setGender(  rs.getString(3));
				teacher.setBirthday (rs.getDate(4));
				teacher.setPhoneNumber (rs.getString(5));
				teacher.setAddress (rs.getString(6));
			}
			return teacher;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//查询全部
	public List<TeacherBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from teachers";
		List<TeacherBean> teachers =  new ArrayList<TeacherBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TeacherBean teacher = new TeacherBean();
				teacher.setTeacherNumber(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setGender(  rs.getString(3));
				teacher.setBirthday (rs.getDate(4));
				teacher.setPhoneNumber (rs.getString(5));
				teacher.setAddress (rs.getString(6));
				teachers.add(teacher);
			}
			return teachers;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
