package com.xinyiglass.paging.dao;

import xygdev.commons.entity.RetValue;

public interface InteractDao {
	//根据头ID找对应的交互式报表的定义，返回json格式的数据
	public String retJsonById(Long id) throws Exception;
	//根据用户ID和交互式报表ID找所有交互式报表的定义，返回Json格式
	public String retJsonByUser(Long userId,String interfactCode) throws Exception;
	//保存交互式报表的定义，逻辑：无则新增，有则覆盖！
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
			) throws Exception;
}
