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
			System.out.println("���ݿ�����������ʧ�ܣ�");
		}
	}


	public static Connection getConnection() throws SQLException     {

		try {
			return     DriverManager.getConnection(url,"scott","triger");
//			return     DriverManager.getConnection(url,"system","123abc");
		} catch (SQLException e) {
			//			No suitable driver found for jdbc:oracle:thin:@127.0.0.1:1521:orcl  ��������ʱ������ʱδ���ص�JVM
			//			java.sql.SQLException: ָ������Ч�� Oracle URL     jdbc:oracle:thin:д�Ĳ���
			//			Io �쳣: The Network Adapter could not establish the connection   IP,port����  ������δ���������δ������
			/* 			 Listener refused the connection with the following error:       ���ݿ����ƴ��󲻶�
			ORA-12505, TNS:listener does not currently know of SID given in connect descriptor
			The Connection descriptor used by the client was:
			127.0.0.1:1521:ds*/
			//			 ORA-01017: invalid username/password; logon denied  �û����������
			if(e.getMessage().indexOf("No suitable driver found for jdbc:")>=0) {
				System.out.println("��������ʱ������ʱδ���ص�JVM");
			} else if(e.getMessage().indexOf("ָ������Ч�� Oracle URL")>=0){
				System.out.println("jdbc:oracle:thin:д�Ĳ���");
			} else if(e.getMessage().indexOf("Io �쳣: The Network Adapter could not establish the connection ")>=0){
				System.out.println("IP��port����  ������δ���������δ������");
			} else if(e.getMessage().indexOf("Listener refused the connection with the following error:")>=0){
				System.out.println("���ݿ����ƴ��󲻶�");
			} else if(e.getMessage().indexOf("ORA-01017: invalid username/password;")>=0){
				System.out.println(" �û����������");
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
