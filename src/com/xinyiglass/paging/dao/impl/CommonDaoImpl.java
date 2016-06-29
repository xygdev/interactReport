package com.xinyiglass.paging.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import com.xinyiglass.paging.dao.CommonDao;
import com.xinyiglass.paging.entity.EmpVO;
import com.xinyiglass.paging.entity.SqlResultSet;
import com.xinyiglass.paging.util.Constant;
import com.xinyiglass.paging.util.DBUtil;
import com.xinyiglass.paging.util.Factory;
import com.xinyiglass.paging.util.TypeConvert;

@SuppressWarnings("rawtypes")
public class CommonDaoImpl implements CommonDao {
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}

	public List<String> retStrListBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal)
			throws Exception{
		List<String> strList = new ArrayList<String>();
		//Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = (OracleCallableStatement) conn.prepareCall(sqlStmt);
			log("sqlStmt:"+sqlStmt);
			//绑定变量
			if(param!=null&&param.length!=0){
				for(int i=0;i<param.length;i++){
                    stmt.setObject(param[i], paramVal[i]);
					log(param[i]+":"+TypeConvert.type2Str(paramVal[i]));
				}
			}
			rs = stmt.executeQuery();
			while(rs.next()){
				strList.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt,rs);
		}
		return strList;
	}

	public List<String> retStrListBySql(String sqlStmt,String[] param,Object[] paramVal) throws Exception{
		Connection conn=DBUtil.getConnection();
		List<String> ret=new ArrayList<String>();
		try{
			ret = retStrListBySql(conn,sqlStmt,param,paramVal);
		}catch(Exception e){
			throw e;
		}finally{
			DBUtil.close(conn);
		}
		return ret;
	}
	
	public List<String> retStrListBySql(String sqlStmt) throws Exception{
		return retStrListBySql(sqlStmt,null,null);
	}
	
	public String retStrBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal)
			throws Exception{
		String ret=null;
		List<String> retList = retStrListBySql(conn,sqlStmt,param,paramVal);
		ret = retList.get(0);
		/*
		for (int i =0;i<retList.size();i++){
			ret = retList.get(i);
			break;
		}*/
		return ret;
	}
	public String retStrBySql(String sqlStmt,String[] param,Object[] paramVal)
			throws Exception{
		String ret=null;
		List<String> retList = retStrListBySql(sqlStmt,param,paramVal);
		ret = retList.get(0);
		/*
		for (int i =0;i<retList.size();i++){
			ret = retList.get(i);
			break;
		}*/
		return ret;
	}
	public String retStrBySql(String sqlStmt)
			throws Exception{
		return retStrBySql(sqlStmt,null,null);
	}
	
	public SqlResultSet retResultSetBySql(Connection conn,String sqlStmt,String[] param,Object[] paramVal) throws Exception{
		SqlResultSet ret=new SqlResultSet();
		log("SqlResultSet sqlStmt:"+sqlStmt);
		if (!(sqlStmt!=null&&sqlStmt.length()!=0)){
			log("sqlStmt is null or sqlStmt.length=0!");
			return ret;
		}
		//Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt=(OracleCallableStatement)conn.prepareCall(sqlStmt);
			//绑定变量
			if(param!=null&&param.length!=0){
				for(int i=0;i<param.length;i++){
                    stmt.setObject(param[i], paramVal[i]);
					log(param[i]+":"+TypeConvert.type2Str(paramVal[i]));
				}
			}
			rs = stmt.executeQuery();
			//利用结果集对象获得ResultSetMetadata对象，ResultSetMetadata是结果集的源数据，
			//在ResultSetMetadata中存放着查询结果的结果集的表结构
			ResultSetMetaData rsmt = rs.getMetaData();
			String[] colName=new String[rsmt.getColumnCount()];
			for(int i=1;i<=rsmt.getColumnCount();i++){
				colName[i-1]= rsmt.getColumnName(i);
			}
			//log("rs.getRow():"+rs.get+",rsmt.getColumnCount():"+rsmt.getColumnCount());
			//向resultSet填充值
			List<Object[]> resultSet=new ArrayList<Object[]>();
			//int row=0;
			while(rs.next()){
				Object[] rowObj=new Object[rsmt.getColumnCount()];
				for(int i=0;i<rsmt.getColumnCount();i++){
					rowObj[i]=rs.getObject(i+1);
					//resultSet[row][i]=rs.getObject(i+1);
			      }
				resultSet.add(rowObj);
			    }
			//Object[][] resultSet=new Object[row][rsmt.getColumnCount()];
			//Object[][]resultSet = resultSetList.toArray(new Object[row][rsmt.getColumnCount()]);
			ret.setColName(colName);
			ret.setResultSet(resultSet);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close( stmt, rs);
		}
		return ret;
	}
	public SqlResultSet retResultSetBySql(String sqlStmt,String[] param,Object[] paramVal) throws Exception{
		Connection conn=DBUtil.getConnection();
		try{
			return retResultSetBySql(conn,sqlStmt,param,paramVal);
		}catch(Exception e){
			throw e;
		}finally{
			DBUtil.close(conn);
		}
	}
	

	public SqlResultSet findByMinMax(Connection conn,String sqlStmt,String[] param,Object[] paramVal
			,Integer pageMinRow,Integer pageMaxRow) throws Exception{
		SqlResultSet ret=new SqlResultSet();
		if(sqlStmt==null||sqlStmt.trim().length()==0){
			log("sqlStmt is null!");
			return ret;
		}
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT /*+ FIRST_ROWS */ R.*  FROM (");
		sql.append(" SELECT ROWNUM RN,INNER_Q.* ");
		sql.append(" FROM (");
		sql.append(sqlStmt);
		sql.append(" ) INNER_Q ");
		sql.append(" WHERE ROWNUM <= :pageMaxRow ");
		sql.append(" ) R ");
		sql.append(" WHERE RN >= :pageMinRow");
		//重新构造参数，主要是多了2个最大最小的参数
		int paramLenM=0;
		if(param!=null&&param.length>0){
			paramLenM=param.length;
		}
		paramLenM=paramLenM+2;
		String[] paramM=new String[paramLenM];
		Object[] paramValM=new Object[paramLenM];
		if(param!=null&&param.length>0){
			for(int i=0;i<param.length;i++){
				paramM[i]=param[i];
				paramValM[i]=paramVal[i];
			}
		}
		paramM[paramLenM-2]="pageMaxRow";
		paramValM[paramLenM-2]=pageMaxRow;
		paramM[paramLenM-1]="pageMinRow";
		paramValM[paramLenM-1]=pageMinRow;
		//调用统一的方法返回数据结果
		ret=retResultSetBySql(conn,sql.toString(),paramM,paramValM);
		return ret;
	}
	public SqlResultSet findByMinMax(String sqlStmt,String[] param,Object[] paramVal
			,Integer pageMinRow,Integer pageMaxRow) throws Exception{
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			return findByMinMax(conn,sqlStmt,param,paramVal,pageMinRow,pageMaxRow);
		}catch(Exception e){
			throw e;
		}finally{
			DBUtil.close(conn);
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		/*String sqlStmt="select 222 from dual union all select 111 from dual";
		CommonDao dao = (CommonDao)Factory.getInstance("CommonDao");
		try {
			List<String> retList = dao.retListBySql(sqlStmt);
			for (int i=0;i<retList.size();i++){
				System.out.println("ret:"+retList.get(i));
			}
			System.out.println("ret:"+dao.retStrBySql(sqlStmt));
			System.out.println("ret seq::"+dao.retStrBySql("select XYG_JBO_CRM_EMP_NUM_S.nextval from dual"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//String sqlCol = "ROW_ID";
		//System.out.println(NameConvert.qCol2jField(sqlCol));

		String sqlStmt="select * from XYG_JBO_CRM_EMP_V";
		/*
		//模拟一种SQL查询的情况
		//假设界面一共有5个参数，只输入了n个参数
		String qEmpNumber=null;
		String qFullName=null;
		String qDepartmentId=null;
		String qHireDateF=null;//必输
		String qHireDateT=null;//必输
		//给参数
		qEmpNumber="";
		qFullName="";
		qHireDateF="2010-01-01 10:00:00";
		qHireDateT="2016-06-01";		
		//整合处理：
		//动态合成Where SQL
		StringBuffer sqlWhere=new StringBuffer(" WHERE 1=1 ");
		List<String> paraList=new ArrayList<String>();
		List<Object> paraValList=new ArrayList<Object>();
		if(qEmpNumber!=null&&qEmpNumber.trim().length()!=0){
			sqlWhere.append(" AND EMP_NUMBER=:1 ");
			paraList.add("1");
			paraValList.add(qEmpNumber);
		}
		if(qFullName!=null&&qFullName.trim().length()!=0){
			sqlWhere.append(" AND FULL_NAME LIKE :2 ");
			paraList.add("2");
			paraValList.add(qFullName);
		}
		if(qDepartmentId!=null&&qDepartmentId.trim().length()!=0){
			sqlWhere.append(" AND DEPARTMENT_ID = :3 ");
			paraList.add("3");
			paraValList.add(qDepartmentId);
		}
		sqlWhere.append(" AND HIRE_DATE >= :4 ");
		try{
			paraList.add("4");
			paraValList.add(TypeConvert.str2Timestamp(qHireDateF));
			sqlWhere.append(" AND HIRE_DATE <= :5 ");
			paraList.add("5");
			paraValList.add(TypeConvert.str2Timestamp(qHireDateT));
		}catch(Exception e){
			e.printStackTrace();
		}
		//完整的SQL语句：
		sqlStmt=sqlStmt+sqlWhere.toString()+" AND 1=1 "+" ORDER BY EMP_ID ";
		//转换参数
		String[] param = paraList.toArray(new String[paraList.size()]);
		Object[] paramVal = paraValList.toArray(new Object[paraValList.size()]);
		//直接给参数的例子：
		//String[] param=new String[2];
		//param[0]="1";
		//param[1]="2";
		//String[] paramVal={"6","631"};
		List<EmpVO> empVOList = new ArrayList<EmpVO>();
		List<UserVO> userVOList=new ArrayList<UserVO>();
		Connection conn = null;
		try {
			conn=DBUtil.getConnection();
			CommonDao dao = (CommonDao)Factory.getInstance("CommonDao");
			//empVOList=dao.retListBySql(sqlStmt, param,paramVal, EmpVO.class);
			//empVOList=dao.findByMinMax(conn,sqlStmt, param,paramVal, (int)21,(int)40,EmpVO.class);
			sqlStmt="select * from XYG_JBO_CRM_USER_V";
			userVOList=dao.retListBySql(conn,sqlStmt,null,null, UserVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		System.out.println("ret size:"+empVOList.size());
		for (int i=0;i<empVOList.size();i++){
			System.out.println(
					i+" empName:"+empVOList.get(i).getFullName()
					+",empId:"+empVOList.get(i).getEmpId()
					+",empNumber:"+empVOList.get(i).getEmpNumber()
					+",getHireDate:"+empVOList.get(i).getHireDate()
					+",getCreationDate:"+TypeConvert.type2Str(empVOList.get(i).getCreationDate())
					+",getRowId:"+TypeConvert.type2Str(empVOList.get(i).getRowId())
					);
		}

		for(int i=0;i<userVOList.size();i++){
			System.out.println(i+",USER_NAME:"+userVOList.get(i).getUserName()
					//+",ROW_ID:"+userVOList.get(i).getRowId().toString()
					);
		}
		*/
		
		//再测试用户查询的：
		/*
		sqlStmt="select * from XYG_JBO_CRM_USER_V";
		List<UserVO> userVOList=new ArrayList<UserVO>();
		try{
			CommonDao dao=(CommonDao)Factory.getInstance("CommonDao");
			userVOList=dao.retListBySql(sqlStmt, UserVO.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<userVOList.size();i++){
			System.out.println(i+",USER_NAME:"+userVOList.get(i).getUserName());
		}*/
		//再测试SqlResultSet的：
		sqlStmt="select USER_NAME,DESCRIPTION from XYG_JBO_CRM_USER_V ";
		SqlResultSet resultSet=new SqlResultSet();
		String[] param = new String[1];
		Object[] paramVal = new Object[1];
		param[0]="1";
		paramVal[0]="WX%";
		if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
			sqlStmt=sqlStmt+" WHERE USER_NAME LIKE :1";
		}else{
			sqlStmt=sqlStmt+" WHERE USER_NAME = :1";
		}
		//Connection conn = null;
		try{
		    //conn = DBUtil.getConnection();
			CommonDao dao=(CommonDao)Factory.getInstance("CommonDao");
			resultSet=dao.retResultSetBySql(sqlStmt, param, paramVal);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<resultSet.getResultSet().size();i++){
			for(int n=0;n<resultSet.getResultSet().get(i).length;n++){
				System.out.print(i+","+resultSet.getColName()[n]+":"+resultSet.getResultSet().get(i)[n]+"/");
			}
			System.out.println("");
		}

		//再测试DML语句的使用结果：
		/*
		sqlStmt="UPDATE XYG_JBO_CRM_EMP SET EMP_NUMBER = EMP_ID WHERE EMP_ID IN (:1,:2,:3) ";
		RetValue ret=new RetValue(0,"");
		String[] param=new String[3];
		param[0]="1";
		param[1]="2";
		param[2]="3";
		Object[] paramVal=new Object[3];
		paramVal[0]="634";
		paramVal[1]="635";
		paramVal[2]="636";
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			CommonDao dao=(CommonDao)Factory.getInstance("CommonDao");
			ret=dao.executeDML(conn,sqlStmt,param,paramVal);
			//conn.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		System.out.print(ret.getRetcode()+",BUF:"+ret.getErrbuf()+",UPDATE ROWS:"+ret.getParam1());
		*/
		//再测试调用PLSQL的
		/*
		sqlStmt="begin "
				+ "XYG_APDM_COMMON_PKG.HANDLE_RANDOM( "
				+ "null "
				+ ",'JAVA7--新年快乐！红包多多！' "
				+ ",null "
				+ ",:1 "//P_RANDOM_USER_ID          NUMBER
				+ ",:2 "//P_ENABLE_DATE             DATE
				+ ",:3 "//P_CURRENCY_CODE           VARCHAR2
				+ ",:4 "//P_RANDOM_AMOUNT           NUMBER
				+ ",:5 "//P_RANDOM_COUNT            NUMBER
				+ ",:"+Constant.RETCODE  //x_retcode              OUT NUMBER
				+ ",:"+Constant.ERRBUF  //x_errbuf               OUT VARCHAR2
				+ "); "
				+ "end ;";
		RetValue retPLSQL=new RetValue(0,"");
		String[] param=new String[5];
		param[0]="1";
		param[1]="2";
		param[2]="3";
		param[3]="4";
		param[4]="5";
		Object[] paramVal=new Object[5];
		paramVal[0]=(long)2;
		paramVal[1]=new Timestamp(System.currentTimeMillis());//日期输入的统一是Timestamp类型！
		paramVal[2]="CNY";
		paramVal[3]=(long)600;
		paramVal[4]=(long)10;
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			CommonDao dao=(CommonDao)Factory.getInstance("CommonDao");
			retPLSQL = dao.executePLSQL(conn,sqlStmt, param, paramVal);
			conn.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		System.out.print(retPLSQL.getRetcode()+",BUF:"+retPLSQL.getErrbuf()+",PARAM1:"+retPLSQL.getParam1());
		*/
	}

}
