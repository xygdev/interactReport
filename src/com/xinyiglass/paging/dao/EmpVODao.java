package com.xinyiglass.paging.dao;

import java.util.List;

import com.xinyiglass.paging.entity.EmpVO;

public interface EmpVODao {
	public void insert(EmpVO e) throws Exception;
	public void delete(Long empId) throws Exception;
	public void lock(EmpVO e) throws Exception;
	public void update(EmpVO e) throws Exception;
	public List<EmpVO> findAll(int begin,int end,String orderby) throws Exception;
	public int countAll() throws Exception;
	public EmpVO findById(Long empId) throws Exception;
	public List<EmpVO> findByCondition(String condition,List<Integer> para,List<String> paraVal) throws Exception;
	public StringBuffer findBySQL(String sql,int pagesize,int output,int pagemax,int pagemin) throws Exception;
}
