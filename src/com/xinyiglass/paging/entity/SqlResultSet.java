package com.xinyiglass.paging.entity;

import java.util.List;

public class SqlResultSet {
	private List<Object[]> resultSet;//改为List，是为了让行数可以动态扩展。
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
