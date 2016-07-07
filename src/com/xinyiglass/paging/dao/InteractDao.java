package com.xinyiglass.paging.dao;

import xygdev.commons.entity.RetValue;

public interface InteractDao {
	//����ͷID�Ҷ�Ӧ�Ľ���ʽ����Ķ��壬����json��ʽ������
	public String retJsonById(Long id) throws Exception;
	//�����û�ID�ͽ���ʽ����ID�����н���ʽ����Ķ��壬����Json��ʽ
	public String retJsonByUser(Long userId,String interfactCode) throws Exception;
	//���潻��ʽ����Ķ��壬�߼����������������򸲸ǣ�
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
