package com.xinyiglass.paging.entity;

import java.util.List;

public class SqlResultSet {
	private List<Object[]> resultSet;//��ΪList����Ϊ�����������Զ�̬��չ��
	private String[] colName;

	public List<Object[]> getResultSet() {
		return resultSet;
	}
	public void setResultSet(List<Object[]> resultSet) {
		this.resultSet = resultSet;
	}
	
	public String[] getColName() {
		return colName;
	}
	public void setColName(String[] colName) {
		this.colName = colName;
	}
}
