package com.xinyiglass.paging.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class WebUIConvert {
	public static String nullStr = "";
    //UI ר�ã���Ϊ��ʱ��յ�ֵ�Ļ������淵�ص�ֱ�����ַ���null������Ҫ��һ�£�
    public static String type2Str(String x){
    	return x==null?nullStr:x;
    }
    public static String type2Str(Timestamp x){
    	//new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(ts);�������00���Ȼ�����24��
    	String ret = null;
    	if(x==null){
    		ret=nullStr;
    	}else{
    		ret=x.toString();
    		ret=ret.substring(0,ret.length()-2);
    	}
    	return ret;
    }
    public static String type2Str(java.sql.Date x){
    	return x==null?nullStr:new SimpleDateFormat(Constant.DATE_FORMAT).format(x);
    }
    public static String type2Str(java.util.Date x){
    	return x==null?nullStr:new SimpleDateFormat(Constant.DATETIME_FORMAT).format(x);
    }
    public static String type2Str(Long x){
    	return x==null?nullStr:x.toString();
    }
    public static String type2Str(Double x){
    	return x==null?nullStr:x.toString();
    }
    
}
