package com.xinyiglass.paging.dao;

import xygdev.commons.entity.RetValue;

public interface InteractDao {
	public String retJsonById(Long id) throws Exception;
	//保存交互式报表的定义，逻辑：无则新增，有则覆盖！
	public RetValue saveInteract(
			 Long user_id
			,String interact_code
			,String user_interact_name
			,String description
			,String public_flag
			,String autoquery_flag
			,String default_flag
			,String order_by
			,int page_size
			,String seq
			) throws Exception;
}
