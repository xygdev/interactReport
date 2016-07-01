package com.xinyiglass.paging.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinyiglass.paging.dao.EmpVODao;
import com.xinyiglass.paging.dao.InteractDao;
import com.xinyiglass.paging.entity.EmpVO;

import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.util.DBUtil;

import com.xinyiglass.paging.util.Constant;
import com.xinyiglass.paging.util.Factory;

import xygdev.commons.convert.TypeConvert;
import xygdev.commons.convert.WebUIConvert;
import xygdev.commons.dao.OracleDao;

import com.xinyiglass.paging.util.PageAnalyze;

//整合所有的Servlet处理
@SuppressWarnings("serial")
public class ActionServlet extends HttpServlet {
	
	public EmpVO ebk = null;//为了在更新的时候，验证是否界面数据有变化了！注意，这里的是全局的！
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	public void service(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		int pageSize=0;
		int pageNo=0;
		boolean goLastPage;
		int pageMinRow=0;
		int pageMaxRow=0;
		int recsSize=0;
		int totalRecs=0;
		int totalPages=0;
		boolean firstPageFlag;
		boolean lastPageFlag;
		String orderBy;
		StringBuffer sb = new StringBuffer();
		HttpSession sess = req.getSession();
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");  
		PrintWriter out=res.getWriter();
		String uri=req.getRequestURI();
		//截取从"/"开始到"."结束，不包括"."的字符串
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		log("action:"+action);
		//暂时没有想好在哪里。以后肯定是不可以加在这里的！仅仅测试用！2016.6.30
		DBUtil.url = "jdbc:oracle:thin:@//192.168.0.26:1521/PDB_APEX";
		DBUtil.user = "APEX_UAT";
		DBUtil.password = "apex_uat";
		if (action.equals("/list")){
			RequestDispatcher rd=req.getRequestDispatcher("ListEmpVO.jsp");
			rd.forward(req, res);
		}
		else if(action.equals("/page")){
			pageSize=Integer.parseInt(req.getParameter("pageSize"));
			pageNo=Integer.parseInt(req.getParameter("pageNo"));
			goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
			orderBy=req.getParameter("orderby");
			System.out.println("orderBy="+orderBy);
			EmpVODao dao = (EmpVODao)Factory.getInstance("EmpVODao");
			List<EmpVO> empVOList = new ArrayList<EmpVO>();
			firstPageFlag=PageAnalyze.getFirstPageFlag(pageNo);
			System.out.println(firstPageFlag);
			if(goLastPage==false){
				pageMinRow=PageAnalyze.getPageMinRow(pageNo, pageSize);
				pageMaxRow=PageAnalyze.getPageMaxRow(pageNo, pageSize);	
				try {
					empVOList = dao.findAll(pageMinRow,pageMaxRow,orderBy);
				} catch (Exception e) {
					e.printStackTrace();
				}
				recsSize=empVOList.size();	
			    lastPageFlag=PageAnalyze.getLastPageFlag(pageNo, pageSize, totalPages, recsSize);
			    if(recsSize>0){
				    pageMaxRow=pageMinRow+recsSize-1;
			    }else{
			    	pageMinRow=0;
			    	pageMaxRow=0;
			    }
				sb.append("{\"jsonRoot\":["); 
			    for (int i=0;i<empVOList.size();i++) {
			    	EmpVO e = empVOList.get(i);
			    	sb.append("{\"id\":\"" + e.getEmpId()); 
			    	sb.append("\",");  
			    	sb.append("\"eno\":\"" + WebUIConvert.type2Str(e.getEmpNumber())); 
			    	sb.append("\",");  
			    	sb.append("\"name\":\"" + e.getFullName()); 
			    	sb.append("\",");  
			    	sb.append("\"sex\":\"" + e.getSexDesc()); 
			    	sb.append("\",");  
			    	sb.append("\"pno\":\"" + WebUIConvert.type2Str(e.getPhoneNumber())); 
			    	sb.append("\",");  
			    	sb.append("\"sal\":\"" + WebUIConvert.type2Str(e.getSalary())); 
			    	sb.append("\",");  
			    	sb.append("\"hdate\":\"" + WebUIConvert.type2Str(e.getHireDate())); 
			    	sb.append("\",");  
			    	sb.append("\"job\":\"" + WebUIConvert.type2Str(e.getJobName())); 
			    	sb.append("\",");  
			    	sb.append("\"dept\":\"" + WebUIConvert.type2Str(e.getDeptName()));
			    	sb.append("\"");  
		            sb.append("},");
			    }
			    if (recsSize>0){
				    sb.deleteCharAt(sb.lastIndexOf(","));  // 删去最后一个逗号  
			    }
			    sb.append("],\"pageMinRow\":\""+pageMinRow);
			    sb.append("\",\"pageMaxRow\":\""+pageMaxRow);
			    sb.append("\",\"firstPageFlag\":\""+firstPageFlag);
			    sb.append("\",\"lastPageFlag\":\""+lastPageFlag);
			    sb.append("\",\"totalPages\":\""+totalPages);
			    sb.append("\"}");
		        log(sb.toString());
		        res.getWriter().print(sb);  
			}
			else{
				try {
					totalRecs=dao.countAll();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				totalPages=PageAnalyze.getTotalPages(totalRecs, pageSize);			
				pageMinRow=PageAnalyze.getPageMinRow(totalPages, pageSize);
				pageMaxRow=PageAnalyze.getLastPageMaxRow(totalPages, pageSize, totalRecs);
				System.out.println("totalPages:"+totalPages);
				System.out.println("min:"+pageMinRow);
				System.out.println("max:"+pageMaxRow);
				lastPageFlag=PageAnalyze.getLastPageFlag(pageNo, pageSize, totalPages, recsSize);
				try {
					empVOList = dao.findAll(pageMinRow,pageMaxRow,orderBy);
				} catch (Exception e) {
					e.printStackTrace();
				}
				sb.append("{\"jsonRoot\":["); 
			    for (int i=0;i<empVOList.size();i++) {
			    	EmpVO e = empVOList.get(i);
			    	sb.append("{\"id\":\"" + e.getEmpId()); 
			    	sb.append("\",");  
			    	sb.append("\"eno\":\"" + WebUIConvert.type2Str(e.getEmpNumber())); 
			    	sb.append("\",");  
			    	sb.append("\"name\":\"" + e.getFullName()); 
			    	sb.append("\",");  
			    	sb.append("\"sex\":\"" + e.getSexDesc()); 
			    	sb.append("\",");  
			    	sb.append("\"pno\":\"" + WebUIConvert.type2Str(e.getPhoneNumber())); 
			    	sb.append("\",");  
			    	sb.append("\"sal\":\"" + WebUIConvert.type2Str(e.getSalary())); 
			    	sb.append("\",");  
			    	sb.append("\"hdate\":\"" + WebUIConvert.type2Str(e.getHireDate())); 
			    	sb.append("\",");  
			    	sb.append("\"job\":\"" + WebUIConvert.type2Str(e.getJobName())); 
			    	sb.append("\",");  
			    	sb.append("\"dept\":\"" + WebUIConvert.type2Str(e.getDeptName()));
			    	sb.append("\"");  
		            sb.append("},");
			    }
			    sb.deleteCharAt(sb.lastIndexOf(","));  // 删去最后一个逗号  
			    sb.append("],\"pageMinRow\":\""+pageMinRow);
			    sb.append("\",\"pageMaxRow\":\""+pageMaxRow);
			    sb.append("\",\"firstPageFlag\":\""+firstPageFlag);
			    sb.append("\",\"lastPageFlag\":\""+lastPageFlag);
			    sb.append("\",\"totalPages\":\""+totalPages);
			    sb.append("\"}");
		        log(sb.toString());
		        res.getWriter().print(sb);  
			}
		}
		else if(action.equals("/delete")){
			pageSize=Integer.parseInt(req.getParameter("pageSize"));
			pageNo=Integer.parseInt(req.getParameter("pageNo"));
			orderBy="emp_id";
			EmpVODao dao = (EmpVODao) Factory.getInstance("EmpVODao");
			Long empId = Long.parseLong(req.getParameter("id"));
			try{				
				dao.delete(empId);
			}catch(Exception e){
				e.printStackTrace();
				log("删除失败!请稍后重试!");
			}
			 sb.append("{\"jsonRoot\":\"success\"}"); 
			/*
			pageMinRow=PageAnalyze.getPageMinRow(pageNo, pageSize);
			pageMaxRow=PageAnalyze.getPageMaxRow(pageNo, pageSize);	
			try {
				empVOList = dao.findAll(pageMinRow,pageMaxRow,orderBy);
			} catch (Exception e) {
				e.printStackTrace();
			}
			firstPageFlag=PageAnalyze.getFirstPageFlag(pageNo);
			recsSize=empVOList.size();	
		    lastPageFlag=PageAnalyze.getLastPageFlag(pageNo, pageSize, totalPages, recsSize);
		    if(recsSize>0){
			    pageMaxRow=pageMinRow+recsSize-1;
		    }else{
		    	pageMinRow=0;
		    	pageMaxRow=0;
		    }
		    sb.append("{\"jsonRoot\":["); 
		    for (int i=0;i<empVOList.size();i++) {
		    	EmpVO e = empVOList.get(i);
		    	sb.append("{\"id\":\"" + e.getEmpId()); 
		    	sb.append("\",");  
		    	sb.append("\"eno\":\"" + WebUIConvert.type2Str(e.getEmpNumber())); 
		    	sb.append("\",");  
		    	sb.append("\"name\":\"" + e.getFullName()); 
		    	sb.append("\",");  
		    	sb.append("\"sex\":\"" + e.getSexDesc()); 
		    	sb.append("\",");  
		    	sb.append("\"pno\":\"" + WebUIConvert.type2Str(e.getPhoneNumber())); 
		    	sb.append("\",");  
		    	sb.append("\"sal\":\"" + WebUIConvert.type2Str(e.getSalary())); 
		    	sb.append("\",");  
		    	sb.append("\"hdate\":\"" + WebUIConvert.type2Str(e.getHireDate())); 
		    	sb.append("\",");  
		    	sb.append("\"job\":\"" + WebUIConvert.type2Str(e.getJobName())); 
		    	sb.append("\",");  
		    	sb.append("\"dept\":\"" + WebUIConvert.type2Str(e.getDeptName()));
		    	sb.append("\"");  
	            sb.append("},");
		    }
		    if (recsSize>0){
			    sb.deleteCharAt(sb.lastIndexOf(","));  // 删去最后一个逗号  
		    }
		    sb.append("],\"pageMinRow\":\""+pageMinRow);
		    sb.append("\",\"pageMaxRow\":\""+pageMaxRow);
		    sb.append("\",\"firstPageFlag\":\""+firstPageFlag);
		    sb.append("\",\"lastPageFlag\":\""+lastPageFlag);
		    sb.append("\",\"totalPages\":\""+totalPages);
		    sb.append("\"}");
		    */
	        log(sb.toString());
	        res.getWriter().print(sb); 
		}
		else if(action.equals("/preupdate")){
			//log("getParameter id:"+req.getParameter("id"));
			Long empId = Long.parseLong(req.getParameter("id"));
			//log("empId:"+empId);
			EmpVO empVO = new EmpVO();
			EmpVODao dao = (EmpVODao) Factory.getInstance("EmpVODao");
			try {
				empVO = dao.findById(empId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sess.setAttribute("empVObk", empVO);//记录在session变量
			sb.append("{\"jsonRoot\":[");
			sb.append("{\"id\":\"" + empVO.getEmpId()); 
	    	sb.append("\",");  
	    	sb.append("\"eno\":\"" + WebUIConvert.type2Str(empVO.getEmpNumber())); 
	    	sb.append("\",");  
	    	sb.append("\"name\":\"" + empVO.getFullName()); 
	    	sb.append("\",");  
	    	sb.append("\"sex\":\"" + empVO.getSex()); 
	    	sb.append("\",");
	    	sb.append("\"sexdesc\":\"" + empVO.getSexDesc()); 
	    	sb.append("\",");  
	    	sb.append("\"pno\":\"" + WebUIConvert.type2Str(empVO.getPhoneNumber())); 
	    	sb.append("\",");  
	    	sb.append("\"sal\":\"" + WebUIConvert.type2Str(empVO.getSalary())); 
	    	sb.append("\",");  
	    	sb.append("\"hdate\":\"" + WebUIConvert.type2Str(empVO.getHireDate())); 
	    	sb.append("\",");  
	    	sb.append("\"jobid\":\"" + WebUIConvert.type2Str(empVO.getJobId())); 
	    	sb.append("\","); 
	    	sb.append("\"job\":\"" + WebUIConvert.type2Str(empVO.getJobName())); 
	    	sb.append("\",");  
	    	sb.append("\"deptid\":\"" + WebUIConvert.type2Str(empVO.getDepartmentId()));
	    	sb.append("\",");
	    	sb.append("\"dept\":\"" + WebUIConvert.type2Str(empVO.getDeptName()));
	    	sb.append("\",");  
	    	sb.append("\"remark\":\"" + WebUIConvert.type2Str(empVO.getRemark()));
	    	sb.append("\""); 
            sb.append("}]}");
            log(sb.toString());
	        res.getWriter().print(sb);
		}
		else if(action.equals("/update")){
			//更新员工
			Long empId = Long.parseLong(req.getParameter("id"));
			log("empId: "+empId.toString());
			log("name: "+req.getParameter("name"));
			boolean updateFlag=false;
			try{				
				EmpVO ebk = (EmpVO)sess.getAttribute("empVObk");
				if (ebk ==null){
					out.println("更新数据出错:会话数据已经无效!请返回再重新操作!");
					return;
				}
				if (!empId.equals(ebk.getEmpId())){
					out.println("更新的数据无法匹配!请重新查询!");
					log("empId:"+empId+",ebk.getEmpId:"+ebk.getEmpId());
					return;
				}
				//更新处理
				
				EmpVODao dao = (EmpVODao)Factory.getInstance("EmpVODao");			
				try{
					log("getHireDate:"+ebk.getHireDate());
					dao.lock(ebk);//加锁，验证数据是否变更了。
				}catch(Exception ex){
					ex.printStackTrace();
					sess.setAttribute("errbuf", ex.getMessage());
					throw new ServletException(ex);
					//out.println("记录已经被另外一个用户更新!请刷新界面再处理!");
					//return;
				}
				EmpVO e = new EmpVO();//dao.findById(empId);复制对象！
				e = (EmpVO) ebk.clone();
				log("e2:"+e.toString());
				log("ebk2:"+ebk.toString());
				e.setEmpNumber(req.getParameter("eno"));
				e.setEmpId(empId);
				e.setFullName(e.getLastName()+","+e.getFirstName());
				e.setSex(req.getParameter("sex"));
				e.setPhoneNumber(req.getParameter("pno"));
				e.setHireDate(TypeConvert.str2sDate(req.getParameter("hdate")));
				e.setSalary(TypeConvert.str2Double(req.getParameter("sal")));
				e.setJobId(TypeConvert.str2Long(req.getParameter("jobid")));
				e.setDepartmentId(TypeConvert.str2Long(req.getParameter("deptid")));
				e.setRemark(req.getParameter("remark"));
				//log("remark: "+req.getParameter("remark"));
				//String remark=(String)req.getParameter("remark");
				//log("remark1: "+remark);
				//remark=new String(remark.getBytes("ISO-8859-1"),"UTF-8");
				//log("remark2: "+remark);
				//e.setRemark(remark);				
				dao.update(e);
				updateFlag=true;
				log("updateFlag="+updateFlag);
			}catch(Exception e){
				e.printStackTrace();
				sess.setAttribute("errbuf", e.getMessage());
				throw new ServletException(e);
				//out.println("更新失败!请稍后重试!");
			}
			res.getWriter().print(updateFlag);
		}		
		else if(action.equals("/job")){
			String job_id=null;
			String job_name=null;	
			String sqlStmt=null;
			pageSize=Integer.parseInt(req.getParameter("pageSize"));
			pageNo=Integer.parseInt(req.getParameter("pageNo"));
			job_id=req.getParameter("job_id");
			job_name=req.getParameter("job_name");
			SqlResultSet resultSet=new SqlResultSet();
			String[] param = new String[1];
			Object[] paramVal = new Object[1];
			firstPageFlag=PageAnalyze.getFirstPageFlag(pageNo);
			pageMinRow=PageAnalyze.getPageMinRow(pageNo, pageSize);
			pageMaxRow=PageAnalyze.getPageMaxRow(pageNo, pageSize);	
			OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
			if(job_id!=null){
				sqlStmt="select job_id,job_name from XYG_JBO_CRM_JOB where 1=1";
				param[0]="1";
				paramVal[0]=job_id;
				log("job_id="+paramVal[0].toString());
				if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
					sqlStmt=sqlStmt+" AND JOB_ID LIKE :1 AND DISABLED_DATE IS NULL ORDER BY JOB_ID";
				}else{
					sqlStmt=sqlStmt+" AND JOB_ID = :1 AND DISABLED_DATE IS NULL ORDER BY JOB_ID";
				}
				try{
				    resultSet=dao.findByMinMax(sqlStmt, param, paramVal, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(job_name!=null){
				sqlStmt="select job_id,job_name from XYG_JBO_CRM_JOB where 1=1";
				param[0]="1";
				paramVal[0]=job_name;
				if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
					sqlStmt=sqlStmt+" AND JOB_NAME LIKE :1 AND DISABLED_DATE IS NULL ORDER BY JOB_ID";
				}else{
					sqlStmt=sqlStmt+" AND JOB_NAME = :1 AND DISABLED_DATE IS NULL ORDER BY JOB_ID";
				}
				try{
				    resultSet=dao.findByMinMax(sqlStmt, param, paramVal, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				sqlStmt="select job_id,job_name from XYG_JBO_CRM_JOB where 1=1 AND DISABLED_DATE IS NULL ORDER BY JOB_ID";
				try{
				    resultSet=dao.findByMinMax(sqlStmt, null, null, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			recsSize=resultSet.getResultSet().size();
		    lastPageFlag=PageAnalyze.getLastPageFlag(pageNo, pageSize, totalPages, recsSize);
		    if(recsSize>0){
			    pageMaxRow=pageMinRow+recsSize-1;
		    }else{
		    	pageMinRow=0;
		    	pageMaxRow=0;
		    }
		    sb.append("{\"jsonRoot\":["); 
		    for(int i=0;i<resultSet.getResultSet().size();i++){
		    	for(int n=0;n<resultSet.getResultSet().get(i).length;n++){
		    		if(n==0){
		    			sb.append("{");
		    		}
		    		sb.append("\""+resultSet.getColName()[n]+"\":\"" + resultSet.getResultSet().get(i)[n]); 
			    	sb.append("\",");  	
			    	if(n==(resultSet.getResultSet().get(i).length-1)){
			    		sb.deleteCharAt(sb.lastIndexOf(","));
			    		sb.append("},");
			    	}		    		
		    	}
		    }
		    if (recsSize>0){
			    sb.deleteCharAt(sb.lastIndexOf(","));  // 删去最后一个逗号  
		    }
		    sb.append("],\"pageMinRow\":\""+pageMinRow);
		    sb.append("\",\"pageMaxRow\":\""+pageMaxRow);
		    sb.append("\",\"firstPageFlag\":\""+firstPageFlag);
		    sb.append("\",\"lastPageFlag\":\""+lastPageFlag);
		    sb.append("\",\"totalPages\":\""+totalPages);
		    sb.append("\"}");
	        log(sb.toString());
	        res.getWriter().print(sb); 
		}
		else if(action.equals("/dept")){
			String dept_name=null;
			String dept_type_desc=null;	
			String manager_name=null;
			String sqlStmt=null;
			pageSize=Integer.parseInt(req.getParameter("pageSize"));
			pageNo=Integer.parseInt(req.getParameter("pageNo"));
			dept_name=req.getParameter("dept_name");
			dept_type_desc=req.getParameter("dept_type_desc");
			manager_name=req.getParameter("manager_name");
			SqlResultSet resultSet=new SqlResultSet();
			String[] param = new String[1];
			Object[] paramVal = new Object[1];
			firstPageFlag=PageAnalyze.getFirstPageFlag(pageNo);
			pageMinRow=PageAnalyze.getPageMinRow(pageNo, pageSize);
			pageMaxRow=PageAnalyze.getPageMaxRow(pageNo, pageSize);	
			OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
			if(dept_name!=null){
				sqlStmt="SELECT DEPT_ID,DEPT_NAME,DEPT_TYPE_DESC,MANAGER_NAME,LOCATION_NAME,ENABLE_DATE,REMARK FROM XYG_JBO_CRM_DEPT_V";
				param[0]="1";
				paramVal[0]=dept_name;
				if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
					sqlStmt=sqlStmt+" WHERE DEPT_NAME LIKE :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}else{
					sqlStmt=sqlStmt+" WHERE DEPT_NAME = :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}
				try{
				    resultSet=dao.findByMinMax(sqlStmt, param, paramVal, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(dept_type_desc!=null){
				sqlStmt="SELECT DEPT_ID,DEPT_NAME,DEPT_TYPE_DESC,MANAGER_NAME,LOCATION_NAME,ENABLE_DATE,REMARK FROM XYG_JBO_CRM_DEPT_V";
				param[0]="1";
				paramVal[0]=dept_type_desc;
				if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
					sqlStmt=sqlStmt+" WHERE DEPT_TYPE_DESC LIKE :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}else{
					sqlStmt=sqlStmt+" WHERE DEPT_TYPE_DESC = :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}
				try{
				    resultSet=dao.findByMinMax(sqlStmt, param, paramVal, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(manager_name!=null){
				sqlStmt="SELECT DEPT_ID,DEPT_NAME,DEPT_TYPE_DESC,MANAGER_NAME,LOCATION_NAME,ENABLE_DATE,REMARK FROM XYG_JBO_CRM_DEPT_V";
				param[0]="1";
				paramVal[0]=manager_name;
				if(paramVal[0].toString().indexOf("%")!=-1||paramVal[0].toString().indexOf("_")!=-1){
					sqlStmt=sqlStmt+" WHERE MANAGER_NAME LIKE :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}else{
					sqlStmt=sqlStmt+" WHERE MANAGER_NAME = :1 AND SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				}
				try{
				    resultSet=dao.findByMinMax(sqlStmt, param, paramVal, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				sqlStmt="SELECT DEPT_ID,DEPT_NAME,DEPT_TYPE_DESC,MANAGER_NAME,LOCATION_NAME,ENABLE_DATE,REMARK FROM XYG_JBO_CRM_DEPT_V WHERE SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ORDER BY 1";
				try{
				    resultSet=dao.findByMinMax(sqlStmt, null, null, pageMinRow, pageMaxRow);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			recsSize=resultSet.getResultSet().size();
		    lastPageFlag=PageAnalyze.getLastPageFlag(pageNo, pageSize, totalPages, recsSize);
		    if(recsSize>0){
			    pageMaxRow=pageMinRow+recsSize-1;
		    }else{
		    	pageMinRow=0;
		    	pageMaxRow=0;
		    }
		    sb.append("{\"jsonRoot\":["); 
		    for(int i=0;i<resultSet.getResultSet().size();i++){
		    	for(int n=0;n<resultSet.getResultSet().get(i).length;n++){
		    		if(n==0){
		    			sb.append("{");
		    		}
		    		if(resultSet.getResultSet().get(i)[n]!=null){
		    			sb.append("\""+resultSet.getColName()[n]+"\":\"" + resultSet.getResultSet().get(i)[n]);
		    		}
		    		else{
		    			sb.append("\""+resultSet.getColName()[n]+"\":\"");
		    		}
			    	sb.append("\",");  	
			    	if(n==(resultSet.getResultSet().get(i).length-1)){
			    		sb.deleteCharAt(sb.lastIndexOf(","));
			    		sb.append("},");
			    	}		    		
		    	}
		    }
		    if (recsSize>0){
			    sb.deleteCharAt(sb.lastIndexOf(","));  // 删去最后一个逗号  
		    }
		    sb.append("],\"pageMinRow\":\""+pageMinRow);
		    sb.append("\",\"pageMaxRow\":\""+pageMaxRow);
		    sb.append("\",\"firstPageFlag\":\""+firstPageFlag);
		    sb.append("\",\"lastPageFlag\":\""+lastPageFlag);
		    sb.append("\",\"totalPages\":\""+totalPages);
		    sb.append("\"}");
	        log(sb.toString());
	        res.getWriter().print(sb); 
		}
		else if(action.equals("/insert")){
			//获取界面的值，insert到db
			for(int i=0;i<100;i++){
			EmpVO e = new EmpVO();
			e.setEmpNumber(req.getParameter("emp_number"));
			e.setFirstName(req.getParameter("first_name"));
			e.setLastName(req.getParameter("last_name"));
			e.setFullName(e.getLastName()+","+e.getFirstName());
			e.setSex(req.getParameter("sex"));
			e.setEmail(req.getParameter("email"));
			e.setPhoneNumber(req.getParameter("phone_number"));
			try {
				e.setHireDate(TypeConvert.str2sDate(req.getParameter("hire_date")));
				e.setEnableDate(TypeConvert.str2Timestamp(req.getParameter("enable_date")));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.setJobId(TypeConvert.str2Long(req.getParameter("job_id")));
			e.setSalary(TypeConvert.str2Double(req.getParameter("salary")));
			e.setManagerId(TypeConvert.str2Long(req.getParameter("manager_id")));
			e.setDepartmentId(TypeConvert.str2Long(req.getParameter("department_id")));
			e.setUserId(TypeConvert.str2Long(req.getParameter("user_id")));
			e.setDisabledDate(null);
			e.setRemark(req.getParameter("remark"));
			try{
				EmpVODao dao = (EmpVODao) Factory.getInstance("EmpVODao");
				dao.insert(e);
				res.sendRedirect("list.do");
			}catch(Exception ex){
				ex.printStackTrace();
				res.setContentType("text/html;charset=utf-8");
				out.println("系统繁忙，稍后重试");
				out.println("<a href='insert.jsp'>重试</a>");
			}	
			}
		}
		else if(action.equals("/getDefaultIRR")){
			//根据用户和报表的名称获取默认打开的文件夹
			String sqlStmt;
			sqlStmt="SELECT HEADER_ID "
					+ " FROM XYG_ALD_INTERACT_HEADERS "
					+ "  WHERE 1=1 "
					+ " AND DEFAULT_FLAG = 'Y' "
					+ " AND INTERACT_CODE =:1  "
					+ " AND USER_ID = :2 "
					+ " AND ROWNUM<=1"
					+ " AND 1=0 ";
			String[] param = new String[2];
			Object[] paramVal = new Object[2];
			param[0] = "1";
			param[1] = "2";
			paramVal[0] = "REPORT_TEST";
			paramVal[1] = "1";
			String retStr=new String();
			try{
				OracleDao dao=(OracleDao)Factory.getInstance("OracleDao");
				retStr=dao.retStrBySql(sqlStmt,param,paramVal);
			}catch(Exception e){
				e.printStackTrace();
			}
			//由于Long不接受空字符值和null的处理，所以只好用0来代替！
			retStr = (retStr.length()==0?"0":retStr);
			log("retStr:"+retStr);
			String resPrint = new String();
			try{
				InteractDao dao = (InteractDao)Factory.getInstance("InteractDao");
				resPrint = dao.retJsonById(Long.parseLong(retStr));
			}catch(Exception e){
				e.printStackTrace();
			}
			log("resPrint:"+resPrint);
	        res.getWriter().print(resPrint);  
		}
		/*else if(action.equals("/load")){
		    //获取员工
		    Long empId = Long.parseLong(req.getParameter("id"));
		    try{
			    EmpVODao dao = (EmpVODao) Factory.getInstance("EmpVODao");
			    EmpVO e = dao.findById(empId);
		    	ebk = e;
		    	req.setAttribute("empVO", e);
		    	req.getRequestDispatcher("UpdateEmpVO.jsp").forward(req, res);
		    }catch(Exception e){
		    	e.printStackTrace();
		    	out.println("读取失败!请稍后重试!");
		    }
	    }*/
		
	}
}
