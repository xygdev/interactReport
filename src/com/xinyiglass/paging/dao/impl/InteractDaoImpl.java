package com.xinyiglass.paging.dao.impl;

import xygdev.commons.dao.OracleDao;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.paging.dao.InteractDao;
import com.xinyiglass.paging.util.Factory;

public class InteractDaoImpl implements InteractDao {
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
}
