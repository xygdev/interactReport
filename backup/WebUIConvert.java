package com.xinyiglass.paging.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class WebUIConvert {
	public static String nullStr = "";
    //UI 专用，因为有时候空的值的话，界面返回的直接是字符：null，所以要改一下！
    public static String type2Str(String x){
    	return x==null?nullStr:x;
    }
    public static String type2Str(Timestamp x){
    	//new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(ts);用这个，00点居然会出现24！
    	return x==null?nullStr:x.toString();
    }
    public static String type2Str(Date x){
    	return x==null?nullStr:new SimpleDateFormat(Constant.DATE_FORMAT).format(x);
    }
    public static String type2Str(Long x){
    	return x==null?nullStr:x.toString();
    }
    public static String type2Str(Double x){
    	return x==null?nullStr:x.toString();
    }
    
}
