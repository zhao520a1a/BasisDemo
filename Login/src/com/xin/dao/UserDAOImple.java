package com.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.xin.db.DB;
import com.xin.vo.User;

public class UserDAOImple implements IUserDAO {
     
	private Connection connt;

	public UserDAOImple() {
		connt = DB.getConnection();
	}
	
	@Override
	public boolean login(User user) {
	
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			String sql = "select *  from user_login where username=? and password=?";
			pstmt = connt.prepareStatement(sql);
		    
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			
			flag = pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DB.closeconnection();
		}
		
		return flag;
	}

}
