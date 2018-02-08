package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.CourseBean;
import com.hust.studentmis.xin.entity.TeachCourseBean;
import com.hust.studentmis.xin.entity.TeacherBean;

public class TeachCourseDao {


	//单添加
	public int insert(TeachCourseBean teachCourse) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into TEACHCOURSES " + " 　values (?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1, teachCourse.getTeachCourseNumber()   );
			pstmt.setString(2,   teachCourse.getTeacherNumber()     );
			pstmt.setString(3,  teachCourse.getCourseNumber()    );
			pstmt.setDate(4,   teachCourse.getBeginDate()   );
			pstmt.setDate(5,  teachCourse.getEndDate()  );

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//主键删除
	public int delete(String teachCourseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from TEACHCOURSES " + " 　where teachCourseNumber = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, teachCourseNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	// 批量删除，按主键列表删除
	public int delete( String[] teachCourseNumbers ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String strTeachCourseNumbers = "' '";
		for (int i = 0; teachCourseNumbers != null && i < teachCourseNumbers.length; i++){
			strTeachCourseNumbers += ", '" + teachCourseNumbers + "'";
		}
		String strSql = " DELETE FROM TEACHCOURSES " +
		" WHERE teachCourseNumber IN ( " + strTeachCourseNumbers + " ) ";

		try {
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;		} finally {
				MyConnection.closeStatementAndConnection(ps, conn);
			}
	}


	//按主键修改，可修改主键
	public int update(TeachCourseBean teachCourse,String teachCourseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//拼接字符串
		String strSql = " UPDATE TEACHCOURSES " +
		" SET teachCourseNumber = ?,teacherNumber= ?, courseNumber = ?, beginDate = ?, endDate = ? " +
		" WHERE teachCourseNumber = ? ";

		try {
			conn =   MyConnection.getConnection();
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1, teachCourse.getTeachCourseNumber()   );
			pstmt.setString(2,   teachCourse.getTeacherNumber()     );
			pstmt.setString(3,  teachCourse.getCourseNumber()    );
			pstmt.setDate(4,   teachCourse.getBeginDate()   );
			pstmt.setDate(5,  teachCourse.getEndDate()  );
			pstmt.setString(6, teachCourseNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键查询一条
	public TeachCourseBean selectOne(String teachCourseNumber) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
/*
 * 如果非null,下面两种都可以，若有null字段 ，建议第二种；
 */
//		String strSql = " SELECT TeachCourses.*, Teachers.*, Courses.* " +
//						" FROM TeachCourses, Teachers, Courses " +
//						" WHERE TeachCourses.TeacherNumber = Teachers.TeacherNumber " +
//						" 	AND TeachCourses.CourseNumber = Courses.CourseNumber " +
//						" 	AND TeachCourseNumber = ? ";
		String strSql = " SELECT TeachCourses.*, Teachers.*, Courses.* " +
						" FROM TeachCourses " +
						" 	INNER JOIN Teachers ON TeachCourses.TeacherNumber = Teachers.TeacherNumber" +
						" 	INNER JOIN Courses ON TeachCourses.CourseNumber = Courses.CourseNumber " +
						" WHERE TeachCourseNumber = ? ";
		
		TeachCourseBean teachCourse = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, teachCourseNumber);
			rs = pstmt.executeQuery();
			while(rs.next()){
				teachCourse = new TeachCourseBean();
				teachCourse.setTeachCourseNumber(rs.getString(1));
				teachCourse.setTeacherNumber(rs.getString(2));
				teachCourse.setCourseNumber(  rs.getString(3));
				teachCourse.setBeginDate (rs.getDate(4));
				teachCourse.setEndDate(rs.getDate(5));
				
				teachCourse.setTeacher(new TeacherBean());
				teachCourse.setCourse(new CourseBean());
				teachCourse.getTeacher().setTeacherNumber(rs.getString(6));
				teachCourse.getTeacher().setTeacherName(rs.getString(7));
				teachCourse.getTeacher().setGender(  rs.getString(8));
				teachCourse.getTeacher().setBirthday (rs.getDate(9));
				teachCourse.getTeacher().setPhoneNumber (rs.getString(10));
				teachCourse.getTeacher().setAddress (rs.getString(11));
				
				teachCourse.getCourse().setCourseNumber(rs.getString(12));
				teachCourse.getCourse().setCourseName(rs.getString(13));
				teachCourse.getCourse().setCredit(rs.getFloat(14));
				teachCourse.getCourse().setHours(rs.getFloat(15));
				teachCourse.getCourse().setDescription(rs.getString(16));
			}
			return teachCourse;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//查询全部
	public List<TeachCourseBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = " SELECT TeachCourses.*, Teachers.*, Courses.* " +
		" FROM TeachCourses " +
		" 	INNER JOIN Teachers ON TeachCourses.TeacherNumber = Teachers.TeacherNumber" +
		" 	INNER JOIN Courses ON TeachCourses.CourseNumber = Courses.CourseNumber ";
		List<TeachCourseBean> teachCourses = new ArrayList<TeachCourseBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TeachCourseBean teachCourse = new TeachCourseBean();
				teachCourse.setTeachCourseNumber(rs.getString(1));
				teachCourse.setTeacherNumber(rs.getString(2));
				teachCourse.setCourseNumber(  rs.getString(3));
				teachCourse.setBeginDate (rs.getDate(4));
				teachCourse.setEndDate(rs.getDate(5));
				
				teachCourse.setTeacher(new TeacherBean());
				teachCourse.setCourse(new CourseBean());
				teachCourse.getTeacher().setTeacherNumber(rs.getString(6));
				teachCourse.getTeacher().setTeacherName(rs.getString(7));
				teachCourse.getTeacher().setGender(  rs.getString(8));
				teachCourse.getTeacher().setBirthday (rs.getDate(9));
				teachCourse.getTeacher().setPhoneNumber (rs.getString(10));
				teachCourse.getTeacher().setAddress (rs.getString(11));
				
				teachCourse.getCourse().setCourseNumber(rs.getString(12));
				teachCourse.getCourse().setCourseName(rs.getString(13));
				teachCourse.getCourse().setCredit(rs.getFloat(14));
				teachCourse.getCourse().setHours(rs.getFloat(15));
				teachCourse.getCourse().setDescription(rs.getString(16));
				
				teachCourses.add(teachCourse);
			}
			return teachCourses;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
