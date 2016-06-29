package com.xinyiglass.paging.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;

public class DBUtil {
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX","APEX_UAT","apex_uat");
			conn.setAutoCommit(false);//关闭自动提交
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return conn;
	}

	public static void close(Connection conn,OracleCallableStatement stmt,ResultSet rs){
		if(conn != null){
			try{
				conn.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try{
				stmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if(rs != null){
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn,OracleCallableStatement stmt){
		close(conn,stmt,null);
	}
	
	public static void close(Connection conn){
		close(conn,null,null);
	}

	public static void close(OracleCallableStatement stmt,ResultSet rs){
		if(stmt != null){
			try{
				stmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if(rs != null){
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	public static void close(OracleCallableStatement stmt){
		if(stmt != null){
			try{
				stmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
}
