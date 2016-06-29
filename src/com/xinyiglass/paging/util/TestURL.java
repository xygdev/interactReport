package com.xinyiglass.paging.util;

import java.io.InputStream;
import java.net.URL;

public class TestURL {
    public static void main(String[] args) throws Exception{
    	long a = System.currentTimeMillis();
    	System.out.println("Starting request url:");
    	InputStream is=null;
    		for(int i=0;i<3000;i++){
    			URL url = new URL("http://192.168.88.41:8080/table/list.do?");
    			is= url.openStream();
    			System.out.println("Starting request url:"+i);
    		}
    		System.out.println("request url end.take"+(System.currentTimeMillis()-a)+"ms");
    		is.close();
    }
}
