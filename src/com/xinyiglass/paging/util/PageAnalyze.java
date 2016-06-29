package com.xinyiglass.paging.util;


public class PageAnalyze {
	//��ȡ��ʼ������
    public static int getPageMinRow(int pageNo,int pageSize){
    	int pageMinRow=0;
    	pageMinRow=(pageNo-1)*pageSize+1;    	
    	return pageMinRow;
    }
    
    //��ҳ�治Ϊ���һҳʱ����ȡ����������
    public static int getPageMaxRow(int pageNo,int pageSize){
    	int pageMaxRow=0;
    	pageMaxRow=pageNo*pageSize;
    	return pageMaxRow;
    }
    
    //��ҳ��Ϊ���һҳʱ����ȡ����������
    public static int getLastPageMaxRow(int totalPages,int pageSize,int totalRecs){
    	int pageMaxRow=0;
    	int restRecs=totalRecs%pageSize;
    	if(restRecs!=0){
    		pageMaxRow=(totalPages-1)*pageSize+restRecs;   		
    	}
    	else{
    		pageMaxRow=totalPages*pageSize;
    	}
    	return pageMaxRow;
    }
    
    //��ȡ��ҳ��
    public static int getTotalPages(int totalRecs,int pageSize){
    	int totalPages=0;
    	totalPages=(int)Math.ceil((double)totalRecs/(double)pageSize);
    	return totalPages;
    }
    
    //�Ƿ��һҳ���жϱ�־
    public static boolean getFirstPageFlag(int pageNo){
    	boolean firstPageFlag=false;
    	if(pageNo==1){
    		firstPageFlag=true;
    	}
    	return firstPageFlag;
    }
    
    //�Ƿ����һҳ���жϱ�־
    public static boolean getLastPageFlag(int pageNo,int pageSize,double totalPages,int recsSize){
    	boolean lastPageFlag=false;
    	if(pageSize!=recsSize||totalPages==pageNo){
    		lastPageFlag=true;
    	}
    	return lastPageFlag;
    }   
    
}
