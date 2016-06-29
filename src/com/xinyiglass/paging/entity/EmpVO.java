package com.xinyiglass.paging.entity;

import java.sql.Date;
import java.sql.Timestamp;



public class EmpVO implements Cloneable {
	   private String RowId;
	   private Long EmpId;
	   private String EmpNumber;
	   private String FirstName;
	   private String LastName;
	   private String FullName;
	   private String Sex;
	   private String SexDesc;
	   private String Email;
	   private String PhoneNumber;
	   private Date HireDate;
	   private Long JobId;
	   private String JobName;
	   private Double Salary;
	   private Long ManagerId;
	   private String ManagerFullName;
	   private Long DepartmentId;
	   private String DeptName;
	   private String DeptType;
	   private String DeptTypeDesc;
	   private String LocationName;
	   private Long UserId;
	   private Timestamp EnableDate;
	   private Timestamp DisabledDate;
	   private String Remark;
	   private Long CreatedBy;
	   private Timestamp CreationDate;
	   private Long LastUpdatedBy;
	   private Timestamp LastUpdateDate;
	   private Long LastUpdateLogin;
	   //GET&SET Method
	   public String getRowId() {
	      return RowId;
	   }
	   public void setRowId(String RowId) {
	      this.RowId = RowId;
	   }
	   public Long getEmpId() {
	      return EmpId;
	   }
	   public void setEmpId(Long EmpId) {
	      this.EmpId = EmpId;
	   }
	   public String getEmpNumber() {
	      return EmpNumber;
	   }
	   public void setEmpNumber(String EmpNumber) {
	      this.EmpNumber = EmpNumber;
	   }
	   public String getFirstName() {
	      return FirstName;
	   }
	   public void setFirstName(String FirstName) {
	      this.FirstName = FirstName;
	   }
	   public String getLastName() {
	      return LastName;
	   }
	   public void setLastName(String LastName) {
	      this.LastName = LastName;
	   }
	   public String getFullName() {
	      return FullName;
	   }
	   public void setFullName(String FullName) {
	      this.FullName = FullName;
	   }
	   public String getSex() {
	      return Sex;
	   }
	   public void setSex(String Sex) {
	      this.Sex = Sex;
	   }
	   public String getSexDesc() {
	      return SexDesc;
	   }
	   public void setSexDesc(String SexDesc) {
	      this.SexDesc = SexDesc;
	   }
	   public String getEmail() {
	      return Email;
	   }
	   public void setEmail(String Email) {
	      this.Email = Email;
	   }
	   public String getPhoneNumber() {
	      return PhoneNumber;
	   }
	   public void setPhoneNumber(String PhoneNumber) {
	      this.PhoneNumber = PhoneNumber;
	   }
	   public Date getHireDate() {
	      return HireDate;
	   }
	   public void setHireDate(Date HireDate) {
	      this.HireDate = HireDate;
	   }
	   public Long getJobId() {
	      return JobId;
	   }
	   public void setJobId(Long JobId) {
	      this.JobId = JobId;
	   }
	   public String getJobName() {
	      return JobName;
	   }
	   public void setJobName(String JobName) {
	      this.JobName = JobName;
	   }
	   public Double getSalary() {
	      return Salary;
	   }
	   public void setSalary(Double Salary) {
	      this.Salary = Salary;
	   }
	   public Long getManagerId() {
	      return ManagerId;
	   }
	   public void setManagerId(Long ManagerId) {
	      this.ManagerId = ManagerId;
	   }
	   public String getManagerFullName() {
	      return ManagerFullName;
	   }
	   public void setManagerFullName(String ManagerFullName) {
	      this.ManagerFullName = ManagerFullName;
	   }
	   public Long getDepartmentId() {
	      return DepartmentId;
	   }
	   public void setDepartmentId(Long DepartmentId) {
	      this.DepartmentId = DepartmentId;
	   }
	   public String getDeptName() {
	      return DeptName;
	   }
	   public void setDeptName(String DeptName) {
	      this.DeptName = DeptName;
	   }
	   public String getDeptType() {
	      return DeptType;
	   }
	   public void setDeptType(String DeptType) {
	      this.DeptType = DeptType;
	   }
	   public String getDeptTypeDesc() {
	      return DeptTypeDesc;
	   }
	   public void setDeptTypeDesc(String DeptTypeDesc) {
	      this.DeptTypeDesc = DeptTypeDesc;
	   }
	   public String getLocationName() {
	      return LocationName;
	   }
	   public void setLocationName(String LocationName) {
	      this.LocationName = LocationName;
	   }
	   public Long getUserId() {
	      return UserId;
	   }
	   public void setUserId(Long UserId) {
	      this.UserId = UserId;
	   }
	   public Timestamp getEnableDate() {
	      return EnableDate;
	   }
	   public void setEnableDate(Timestamp EnableDate) {
	      this.EnableDate = EnableDate;
	   }
	   public Timestamp getDisabledDate() {
	      return DisabledDate;
	   }
	   public void setDisabledDate(Timestamp DisabledDate) {
	      this.DisabledDate = DisabledDate;
	   }
	   public String getRemark() {
	      return Remark;
	   }
	   public void setRemark(String Remark) {
	      this.Remark = Remark;
	   }
	   public Long getCreatedBy() {
	      return CreatedBy;
	   }
	   public void setCreatedBy(Long CreatedBy) {
	      this.CreatedBy = CreatedBy;
	   }
	   public Timestamp getCreationDate() {
	      return CreationDate;
	   }
	   public void setCreationDate(Timestamp CreationDate) {
	      this.CreationDate = CreationDate;
	   }
	   public Long getLastUpdatedBy() {
	      return LastUpdatedBy;
	   }
	   public void setLastUpdatedBy(Long LastUpdatedBy) {
	      this.LastUpdatedBy = LastUpdatedBy;
	   }
	   public Timestamp getLastUpdateDate() {
	      return LastUpdateDate;
	   }
	   public void setLastUpdateDate(Timestamp LastUpdateDate) {
	      this.LastUpdateDate = LastUpdateDate;
	   }
	   public Long getLastUpdateLogin() {
	      return LastUpdateLogin;
	   }
	   public void setLastUpdateLogin(Long LastUpdateLogin) {
	      this.LastUpdateLogin = LastUpdateLogin;
	   }
	   public EmpVO( EmpVO e){
		   if (e!=null){
			   this.RowId = e.getRowId();
			   this.EmpId =e.getEmpId();
			   this.EmpNumber = e.getEmpNumber();
			   this.FirstName = e.getFirstName();
			   this.LastName = e.getLastName();
			   this.FullName = e.getFullName();
			   this.Sex = e.getSex();
			   this.SexDesc = e.getSexDesc();
			   this.Email = e.getEmail();
			   this.PhoneNumber = e.getPhoneNumber();
			   this.HireDate = e.getHireDate();
			   this.JobId = e.getJobId();
			   this.JobName = e.getJobName();
			   this.Salary = e.getSalary();
			   this.ManagerId = e.getManagerId();
			   this.ManagerFullName = e.getManagerFullName();
			   this.DepartmentId = e.getDepartmentId();
			   this.DeptName = e.getDeptName();
			   this.DeptType = e.getDeptType();
			   this.DeptTypeDesc = e.getDeptTypeDesc();
			   this.LocationName = e.getLocationName();
			   this.UserId = e.getUserId();
			   this.EnableDate = e.getEnableDate();
			   this.DisabledDate = e.getDisabledDate();
			   this.Remark = e.getRemark();
			   this.CreatedBy  = e.getCreatedBy();
			   this.CreationDate = e.getCreationDate();
			   this.LastUpdatedBy = e.getLastUpdatedBy();
			   this.LastUpdateDate = e.getLastUpdateDate();
			   this.LastUpdateLogin = e.getLastUpdateLogin();
		   }
	   }
	   
	   @Override  
	    public Object clone() {  
	        EmpVO empVO = null;  
	        try{  
	        	empVO = (EmpVO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return empVO;  
	    } 
	   
	public EmpVO() {
		// TODO Auto-generated constructor stub
	}
}
