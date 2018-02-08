package com.hust.studentmis.xin.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

	private static String driverName = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库驱动包加载失败！");
		}
	}


	public static Connection getConnection() throws SQLException     {

		try {
			return     DriverManager.getConnection(url,"scott","triger");
//			return     DriverManager.getConnection(url,"system","123abc");
		} catch (SQLException e) {
			//			No suitable driver found for jdbc:oracle:thin:@127.0.0.1:1521:orcl  建立链接时，驱动时未加载到JVM
			//			java.sql.SQLException: 指定了无效的 Oracle URL     jdbc:oracle:thin:写的不对
			//			Io 异常: The Network Adapter could not establish the connection   IP,port不对  服务器未启动或服务未启动；
			/* 			 Listener refused the connection with the following error:       数据库名称错误不对
			ORA-12505, TNS:listener does not currently know of SID given in connect descriptor
			The Connection descriptor used by the client was:
			127.0.0.1:1521:ds*/
			//			 ORA-01017: invalid username/password; logon denied  用户或密码错误
			if(e.getMessage().indexOf("No suitable driver found for jdbc:")>=0) {
				System.out.println("建立链接时，驱动时未加载到JVM");
			} else if(e.getMessage().indexOf("指定了无效的 Oracle URL")>=0){
				System.out.println("jdbc:oracle:thin:写的不对");
			} else if(e.getMessage().indexOf("Io 异常: The Network Adapter could not establish the connection ")>=0){
				System.out.println("IP或port不对  服务器未启动或服务未启动；");
			} else if(e.getMessage().indexOf("Listener refused the connection with the following error:")>=0){
				System.out.println("数据库名称错误不对");
			} else if(e.getMessage().indexOf("ORA-01017: invalid username/password;")>=0){
				System.out.println(" 用户或密码错误");
			} else{
				throw e;
			}
		}
		return null;
	}

	public static void closeConnection (Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement (Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeStatementAndConnection (Statement stmt, Connection conn) {
		closeStatement(stmt);
		closeConnection(conn);
	}
}
