package com.xinyiglass.paging.dao.impl;

import java.sql.Connection;

import xygdev.commons.dao.OracleDao;
import xygdev.commons.entity.RetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.util.Constant;
import xygdev.commons.util.DBUtil;

import com.xinyiglass.paging.dao.InteractDao;
import com.xinyiglass.paging.util.Factory;

public class InteractDaoImpl implements InteractDao {
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	public String retJsonById(Long id) throws Exception{
		StringBuffer sb = new StringBuffer();
		OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
		String sqlStmtH;
		String sqlStmtL;
		sqlStmtH="SELECT HEADER_ID "
				+ " ,INTERACT_CODE "
				+ " ,USER_INTERACT_NAME "
				+ ",DESCRIPTION "
				+ " ,PUBLIC_FLAG "
				+ " ,AUTOQUERY_FLAG "
				+ ",DEFAULT_FLAG "
				+ ",ORDER_BY "
				+ ",PAGE_SIZE "
				+ " FROM XYG_ALD_INTERACT_HEADERS "
				+ "  WHERE 1=1 "
				+ " AND HEADER_ID = :1";
		sqlStmtL="SELECT LINE_SEQ,COLUMN_NAME"
				+ " FROM XYG_ALD_INTERACT_LINES "
				+ " WHERE HEADER_ID =:1 "
				+ " ORDER BY LINE_SEQ";
		String[] param = new String[1];
		Object[] paramVal = new Object[1];
		param[0] = "1";
		paramVal[0] = id;
		SqlResultSet resultSetH=new SqlResultSet();
		SqlResultSet resultSetL=new SqlResultSet();
		try{
			resultSetH=dao.retResultSetBySql(sqlStmtH,param,paramVal);
		}catch(Exception e){
			e.printStackTrace();
		}
	    sb.append("{"); 
	    if(resultSetH.getResultSet().size()>0){//存在数据
		    sb.append("\"EXISTS\": \"Y\",\"jsonRoot\":"); 
		    for(int i=0;i<resultSetH.getResultSet().size();i++){
		    	for(int n=0;n<resultSetH.getResultSet().get(i).length;n++){
		    		if(n==0){
		    			sb.append("{");
		    		}
		    		sb.append("\""+resultSetH.getColName()[n]+"\":\"" + resultSetH.getResultSet().get(i)[n]); 
			    	sb.append("\",");  	
			    	if(n==(resultSetH.getResultSet().get(i).length-1)){
			    		sb.deleteCharAt(sb.lastIndexOf(","));
			    		//sb.append("},");
			    	}		
		    	}
		    }
	    	//行：
			try{
				resultSetL=dao.retResultSetBySql(sqlStmtL,param,paramVal);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(",\"SEQ\":["); 
		    for(int i=0;i<resultSetL.getResultSet().size();i++){
		    	for(int n=0;n<resultSetL.getResultSet().get(i).length;n++){
		    		if(n==0){
		    			sb.append("{");
		    		}
		    		sb.append("\""+resultSetL.getColName()[n]+"\":\"" + resultSetL.getResultSet().get(i)[n]); 
			    	sb.append("\",");  	
			    	if(n==(resultSetL.getResultSet().get(i).length-1)){
			    		sb.deleteCharAt(sb.lastIndexOf(","));
			    		sb.append("},");
			    	}		    		
		    	}
		    }
		    sb.deleteCharAt(sb.lastIndexOf(","));
		    sb.append("]}");
	    }else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"jsonRoot\":{}"); 
	    }
	    sb.append("}");
	    return sb.toString();
	}

	public String retJsonByUser(Long userId,String interfactCode) throws Exception{
		StringBuffer sb = new StringBuffer();
		OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
		String sqlStmt;
		//根据用户和报表的名称获取该用户所有可以打开的文件夹
		sqlStmt="SELECT HEADER_ID,USER_INTERACT_NAME "
				+ " FROM XYG_ALD_INTERACT_HEADERS "
				+ "  WHERE 1=1 "
				+ " AND INTERACT_CODE =:1  "
				+ " AND (USER_ID = :2 OR PUBLIC_FLAG = 'Y') "
				+ " ORDER BY DECODE(USER_ID,:3,0,1),USER_INTERACT_NAME ";
		String[] param = new String[3];
		Object[] paramVal = new Object[3];
		param[0] = "1";
		param[1] = "2";
		param[2] = "3";
		paramVal[0] = interfactCode;		
		paramVal[1] = userId;
		paramVal[2] = userId;
		SqlResultSet resultSet=new SqlResultSet();
		try{
			resultSet=dao.retResultSetBySql(sqlStmt,param,paramVal);
		}catch(Exception e){
			e.printStackTrace();
		}
		log("sql result:"+resultSet.getResultSet().size());
	    if(resultSet.getResultSet().size()>0){//存在数据
		    sb.append("{\"EXISTS\": \"Y\",\"jsonRoot\":["); 
		    for(int i=0;i<resultSet.getResultSet().size();i++){
		    	for(int n=0;n<resultSet.getResultSet().get(i).length;n++){
		    		if(n==0){
		    			sb.append("{");
		    		}
		    		sb.append("\""+resultSet.getColName()[n]+"\":\"" + resultSet.getResultSet().get(i)[n]); 
			    	sb.append("\",");  	
			    	if(n==(resultSet.getResultSet().get(i).length-1)){
			    		sb.deleteCharAt(sb.lastIndexOf(","));
			    		sb.append("},");
			    	}		
		    	}
		    }
		    sb.deleteCharAt(sb.lastIndexOf(","));
		    sb.append("]}");
	    }else{//不存在数据
		    sb.append("{\"EXISTS\": \"N\",\"jsonRoot\":{}}"); 
	    }
	    return sb.toString();
	}
	
	public RetValue saveInteract(
			 Long userId
			,String interactCode
			,String userInteractName
			,String description
			,String publicFlag
			,String autoqueryFlag
			,String defaultFlag
			,String orderBy
			,int pageSize
			,String seq
			) throws Exception{
		String sqlStmt=null;
		sqlStmt ="begin "
				+ "XYG_ALD_INTERACT_PKG.HANDLE_INTERACT( "
				+ ":1 "//P_USER_ID              NUMBER
				+ ",:2 "//P_INTERACT_CODE        VARCHAR2
				+ ",:3 "//P_USER_INTERACT_NAME   VARCHAR2
				+ ",:4 "//P_DESCRIPTION          VARCHAR2
				+ ",:5 "//P_PUBLIC_FLAG          VARCHAR2
				+ ",:6 "//P_AUTOQUERY_FLAG       VARCHAR2
				+ ",:7 "//P_DEFAULT_FLAG         VARCHAR2
				+ ",:8 "//P_ORDER_BY             VARCHAR2
				+ ",:9 "//P_Page_Size            NUMBER
				+ ",:10 "//P_LANGUAGE             VARCHAR2
				+ ",:11 "//P_INTERACT_LINES       VARCHAR2
				+ ",:"+Constant.PARAM1  //X_HEADER_ID            OUT NUMBER
				+ ",:"+Constant.RETCODE  //x_retcode              OUT NUMBER
				+ ",:"+Constant.ERRBUF  //x_errbuf               OUT VARCHAR2
				+ "); "
				+ "end ;";
		RetValue retPLSQL=new RetValue(0,"成功Save");
		String[] param=new String[11];
		param[0]="1";
		param[1]="2";
		param[2]="3";
		param[3]="4";
		param[4]="5";
		param[5]="6";
		param[6]="7";
		param[7]="8";
		param[8]="9";
		param[9]="10";
		param[10]="11";
		Object[] paramVal=new Object[11];
		paramVal[0]=userId;
		paramVal[1]=interactCode;
		paramVal[2]=userInteractName;
		paramVal[3]=description;
		paramVal[4]=publicFlag;
		paramVal[5]=autoqueryFlag;
		paramVal[6]=defaultFlag;
		paramVal[7]=orderBy;
		paramVal[8]=pageSize;
		paramVal[9]="ZHS";
		paramVal[10]=seq;
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
			retPLSQL = dao.executePLSQL(conn,sqlStmt, param, paramVal);
			if(!(retPLSQL.getRetcode()==0)){
				conn.rollback();
			}else{
				conn.commit();
			}
		}catch(Exception e){
			conn.rollback();//有异常先回滚
			retPLSQL.setRetcode(2);
			retPLSQL.setErrbuf(e.getMessage());
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return retPLSQL;
	}
}
