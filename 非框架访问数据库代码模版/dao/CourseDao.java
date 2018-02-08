package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.CourseBean;

public class CourseDao {


	//�����
	public int insert(CourseBean course) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into COURSES " + " ��values (?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  course.getCourseNumber()  );
			pstmt.setString(2, course.getCourseName()  );
			pstmt.setFloat(3,   course.getCredit()   );
			pstmt.setFloat(4,   course.getHours()  );
			pstmt.setString(5,  course.getDescription() );

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	// �����:��ʹ������������ӣ��������
	public int insert1(ArrayList<CourseBean> courses) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into COURSES " + " ��values (?,?,?,?,?) " ;
		int count = 0;

		try {
			conn =   MyConnection.getConnection();;
			for(CourseBean course: courses) {
				try{
					pstmt = conn.prepareStatement(strSql);
					pstmt.setString(1,  course.getCourseNumber()  );
					pstmt.setString(2, course.getCourseName()  );
					pstmt.setFloat(3,   course.getCredit()   );
					pstmt.setFloat(4,   course.getHours()  );
					pstmt.setString(5,  course.getDescription() );
					count += pstmt.executeUpdate();
				} catch(SQLException e){
					System.out.println("���ʧ��");
				}
			}
			return count;
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}
	// �����:ʹ�������������:�����or�������
	public int insert(List<CourseBean> courses) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into COURSES " + " ��values (?,?,?,?,?) " ;
		int count = 0;

		try {
			conn =   MyConnection.getConnection();;
			conn.setAutoCommit(false);
			for(CourseBean course: courses) {
				pstmt = conn.prepareStatement(strSql);
				pstmt.setString(1,  course.getCourseNumber()  );
				pstmt.setString(2, course.getCourseName()  );
				pstmt.setFloat(3,   course.getCredit()   );
				pstmt.setFloat(4,   course.getHours()  );
				pstmt.setString(5,  course.getDescription() );
				count += pstmt.executeUpdate();
			}
			conn.commit();
			return count;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//����ɾ��
	public int delete(String courseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from COURSES " + " ��where CourseNumber = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, courseNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	// ����ɾ�����������б�ɾ��
	public int delete( String [] courseNumbers ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String strCourseNumbers = "' '";
		for (int i = 0; courseNumbers != null && i < courseNumbers.length; i++){
			strCourseNumbers += ", '" + courseNumbers + "'";
		}
		String strSql = " DELETE FROM Courses " +
		" WHERE CourseNumber IN ( " + strCourseNumbers + " ) ";

		try {
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;		} finally {
				MyConnection.closeStatementAndConnection(ps, conn);
			}
	}


	//�������޸�
	public int update(CourseBean course,String courseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//ƴ���ַ���
		String strSql = " UPDATE Courses " +
		" SET CourseNumber = ?, CourseName = ?, Credit = ?, Hours = ?, Description = ? " +
		" WHERE CourseNumber = ? ";

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  course.getCourseNumber()  );
			pstmt.setString(2, course.getCourseName()  );
			pstmt.setFloat(3,   course.getCredit()   );
			pstmt.setFloat(4,   course.getHours()  );
			pstmt.setString(5,  course.getDescription() );
			pstmt.setString(6, courseNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//��������ѯһ��
	public CourseBean selectOne(String CourseNumber) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *��from Courses where CourseNumber=?";
		CourseBean course = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, CourseNumber);
			rs = pstmt.executeQuery();
			while(rs.next()){
				course = new CourseBean();
				course.setCourseNumber(rs.getString(1));
				course.setCourseName(rs.getString(2));
				course.setCredit(rs.getFloat(3));
				course.setHours(rs.getFloat(4));
				course.setDescription(rs.getString(5));
			}
			return course;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//��ѯȫ��
	public List<CourseBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *��from Courses";
		List<CourseBean> courses =  new ArrayList<CourseBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CourseBean course = new CourseBean();
				course.setCourseNumber(rs.getString(1));
				course.setCourseName(rs.getString(2));
				course.setCredit(rs.getFloat(3));
				course.setHours(rs.getFloat(4));
				course.setDescription(rs.getString(5));
				courses.add(course);
			}
			return courses;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
