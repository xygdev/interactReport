package com.xinyiglass.paging.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import com.xinyiglass.paging.dao.EmpVODao;
import com.xinyiglass.paging.entity.EmpVO;
import com.xinyiglass.paging.util.Constant;
import com.xinyiglass.paging.util.DBUtil;
import com.xinyiglass.paging.util.Factory;
import com.xinyiglass.paging.util.TypeConvert;

public class EmpVODaoImpl implements EmpVODao {
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	
	//添加员工
	public void insert(EmpVO e) throws Exception{
		Connection conn = DBUtil.getConnection();
		//调用Procedure向View对应的基表insert数据
		OracleCallableStatement stmt = null;
		try{
			String sql = "Declare "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  XYG_JBO_CRM_EMP_PKG.INSERT_EMP("
					+ "  :1"//,P_EMP_ID IN OUT NUMBER
					+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
					+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                    + " ,:4"//,P_LAST_NAME IN VARCHAR2
                    + " ,:5"//,P_FULL_NAME IN VARCHAR2
                    + " ,:6"//,P_SEX IN      VARCHAR2
                    + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                    + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                    + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                    + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                    + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                    + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                    + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                    + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                    + " ,:15"//,P_ENABLE_DATE IN DATE
                    + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                    + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
					+ " ); "
					+ "end;";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			//绑定变量
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			stmt.setString(2, e.getEmpNumber());
			stmt.setString(3, e.getFirstName());
			stmt.setString(4, e.getLastName());
			stmt.setString(5, e.getFullName());
			stmt.setString(6, e.getSex());
			stmt.setString(7, e.getEmail());
			stmt.setString(8, e.getPhoneNumber());
			stmt.setDate(9, e.getHireDate());
			//可能为空的Number相关的都要这样子处理
			stmt.setString(10, TypeConvert.type2Str(e.getJobId()));//stmt.setLong(10, e.getJobId());
			stmt.setString(11, TypeConvert.type2Str(e.getSalary()));//stmt.setDouble(11, e.getSalary());
			stmt.setString(12, TypeConvert.type2Str(e.getManagerId()));//stmt.setLong(12, e.getManagerId());
			stmt.setString(13, TypeConvert.type2Str(e.getDepartmentId()));//stmt.setLong(13, e.getDepartmentId());
			//stmt.setLong(14, e.getUserId());//这个方法不支持null的传入！会报错:java.lang.NullPointerException
			//stmt.setNUMBER(14, new Number(e.getUserId()));//Number的还要好好研究
			stmt.setString(14, TypeConvert.type2Str(e.getUserId()));
			stmt.setTimestamp(15, e.getEnableDate());
			stmt.setTimestamp(16, e.getDisabledDate());
			stmt.setString(17, e.getRemark());
			stmt.execute();
			Long empId = stmt.getLong(1);
			log("INSERT Emp ID:"+empId);
			conn.commit();
		}catch(Exception ex){
			conn.rollback();
			ex.printStackTrace();
			return;
		}finally{
			stmt.close();
			DBUtil.close(conn);
		}
	}
	
	//删除员工
	public void delete(Long empId) throws Exception{
		Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		try{
			String sql = "Declare "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  XYG_JBO_CRM_EMP_PKG.DELETE_EMP("
					+ "  :1"//P_EMP_ID IN NUMBER
					+ " ); "
					+ "end;";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			//绑定变量
			stmt.setLong(1, empId);
			stmt.execute();
			log("DELETE Emp ID:"+empId);
			conn.commit();
		}catch(Exception ex){
			conn.rollback();
			ex.printStackTrace();
			return;
		}finally{
			stmt.close();
			DBUtil.close(conn);
		}
	}
	
	//lock必须在打开界面的时候执行！因为lock的逻辑是要在更新之前要验证是否界面和DB的一致。
	public void lock(EmpVO e) throws Exception{
		Connection conn = DBUtil.getConnection();
		//调用Procedure向View对应的基表insert数据
		OracleCallableStatement stmt = null;
		try{
			//System.out.println("getUserId:"+TypeConvert.Long2Str(e.getUserId()));
			String sql = "Declare "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  XYG_JBO_CRM_EMP_PKG.LOCK_EMP("
					+ "  :1"//P_EMP_ID IN NUMBER
					+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
					+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                    + " ,:4"//,P_LAST_NAME IN VARCHAR2
                    + " ,:5"//,P_FULL_NAME IN VARCHAR2
                    + " ,:6"//,P_SEX IN      VARCHAR2
                    + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                    + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                    + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                    + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                    + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                    + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                    + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                    + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                    + " ,:15"//,P_ENABLE_DATE IN DATE
                    + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                    + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
					+ " ); "
					+ "end;";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			//绑定变量
			stmt.setLong(1, e.getEmpId());
			stmt.setString(2, e.getEmpNumber());
			stmt.setString(3, e.getFirstName());
			stmt.setString(4, e.getLastName());
			stmt.setString(5, e.getFullName());
			stmt.setString(6, e.getSex());
			stmt.setString(7, e.getEmail());
			stmt.setString(8, e.getPhoneNumber());
			stmt.setDate(9, e.getHireDate());
			stmt.setString(10, TypeConvert.type2Str(e.getJobId()));
			stmt.setString(11, TypeConvert.type2Str(e.getSalary()));
			stmt.setString(12, TypeConvert.type2Str(e.getManagerId()));
			stmt.setString(13, TypeConvert.type2Str(e.getDepartmentId()));
			stmt.setString(14, TypeConvert.type2Str(e.getUserId()));
			stmt.setTimestamp(15, e.getEnableDate());
			stmt.setTimestamp(16, e.getDisabledDate());
			stmt.setString(17, e.getRemark());
			stmt.execute();
			log("LOCK Emp ID:"+e.getEmpId());
			//conn.commit();
		}finally{
			DBUtil.close(conn,stmt);
		}
	}
	
	//更新员工
	public void update(EmpVO e) throws Exception{
		Connection conn = DBUtil.getConnection();
		//调用Procedure向View对应的基表insert数据
		OracleCallableStatement stmt = null;
		try{
			String sql = "Declare "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  XYG_JBO_CRM_EMP_PKG.UPDATE_EMP("
					+ "  :1"//P_EMP_ID IN NUMBER
					+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
					+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                    + " ,:4"//,P_LAST_NAME IN VARCHAR2
                    + " ,:5"//,P_FULL_NAME IN VARCHAR2
                    + " ,:6"//,P_SEX IN      VARCHAR2
                    + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                    + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                    + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                    + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                    + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                    + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                    + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                    + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                    + " ,:15"//,P_ENABLE_DATE IN DATE
                    + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                    + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
					+ " ); "
					+ "end;";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			//绑定变量
			stmt.setLong(1, e.getEmpId());
			stmt.setString(2, e.getEmpNumber());
			stmt.setString(3, e.getFirstName());
			stmt.setString(4, e.getLastName());
			stmt.setString(5, e.getFullName());
			stmt.setString(6, e.getSex());
			stmt.setString(7, e.getEmail());
			stmt.setString(8, e.getPhoneNumber());
			stmt.setDate(9, e.getHireDate());
			stmt.setString(10, TypeConvert.type2Str(e.getJobId()));
			stmt.setString(11, TypeConvert.type2Str(e.getSalary()));
			stmt.setString(12, TypeConvert.type2Str(e.getManagerId()));
			stmt.setString(13, TypeConvert.type2Str(e.getDepartmentId()));
			stmt.setString(14, TypeConvert.type2Str(e.getUserId()));
			stmt.setTimestamp(15, e.getEnableDate());
			stmt.setTimestamp(16, e.getDisabledDate());
			stmt.setString(17, e.getRemark());
			stmt.execute();
			log("UPDATE Emp ID:"+e.getEmpId());
			conn.commit();
		}catch(Exception ex){
			conn.rollback();
			ex.printStackTrace();
			return;
		}finally{
			stmt.close();
			DBUtil.close(conn);
		}
	}

	//查询所有员工
	public List<EmpVO> findAll(int begin,int end,String orderby) throws Exception{
		List<EmpVO> empVOList = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs=null;
		log("orderBy="+orderby);
		try{
			String sql = "SELECT * FROM (select ROWNUM ROW_NUM,TMP.* from (select * from XYG_JBO_CRM_EMP_V A order by "+orderby+") TMP)WHERE ROW_NUM >="+begin+" AND ROW_NUM <="+end;
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				EmpVO e = new EmpVO();
				e.setEmpId(rs.getLong("emp_id"));
				e.setEmpNumber(rs.getString("emp_number"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setFullName(rs.getString("full_name"));
				e.setSex(rs.getString("sex"));
				e.setSexDesc(rs.getString("sex_desc"));
				e.setEmail(rs.getString("email"));
				e.setPhoneNumber(rs.getString("phone_number"));
				e.setHireDate(rs.getDate("hire_date"));
				//e.setJobId(rs.getLong("job_id"));
				e.setJobId(rs.getObject("job_id")==null?null:rs.getLong("job_id"));
				e.setJobName(rs.getString("job_name"));
				//e.setSalary(rs.getDouble("salary"));
				e.setSalary(rs.getObject("salary")==null?null:rs.getDouble("salary"));
				//e.setManagerId(rs.getLong("manager_id"));
				e.setManagerId(rs.getObject("manager_id")==null?null:rs.getLong("manager_id"));
				e.setManagerFullName(rs.getString("manager_full_name"));
				//e.setDepartmentId(rs.getLong("department_id"));
				e.setDepartmentId(rs.getObject("department_id")==null?null:rs.getLong("department_id"));
				e.setDeptName(rs.getString("dept_name"));
				e.setDeptType(rs.getString("dept_type"));
				e.setDeptTypeDesc(rs.getString("dept_type_desc"));
				e.setLocationName(rs.getString("location_name"));
				//e.setUserId(rs.getLong("user_id"));
				e.setUserId(rs.getObject("user_id")==null?null:rs.getLong("user_id"));
				e.setEnableDate(rs.getTimestamp("enable_date"));
				e.setDisabledDate(rs.getTimestamp("disabled_date"));
				e.setRemark(rs.getString("remark"));
				e.setCreatedBy(rs.getLong("created_by"));
				e.setCreationDate(rs.getTimestamp("creation_date"));
				e.setLastUpdatedBy(rs.getLong("last_updated_by"));
				e.setLastUpdateDate(rs.getTimestamp("last_update_date"));
				e.setLastUpdateLogin(rs.getLong("last_update_login"));
				empVOList.add(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,stmt,rs);
		}
		//Statement state = conn.createStatement();
		//ResultSet rs = state.executeQuery("select * from XYG_JBO_CRM_EMP_V order by emp_id");
		return empVOList;
	}
	
	//查询员工总数
	public int countAll() throws Exception{
		int count=0;
		Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs=null;
		try{
			String sql="select count(*) count from XYG_JBO_CRM_EMP_V";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,stmt,rs);
		}
		return count;	
	}
	
	
	//查询单个员工
	public EmpVO findById(Long empId) throws Exception{
		if (empId==null){
			log("empId is null!");
			return null;
		}
		Connection conn = DBUtil.getConnection();
		//调用Procedure向View对应的基表insert数据
		OracleCallableStatement stmt = null;
		ResultSet rs=null;
		EmpVO e = null;
		try{
			String sql = "select * from XYG_JBO_CRM_EMP_V where emp_id = :1";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			stmt.setLong(1, empId);
			rs = stmt.executeQuery();
			if(rs.next()){
				e = new EmpVO();
				e.setEmpId(empId);
				e.setEmpNumber(rs.getString("emp_number"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setFullName(rs.getString("full_name"));
				e.setSex(rs.getString("sex"));
				e.setSexDesc(rs.getString("sex_desc"));
				e.setEmail(rs.getString("email"));
				e.setPhoneNumber(rs.getString("phone_number"));
				e.setHireDate(rs.getDate("hire_date"));
				//e.setJobId(rs.getLong("job_id"));
				e.setJobId(rs.getObject("job_id")==null?null:rs.getLong("job_id"));
				e.setJobName(rs.getString("job_name"));
				//e.setSalary(rs.getDouble("salary"));
				e.setSalary(rs.getObject("salary")==null?null:rs.getDouble("salary"));
				//如果是Long，而DB是空值，这里居然返回=0而不是空！
				//e.setManagerId(rs.getLong("manager_id"));
				e.setManagerId(rs.getObject("manager_id")==null?null:rs.getLong("manager_id"));
				//System.out.println("manager_id:"+rs.getLong("manager_id")); 
				e.setManagerFullName(rs.getString("manager_full_name"));
				//e.setDepartmentId(rs.getLong("department_id"));
				e.setDepartmentId(rs.getObject("department_id")==null?null:rs.getLong("department_id"));
				e.setDeptName(rs.getString("dept_name"));
				e.setDeptType(rs.getString("dept_type"));
				e.setDeptTypeDesc(rs.getString("dept_type_desc"));
				e.setLocationName(rs.getString("location_name"));
				//e.setUserId(rs.getLong("user_id"));
				e.setUserId(rs.getObject("user_id")==null?null:rs.getLong("user_id"));
				e.setEnableDate(rs.getTimestamp("enable_date"));
				e.setDisabledDate(rs.getTimestamp("disabled_date"));
				e.setRemark(rs.getString("remark"));
				e.setCreatedBy(rs.getLong("created_by"));
				e.setCreationDate(rs.getTimestamp("creation_date"));
				e.setLastUpdatedBy(rs.getLong("last_updated_by"));
				e.setLastUpdateDate(rs.getTimestamp("last_update_date"));
				e.setLastUpdateLogin(rs.getLong("last_update_login"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DBUtil.close(conn,stmt,rs);
		}
		return e;
	}
	
	//输入参数为Sql语句，输出行数，输出栏位数
	public StringBuffer findBySQL(String sql,int pagesize,int output,int pagemax,int pagemin) throws Exception{
		StringBuffer sb = new StringBuffer();
		Object[][] result=new Object[pagesize][output];
		Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs = null;	
		ResultSetMetaData rsmt = rs.getMetaData();
		String[] colName=new String[rsmt.getColumnCount()];
		for(int i=1;i<=rsmt.getColumnCount();i++){
			colName[i-1]= rsmt.getColumnName(i);
		}
		try{
			stmt = (OracleCallableStatement) conn.prepareCall(sql);
			rs = stmt.executeQuery();
			int count=0;
			while(rs.next()){
				for(int i=0;i<output;i++){
					result[count][i]=rs.getString(i+1);					
				}
				count++;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//JDBC.close(stmt, re);
			DBUtil.close(conn,stmt,rs);
		}
		return sb;
		
	}
	
	
	//根据一定的条件查询员工。condition 是where之后的部分，包括where
	public List<EmpVO> findByCondition(String condition,List<Integer> para,List<String> paraVal) throws Exception{
		List<EmpVO> empVOList = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		OracleCallableStatement stmt = null;
		ResultSet rs = null;
		try{
			//StringBuffer sql = new StringBuffer("select * from XYG_JBO_CRM_EMP_V ");
			StringBuffer sql = new StringBuffer("select * from ");
			if (condition!=null){
				sql.append(condition);
			}
			log(sql.toString());
			stmt = (OracleCallableStatement) conn.prepareCall(sql.toString());
			//绑定变量
			for (int i=0;i<para.size();i++){
				log("parameter :"+para.get(i)+"--->"+paraVal.get(i));
				stmt.setString(para.get(i), paraVal.get(i));
			}
			rs = stmt.executeQuery();
			//获取字段的名称以及属性。test用
			/*
			if (Constant.DEBUG_MODE){
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=1;i<=rsmd.getColumnCount();i++){
					String t = rsmd.getColumnTypeName(i);
					String n = rsmd.getColumnName(i);
					System.out.println("Column "+i+",name:"+n+" type is:"+t);
				}
			}*/
			while(rs.next()){
				EmpVO e = new EmpVO();
				/*
				e.setEmpId(rs.getLong("emp_id"));
				e.setEmpNumber(rs.getString("emp_number"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setFullName(rs.getString("full_name"));
				e.setSex(rs.getString("sex"));
				e.setSexDesc(rs.getString("sex_desc"));
				e.setEmail(rs.getString("email"));
				e.setPhoneNumber(rs.getString("phone_number"));
				e.setHireDate(rs.getDate("hire_date"));
				*/
				//e.setJobId(rs.getLong("job_id"));
				e.setJobId(rs.getObject("job_id")==null?null:rs.getLong("job_id"));
				e.setJobName(rs.getString("job_name"));
				//e.setSalary(rs.getDouble("salary"));
				/*
				e.setSalary(rs.getObject("salary")==null?null:rs.getDouble("salary"));
				//e.setManagerId(rs.getLong("manager_id"));
				e.setManagerId(rs.getObject("manager_id")==null?null:rs.getLong("manager_id"));
				e.setManagerFullName(rs.getString("manager_full_name"));
				//e.setDepartmentId(rs.getLong("department_id"));
				e.setDepartmentId(rs.getObject("department_id")==null?null:rs.getLong("department_id"));
				e.setDeptName(rs.getString("dept_name"));
				e.setDeptType(rs.getString("dept_type"));
				e.setDeptTypeDesc(rs.getString("dept_type_desc"));
				e.setLocationName(rs.getString("location_name"));
				//e.setUserId(rs.getLong("user_id"));
				e.setUserId(rs.getObject("user_id")==null?null:rs.getLong("user_id"));
				e.setEnableDate(rs.getTimestamp("enable_date"));
				e.setDisabledDate(rs.getTimestamp("disabled_date"));
				e.setRemark(rs.getString("remark"));
				e.setCreatedBy(rs.getLong("created_by"));
				e.setCreationDate(rs.getTimestamp("creation_date"));
				e.setLastUpdatedBy(rs.getLong("last_updated_by"));
				e.setLastUpdateDate(rs.getTimestamp("last_update_date"));
				e.setLastUpdateLogin(rs.getLong("last_update_login"));
				*/
				empVOList.add(e);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//JDBC.close(stmt, re);
			DBUtil.close(conn,stmt,rs);
		}
		return empVOList;
	}
	
	
	public static void main (String[] args) throws Exception{
		//01 Test insert emp in java:
		try{
			Constant.DEBUG_MODE =false;
			EmpVODao dao = (EmpVODao) Factory.getInstance("EmpVODao");
			Object[][] result=new Object[10][2];
			int pagesize=10;
			int output=3;
			String sql="select job_id,job_name,ENABLE_DATE from XYG_JBO_CRM_JOB where 1=1 order by job_id";
			//result=dao.findBySQL(sql, pagesize, output);
			for(int i=0;i!=-1;i++){
				if(result[i][0]==null){
					System.out.println("共有"+i+"行数据");
					break;
				}
				for(int j=0;j<output;j++){
					System.out.println("result["+i+"]["+j+"]="+result[i][j]);			
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		/*EmpVO ev = new EmpVO();
		java.sql.Timestamp currTime = new java.sql.Timestamp(System.currentTimeMillis());
		ev.setEmpNumber("7777");
		ev.setFirstName("偶偶");
		ev.setLastName("蔡");
		ev.setFullName(ev.getLastName()+","+ev.getFirstName());
		ev.setSex("U");
		ev.setEmail("caizuopeng@xinyiglass.com");
		ev.setPhoneNumber("88888999");
		ev.setHireDate(new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01").getTime()));
		ev.setJobId(Long.parseLong("21"));
		ev.setSalary(Double.parseDouble("100.2"));
		ev.setManagerId(null);
		ev.setDepartmentId(Long.parseLong("41"));
		ev.setUserId(null);//这里如果给空必须要特殊处理！麻烦的！！
		ev.setEnableDate(currTime);
		ev.setDisabledDate(null);
		ev.setRemark("测试直接添加2!");
		try{
			EmpVODao dao = (EmpVODao)Factory.getInstance("EmpVODao");
			dao.add(ev);
		}catch (Exception e){
			e.printStackTrace();
		}
		//02 test fileByEmpId and the update!
		
		try{
			Long empId = (long) 141;//Long.parseLong("181");
			EmpVODao dao = (EmpVODao)Factory.getInstance("EmpVODao");
			EmpVO fe = dao.findById(empId);
			if (fe == null){
				System.out.println(empId+" is a invaild ID!");
				return;
			}
			System.out.println(fe.getFullName()+"--"+fe.getDeptName()+"--"+fe.getEmpNumber());
			//lock 失败则报错！
			try{
				//fe.setEmpNumber("11111");
				dao.lock(fe);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Lock fail!");
				return;
			}
			//update
			//fe.setFirstName("旺财11");
			//fe.setEmpNumber("55612");
			dao.update(fe);
			fe = dao.findById(empId);//最好重新查询，保证所有栏位都是DB的。
			System.out.println(fe.getFullName()+"--"+fe.getDeptName()+"--"+fe.getEmpNumber());
			//delete：
			//dao.delete(fe.getEmpId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//test findByCondition
		
		
		
		
		System.out.println("test2");
		Connection conn = DBUtil.getConnection();
		//调用Procedure向View对应的基表insert数据
		OracleCallableStatement stmt = null;
		try{
			String sql = "Declare "
					+	"  l_rowid varchar2(240); "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  SELECT 1 INTO l_emp_id FROM DUAL WHERE 1=2; "
					+ "end;";
			stmt = (OracleCallableStatement)conn.prepareCall(sql);
			//绑定变量
			stmt.execute();
		}catch(Exception ex){
			System.out.println("sql exception!");
			ex.printStackTrace();
			return;
		}finally{
			System.out.println("sql finally!");
		}
		System.out.println("sql CONTINUE!");*/
	}
    
}
