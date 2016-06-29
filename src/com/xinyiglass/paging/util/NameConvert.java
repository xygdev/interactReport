package com.xinyiglass.paging.util;

public class NameConvert {
	public static String qCol2jField(String sqlCol){
		StringBuffer jField = new StringBuffer();
		//System.out.println(sqlCol.toLowerCase());
		String[] strList = sqlCol.split("_");
		for (int i=0;i<strList.length;i++){
			//System.out.println(strList[i]);
			if (i==0){//第一个要小写
				jField.append(strList[i].toLowerCase());
			}else{//别的都要首字母大写
				jField.append(Initcap(strList[i]));
			}
		}
		return jField.toString();
	}
	
	public static String Initcap(String x){
		x=x.toLowerCase();
		return x.substring(0,1).toUpperCase()+x.substring(1);
	}
}
