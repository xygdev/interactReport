package com.xinyiglass.paging.dao;

import java.sql.Connection;
import java.util.List;

import com.xinyiglass.paging.entity.SqlResultSet;


/**
 * һЩ���ݿ�Ĳ�ѯ�Լ�DML����͵���Pkg�ķ�װ�õĹ��ýӿڡ�
 * @author Sam.T 2016/4/26
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface CommonDao {
	/**
	 * ����String�б�
	 * @param conn Connection���ӡ�Ϊ�˿��Թ���һ���Ự�� 
	 * @param sqlStmt SQL��ѯ���
	 * @param param SQL���Ĳ���������  :1
	 * @param paramVal SQL���Ĳ�����ֵ
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> retStrListBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * ���ط���������ʹ�á�û�Ĳ���Ĭ����null��
	 * @author Sam.T 2016/4/26
	 * @return List<String>
	 * @see com.xinyiglass.web03.dao.CommonDao#retStrListBySql(Connection, String, String[], Object[])
	 */
	public List<String> retStrListBySql(String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * ���ط���������ʹ�á�û�Ĳ���Ĭ����null��
	 * @author Sam.T 2016/4/26
	 * @return List<String>
	 * @see com.xinyiglass.web03.dao.CommonDao#retStrListBySql(Connection, String, String[], Object[])
	 */
	public List<String> retStrListBySql(String sqlStmt) throws Exception;
	/**
	 * ���ص���String
	 * @param conn Connection���ӡ�Ϊ�˿��Թ���һ���Ự�� 
	 * @param sqlStmt sqlStmt SQL��ѯ���
	 * @param param SQL���Ĳ���������  :1
	 * @param paramVal SQL���Ĳ�����ֵ
	 * @return String
	 * @throws Exception
	 */
	public String retStrBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception;
	/**
	 * ���ط���������ʹ�á�û�Ĳ���Ĭ����null��
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
