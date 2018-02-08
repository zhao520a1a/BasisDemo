package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.ClazzBean;

public class ClazzDao {


	//单添加
	public int insert(ClazzBean clazz) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into CLAZZES " + " 　values (?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  clazz.getClazzNumber()  );
			pstmt.setString(2, clazz.getClazzName()  );
			pstmt.setDate(3,   clazz.getBeginDate() );
			pstmt.setDate(4,   clazz.getEndDate()  );
			
			if (clazz.getTeacherNumber().length() == 0)
				pstmt.setNull(5, Types.VARCHAR);
			else
				pstmt.setString(5, clazz.getTeacherNumber());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//主键删除
	public int delete(String clazzNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from CLAZZES " + " 　where CLAZZNUMBER = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, clazzNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键修改
	public int update(ClazzBean clazz,String clazzNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//拼接字符串
		String strSql = " UPDATE CLAZZES " +
		" SET CLAZZNUMBER = ?, CLAZZNAME = ?, BEGINDATE = ?, ENDDATE = ?, TEACHERNUMBER = ? " +
		" WHERE CLAZZNUMBER = ? ";

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  clazz.getClazzNumber()   );
			pstmt.setString(2, clazz.getClazzName()   );
			pstmt.setDate(3,   clazz.getBeginDate()   );
			pstmt.setDate(4,   clazz.getEndDate()  );
			if (clazz.getTeacherNumber().length() == 0)
				pstmt.setNull(5, Types.VARCHAR);
			else
				pstmt.setString(5, clazz.getTeacherNumber());

			pstmt.setString(6,  clazzNumber);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键查询一条
	public ClazzBean selectOne(String CLAZZNUMBER) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from CLAZZES where CLAZZNUMBER=?";
		ClazzBean clazz = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, CLAZZNUMBER);
			rs = pstmt.executeQuery();
			while(rs.next()){
				clazz = new ClazzBean();
				clazz.setClazzNumber(rs.getString(1));
				clazz.setClazzName(rs.getString(2));
				clazz.setBeginDate(rs.getDate(3));
				clazz.setEndDate(rs.getDate(4));
				if (rs.wasNull())
					clazz.setTeacherNumber("");
				else
					clazz.setTeacherNumber( rs.getString(5) );
			}
			return clazz;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//查询全部
	public List<ClazzBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from CLAZZES";
		List<ClazzBean> clazzs =  new ArrayList<ClazzBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ClazzBean clazz = new ClazzBean();
				clazz.setClazzNumber(rs.getString(1));
				clazz.setClazzName(rs.getString(2));
				clazz.setBeginDate(rs.getDate(3));
				clazz.setEndDate(rs.getDate(4));
				if (rs.wasNull())
					clazz.setTeacherNumber("");
				else
					clazz.setTeacherNumber( rs.getString(5) );
				clazzs.add(clazz);
			}
			return clazzs;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
