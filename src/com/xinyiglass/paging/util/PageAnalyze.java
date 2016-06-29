package com.xinyiglass.paging.util;


public class PageAnalyze {
	//获取起始数据行
    public static int getPageMinRow(int pageNo,int pageSize){
    	int pageMinRow=0;
    	pageMinRow=(pageNo-1)*pageSize+1;    	
    	return pageMinRow;
    }
    
    //当页面不为最后一页时，获取结束数据行
    public static int getPageMaxRow(int pageNo,int pageSize){
    	int pageMaxRow=0;
    	pageMaxRow=pageNo*pageSize;
    	return pageMaxRow;
    }
    
    //当页面为最后一页时，获取结束数据行
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
    
    //获取总页数
    public static int getTotalPages(int totalRecs,int pageSize){
    	int totalPages=0;
    	totalPages=(int)Math.ceil((double)totalRecs/(double)pageSize);
    	return totalPages;
    }
    
    //是否第一页，判断标志
    public static boolean getFirstPageFlag(int pageNo){
    	boolean firstPageFlag=false;
    	if(pageNo==1){
    		firstPageFlag=true;
    	}
    	return firstPageFlag;
    }
    
    //是否最后一页，判断标志
    public static boolean getLastPageFlag(int pageNo,int pageSize,double totalPages,int recsSize){
    	boolean lastPageFlag=false;
    	if(pageSize!=recsSize||totalPages==pageNo){
    		lastPageFlag=true;
    	}
    	return lastPageFlag;
    }   
    
}
