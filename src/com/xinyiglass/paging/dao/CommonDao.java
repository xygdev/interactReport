package com.xinyiglass.paging.dao;

import java.sql.Connection;
import java.util.List;

import com.xinyiglass.paging.entity.SqlResultSet;


/**
 * 一些数据库的查询以及DML处理和调用Pkg的封装好的公用接口。
 * @author Sam.T 2016/4/26
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface CommonDao {
	/**
	 * 返回String列表
	 * @param conn Connection连接。为了可以公用一个会话。 
	 * @param sqlStmt SQL查询语句
	 * @param param SQL语句的参数，例如  :1
	 * @param paramVal SQL语句的参数的值
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> retStrListBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * 重载方法。方便使用。没的参数默认是null。
	 * @author Sam.T 2016/4/26
	 * @return List<String>
	 * @see com.xinyiglass.web03.dao.CommonDao#retStrListBySql(Connection, String, String[], Object[])
	 */
	public List<String> retStrListBySql(String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * 重载方法。方便使用。没的参数默认是null。
	 * @author Sam.T 2016/4/26
	 * @return List<String>
	 * @see com.xinyiglass.web03.dao.CommonDao#retStrListBySql(Connection, String, String[], Object[])
	 */
	public List<String> retStrListBySql(String sqlStmt) throws Exception;
	/**
	 * 返回单个String
	 * @param conn Connection连接。为了可以公用一个会话。 
	 * @param sqlStmt sqlStmt SQL查询语句
	 * @param param SQL语句的参数，例如  :1
	 * @param paramVal SQL语句的参数的值
	 * @return String
	 * @throws Exception
	 */
	public String retStrBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * 重载方法。方便使用。没的参数默认是null。
	 * @author Sam.T 2016/4/26
	 * @return List<String>
	 * @see com.xinyiglass.web03.dao.CommonDao#retStrBySql(String,String[],String[])
	 */
	public String retStrBySql(String sqlStmt) throws Exception;
	
	
	public SqlResultSet retResultSetBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	public SqlResultSet retResultSetBySql(String sqlStmt,String[] param,Object[] paramVal) throws Exception;


	public SqlResultSet findByMinMax(Connection conn,String sqlStmt,String[] param,Object[] paramVal
			,Integer pageMinRow,Integer pageMaxRow) throws Exception;
	public SqlResultSet findByMinMax(String sqlStmt,String[] param,Object[] paramVal
			,Integer pageMinRow,Integer pageMaxRow) throws Exception;
	
	
}
