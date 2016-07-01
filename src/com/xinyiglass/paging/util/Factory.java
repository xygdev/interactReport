package com.xinyiglass.paging.util;

import xygdev.commons.dao.impl.OracleDaoImpl;

import com.xinyiglass.paging.dao.impl.*;

public class Factory {
	public static Object getInstance(String type){
		Object obj = null;
		if ("EmpVODao".equals(type)){
			obj=new EmpVODaoImpl();
			/** obj=new EmployeeDAOHibernateImpl();������ʹ��JDBC�������ݿ�ʱ��
			 * ��HibernateI����ʱ������Ҫ����ÿ��new EmployeeDAOJdbcImpl();������ά����
			 * ʹ�ù���ģʽ�Ϳ���ͳһ������ */	
		}else if("OracleDao".equals(type)){
			obj=new OracleDaoImpl();
		}else if("InteractDao".equals(type)){
			obj=new InteractDaoImpl();
		}else{
			throw new RuntimeException("ʵ����type:"+type+" ��֧�֣�");
		}
		return obj;
	}
}
