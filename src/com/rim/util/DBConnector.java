package com.rim.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

	public static Connection getConnector() throws Exception {
		String user="user02";
		String password ="user02";
		String url = "jdbc:oracle:thin:@211.238.142.24:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
		
	}
	
	public static void disConnect(PreparedStatement pst) throws Exception {
		pst.close();
	}
	
	public static void disConnect(ResultSet rs, PreparedStatement pst) throws Exception {
		rs.close();
		pst.close();
	}
	
	public static void disConnect(PreparedStatement pst, Connection conn) throws Exception {
		pst.close();
		conn.close();
	}
	
	public static void disConnect(ResultSet rs, PreparedStatement pst, Connection conn) throws Exception {
		rs.close();
		pst.close();
		conn.close();
	}

}
