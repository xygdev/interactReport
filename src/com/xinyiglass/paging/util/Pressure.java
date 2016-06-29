package com.xinyiglass.paging.util;

import java.io.BufferedReader;   
import java.io.File;   
import java.io.FileInputStream;   
import java.io.InputStreamReader;   
import java.io.PrintWriter;   
import java.net.HttpURLConnection;   
import java.net.URL;   
import java.util.HashMap;   
import java.util.Map;   
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
import java.util.concurrent.Semaphore;   
 
public class Pressure {
	private static int thread_num = 50;   
	private static int client_num = 500;   
	private static Map keywordMap = new HashMap();   
	static {   
		try {   
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File("clicks.txt")), "GBK");   
			BufferedReader buffer = new BufferedReader(isr);   
			String line = "";   
			while ((line = buffer.readLine()) != null) {   
				keywordMap.put(line.substring(0, line.lastIndexOf(":")), "");   
			}   
		} catch (Exception e) {   
			e.printStackTrace();   
		}   
	} 
	public static void main(String[] args) {   
		int size = keywordMap.size();   
		// TODO Auto-generated method stub   
		ExecutorService exec = Executors.newCachedThreadPool();   
		// 50���߳̿���ͬʱ����   
		final Semaphore semp = new Semaphore(thread_num);   
		// ģ��2000���ͻ��˷���   
		for(int index=0;index<client_num;index++) {   
			final int NO = index;   
			Runnable run = new Runnable() {   
			public void run() {   
				try {   
				    // ��ȡ����  
					semp.acquire();   
					System.out.println("Thread:" + NO);   
					String host = "http://192.168.88.41:8080/table/Page.do?";   
					String para = "pageSize=10&goLastPage=false&"   
						+ "pageNo="   
						+ NO;   
						//+ "&questionID=-1&questionIdPath=-1&searchType=1"   
						//+ "&proLine=&proSeries=&proType=" + NO;   
					System.out.println(host + para);   
					URL url = new URL(host);// �˴���д�����Ե�url   
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();   
					// connection.setRequestMethod("POST");   
					// connection.setRequestProperty("Proxy-Connection",   
					// "Keep-Alive");   
					connection.setDoOutput(true);   
					connection.setDoInput(true);
					PrintWriter out = new PrintWriter(connection.getOutputStream());   
					out.print(para);
					out.flush();   
					out.close();   
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line = "";   
					String result = "";   
					while ((line = in.readLine()) != null) {   
						result += line;   
					}   
					//System.out.println(result);   
					//Thread.sleep((long) (Math.random()) * 1000);   
					// �ͷ�   
					System.out.println("�ڣ�" + NO + " ��");   
					semp.release();   
					} catch (Exception e) {   
						e.printStackTrace();   
					}   
				}   
			};   
			exec.execute(run);   
		}   
		// �˳��̳߳�   
		exec.shutdown();   
	}   
	private static String getRandomSearchKey(final int no) {   
		String ret = "";   
		int size = keywordMap.size();   
		// int wanna = (int) (Math.random()) * (size - 1);   
		ret = (keywordMap.entrySet().toArray())[no].toString();   
		ret = ret.substring(0, ret.lastIndexOf("="));   
		System.out.println("\t" + ret);   
		return ret;   
	}    
}