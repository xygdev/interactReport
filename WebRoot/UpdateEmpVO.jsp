<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*,com.xinyiglass.paging.entity.*,com.xinyiglass.paging.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更新员工</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
        <%
        try{
        	EmpVO e = (EmpVO)request.getAttribute("empVO");
        %>
    <form action="update.do?id=<%=e.getEmpId() %>" method="post">
        <fieldset>
        	<legend>更新员工:<%=e.getEmpId() %></legend>
        	工号：<input name="emp_number"  value = "<%=WebUIConvert.type2Str(e.getEmpNumber()) %>"><br/>
        	姓：<input name="last_name" value = "<%=e.getLastName() %>"><br/>
        	名称：<input name="first_name" value = "<%=e.getFirstName() %>"><br/>
        	性别：<input name="sex" value = "<%=e.getSex() %>"><br/>
        	邮件：<input name="email" value = "<%=WebUIConvert.type2Str(e.getEmail()) %>"><br/>
        	电话：<input name="phone_number" value = "<%=WebUIConvert.type2Str(e.getPhoneNumber()) %>"><br/>
        	雇用日期：<input name="hire_date" value = "<%=WebUIConvert.type2Str(e.getHireDate()) %>"><br/>
        	职务ID：<input name="job_id" value = "<%=WebUIConvert.type2Str(e.getJobId()) %>"><br/>
        	部门ID：<input name="department_id" value = "<%=WebUIConvert.type2Str(e.getDepartmentId()) %>"><br/>
        	薪水：<input name="salary" value = "<%=WebUIConvert.type2Str(e.getSalary()) %>"><br/>
        	启用日期：<input name="enable_date" value = "<%=WebUIConvert.type2Str(e.getEnableDate()) %>"><br/>
        	备注：<input name="remark" value = "<%=WebUIConvert.type2Str(e.getRemark()) %>"><br/>
        	<input type="submit" value="确认">
        	<input  type = "button" value="查看员工"  onclick="window.location.href='/web03/list.do'" />
        </fieldset>
    </form>
        <%
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
         %>
  </body>
</html>