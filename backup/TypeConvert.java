package com.xinyiglass.paging.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class TypeConvert {
    //别的数据类型转字符
	public static String type2Str(Long x){
		return x==null?null:x.toString();
	}
    public static String type2Str(Double x){
		return x==null?null:x.toString();
	}
    public static String type2Str(Timestamp x){
    	//new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(ts);用这个，00点居然会出现24！
    	return x==null?null:x.toString();
    }
    public static String type2Str(java.sql.Date x){
    	return x==null?null:new SimpleDateFormat(Constant.DATE_FORMAT).format(x);
    }
    public static String type2Str(java.util.Date x){
    	return x==null?null:new SimpleDateFormat(Constant.DATETIME_FORMAT).format(x);
    }
    public static String type2Str(java.util.Date x,String f){
    	return x==null?null:new SimpleDateFormat(f).format(x);
    }
    
    //字符转别的数据类型
    public static Long str2Long(String x){
		return !(x!=null &&x.trim().length()>0)?null:Long.parseLong(x);
	}
    public static Double str2Double(String x){
		return !(x!=null &&x.trim().length()>0)?null:Double.parseDouble(x);
	}
    public static Timestamp str2Timestamp(String x){
    	//new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(ts);用这个，00点居然会出现24！
    	 Timestamp ret = null;
    	 String fmt = Constant.DATETIME_FORMAT;
    	 //兼容yyyy-MM-dd的格式！
    	 try {
    		if (x.length()>=8&&x.length()<=10){
    			fmt = Constant.DATE_FORMAT;
    		}
    		ret=!(x!=null &&x.trim().length()>0)?null:
    			new Timestamp(new SimpleDateFormat(fmt).parse(x).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
    }
    public static Date str2Date(String x){
    	Date ret = null;
    	try {
    		ret =  !(x!=null &&x.trim().length()>0)?null:
    			new Date(new SimpleDateFormat(Constant.DATE_FORMAT).parse(x).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
    }
    
    //改为util date
    public static java.util.Date str2uDate(String x) throws ParseException{
    	java.util.Date ret = null;
   	 	String fmt = Constant.DATETIME_FORMAT;
   	 //兼容yyyy-MM-dd的格式！
		if (x.length()>=8&&x.length()<=10){
			fmt = Constant.DATE_FORMAT;
		}
		ret =  !(x!=null &&x.trim().length()>0)?null:
			new java.util.Date(new SimpleDateFormat(fmt).parse(x).getTime());
		return ret;
    }
    
  //改为sql date
    public static java.sql.Date str2sDate(String x) throws ParseException{
    	Date ret = null;
		ret =  !(x!=null &&x.trim().length()>0)?null:
			new Date(new SimpleDateFormat(Constant.DATE_FORMAT).parse(x).getTime());
		return ret;
    }
    
    public static void main(String[] args){
    	Double d=null;
    	System.out.println(d);
    	String dt = "2015-1-1";
    	//System.out.println(str2Date(dt));
    	System.out.println(str2Timestamp(dt));
    	System.out.println(type2Str(str2Timestamp(dt)));
    	java.util.Date uDate = new java.util.Date();
    	System.out.println(type2Str(uDate,"yyMMddkkmmss"));
    	
    }
}
