package com.xin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static Connection connt;

	public static Connection getConnection() {
		try {
			// 把mysql的驱动加载到内存
			Class.forName("org.gjt.mm.mysql.Driver");
			// 建立mysql数据库的连接
			connt = DriverManager.getConnection("jdbc:mysql://192.168.1.183/web?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connt;
	}

	public static void closeconnection() {

		try {
			if (connt != null) {
				connt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
