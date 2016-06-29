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
    	return x==null?null:new SimpleDateFormat(Constant.DATETIME_FORMAT).format(x);
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
    public static String type2Str(Object x){
    	return x==null?null:x.toString();
    }
    
    //字符转别的数据类型
    public static Long str2Long(String x){
		return !(x!=null &&x.trim().length()>0)?null:Long.parseLong(x);
	}
    public static Double str2Double(String x){
		return !(x!=null &&x.trim().length()>0)?null:Double.parseDouble(x);
	}
    public static Timestamp str2Timestamp(String x) throws ParseException{
    	//new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(ts);用这个，00点居然会出现24！
    	 Timestamp ret = null;
    	 String fmt = Constant.DATETIME_FORMAT;
    	 //兼容yyyy-MM-dd的格式！
		if (x.length()>=8&&x.length()<=10){
			fmt = Constant.DATE_FORMAT;
		}
		ret=!(x!=null &&x.trim().length()>0)?null:
			new Timestamp(new SimpleDateFormat(fmt).parse(x).getTime());
		return ret;
    }
    //改为sql date
    public static java.sql.Date str2sDate(String x) throws ParseException{
    	Date ret = null;
		ret =  !(x!=null &&x.trim().length()>0)?null:
			new Date(new SimpleDateFormat(Constant.DATE_FORMAT).parse(x).getTime());
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
    
    //sqlDate(sql的只有日期) 转 utilDate(日期+时间的)
    public static java.util.Date q2uDate(java.sql.Date qDate){
    	return qDate==null?null:new java.util.Date(qDate.getTime());
    }
    public static java.sql.Date u2qDate(java.util.Date uDate){
    	return uDate==null?null:new java.sql.Date(uDate.getTime());
    }
    
    //sqlTimestamp(sql的日期+时分秒) 转 utilDate(日期+时间的)
    public static java.util.Date t2uDate(Timestamp tDate){
    	return tDate==null?null:new java.util.Date(tDate.getTime());
    }
    public static Timestamp u2tDate(java.util.Date uDate){
    	return uDate==null?null:new Timestamp(uDate.getTime());
    }
    
    public static void main(String[] args){
    	Double d=null;
    	System.out.println(d);
    	String dt = "2015-1-1 23:00:00";
    	try{
        	System.out.println("str2uDate:"+type2Str(str2uDate(dt)));
        	System.out.println("u2tDate:"+u2tDate(str2uDate(dt)));
        	System.out.println("str2Timestamp:"+str2Timestamp(dt));
        	System.out.println("t2uDate:"+t2uDate(str2Timestamp(dt)));
        	System.out.println("type2Str:"+type2Str(str2Timestamp(dt)));
        	
        	//System.out.println(type2Str(str2Timestamp(dt)));
        	java.util.Date uDate = new java.util.Date();
        	System.out.println(type2Str(uDate,"yyMMddkkmmss"));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
}
