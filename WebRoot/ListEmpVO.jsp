<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*,com.xinyiglass.paging.entity.*,com.xinyiglass.paging.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>分页查询</title>
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.js"></script>
  </head> 
  <body>
  
    <!-- 数据加载动画 start -->
  	<div class="ajax_loading">
      <i class="fa fa-spinner fa-pulse fa-4x" style="color:white"></i>
    </div>
    <!-- 数据加载动画 end -->
    
    <!-- 主表格区域 start -->
    <div class="table">
      <table id="tb">
        <tr>
          <th class="EMP_ID" data-column="db">id</th>
     	  <th class="EMP_NUMBER" data-column="db">工号</th>
     	  <th class="FULL_NAME" data-column="db">姓名</th>
     	  <th class="SEX_DESC" data-column="db">性别</th>
     	  <th class="PHONE_NUMBER" data-column="db">电话</th>
     	  <th class="SALARY" data-column="db">薪酬</th>
     	  <th class="HIRE_DATE" data-column="db">雇用日期</th>
     	  <th class="JOB_NAME" data-column="db">职位</th>
     	  <th class="DEPT_NAME" data-column="db">部门</th>
     	  <th class="ACTION" data-column="normal">操作</th> 
     	  <th style="display:none" data-column="normal">&nbsp;</th>
     	</tr>
     	<tr>
     	  <td class="EMP_ID"></td>
     	  <td class="EMP_NUMBER"></td>
		  <td class="FULL_NAME"></td>
     	  <td class="SEX_DESC"></td>
     	  <td class="PHONE_NUMBER"></td>
     	  <td class="SALARY"></td>
     	  <td class="HIRE_DATE"></td>
     	  <td class="JOB_NAME"></td>
     	  <td class="DEPT_NAME"></td>
     	  <td class="ACTION"></td>
     	  <td style="display:none">&nbsp;</td>
     	</tr>
     </table>
   </div>
   <!-- 主表格区域 end -->
   
   <!-- 主表格按钮区域 start -->
   <div class="table_button">
     <div class="setting">
       <i class="fa fa-cog fa-2x pointer" data-reveal-id="setting" data-animation="none"></i>
     </div>
     <div class="setting">
       <i id='refresh' class="fa fa-refresh fa-2x pointer" data-pagetype='refresh' data-orderby="emp_id" data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
     </div>
     <div id="setting">
       <div class="title">
         <span>设置</span>
         <a class="close-reveal-modal">&#215;</a>
       </div>  
       <div class="line"></div>
       <div class="content">
         <ul>
           <li><a class="pointer" id="row-define" data-reveal-id="row-def" data-animation="none">定义列</a></li>
           <li><a class="pointer">显示行数</a>
             <ul>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 5</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o" aria-hidden="true"></i>
                 10</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 15</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 25</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 50</a>
               </li> 
             </ul>
           </li>
           <li><a class="pointer" data-ordertable="tb" data-reveal-id="orderby" data-animation="none" >多维排序</a></li>
           <li><a class="pointer" data-config="init" data-reveal-id="config" data-animation="none" >个人配置</a></li>
         </ul>
       </div>      
     </div>
     <div class="arrow_S margin">
       <i id="last" class="fa fa-step-forward fa-2x pointer" data-pagetype="lastpage" data-orderby="emp_id" data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
     </div>
     <div class="arrow_L">
       <i id="next" class="fa fa-chevron-circle-right fa-2x pointer" data-pagetype="nextpage" data-orderby="emp_id" data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
     </div>
     <div class="pageRow">
     	<span id="pageRow">1</span>
     </div>
     <div class="arrow_L">
       <i id="previous" class="fa fa-chevron-circle-left fa-2x pointer" data-pagetype="prevpage" data-orderby="emp_id" data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
     </div>
     <div class="arrow_S">
       <i id="first" class="fa fa-step-backward fa-2x pointer" data-pagetype="firstpage" data-orderby="emp_id" data-queryurl="page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
     </div>
   </div>
   <!-- 主表格按钮区域 end --> 
   
   <!-- 定义列区域 start --> 
   <div id="row-def">
     <div class="title">
       <span class="title_name">定义列</span>
     </div>
	 <a class="close-reveal-modal">&#215;</a>
     <div class="line"></div>
     <div class="contain">
       <select class="option_frame" id="hide" multiple='multiple' title='隐藏'></select>
       <div class="button_frame">
     	 <button class="button" id="leftarrow">
     	   <i class="fa fa-angle-left fa-2x"></i>
     	 </button>
     	 <button class="button" id="rightarrow">
     	   <i class="fa fa-angle-right fa-2x"></i>
     	 </button>
     	 <button class="button" id="leftarrow_all">
     	   <i class="fa fa-angle-double-left fa-2x"></i>
     	 </button>
     	 <button class="button" id="rightarrow_all">
     	   <i class="fa fa-angle-double-right fa-2x"></i>
     	 </button>
       </div>
       <select class="option_frame" id="show" multiple='multiple' title='显示'></select>
       <div class="button_frame">
     	 <button class="button" id="uparrow">
     	   <i class="fa fa-angle-up fa-2x"></i>
     	 </button>
     	 <button class="button" id="downarrow">
     	   <i class="fa fa-angle-down fa-2x"></i>
     	 </button>
     	 <button class="button" id="toparrow">
     	   <i class="fa fa-angle-double-up fa-2x"></i>
     	 </button>
     	 <button class="button" id="bottomarrow">
     	   <i class="fa fa-angle-double-down fa-2x"></i>
     	 </button>
       </div>
     </div>
   </div>
   <!-- 定义列区域 end -->
    
   <!-- 多维排序区域 start -->
   <div id="orderby">
     <div class="title">
       <span class="title_name">多维排序</span>
     </div>
     <a class="close-reveal-modal">&#215;</a>
     <div class="line"></div>
     <div class="contain">
       <div class="item">
         <span>排序一：</span>
         <select class="select" id="col1"></select>
		 <input type="radio" name="col1" class="col1" checked="checked" value="ASC" />ASC 
         <input type="radio" name="col1" class="col1" value="DESC" />DESC 
       </div>  
       <div class="item">
         <span>排序二：</span>
         <select class="select" id="col2"></select>
		 <input type="radio" name="col2" class="col2" checked="checked" value="ASC" />ASC 
         <input type="radio" name="col2" class="col2" value="DESC" />DESC 
       </div> 
       <div class="item">
         <span>排序三：</span>
         <select class="select" id="col3"></select>
		 <input type="radio" name="col3" class="col3" checked="checked" value="ASC" />ASC 
         <input type="radio" name="col3" class="col3" value="DESC" />DESC 
       </div> 
     </div>
     <div class="footer">
       <button class='right pointer' data-order=true >排序</button>
     </div>
   </div>
   <!-- 多维排序区域 end -->
   
   <!-- 个人配置区域 start -->
   <div id="config">
     <div class="title">
       <span class="title_name">个人配置</span>
     </div>
     <a class="close-reveal-modal">&#215;</a>
     <div class="line"></div>
     <div class="contain">
       <div class="item left">
         <label class="pointer left" for="define_name">自定义名称：</label>
         <input type="text" class="left" name="define_name" id="define_name">
         <input type="text" class="right" name="define_desc" id="define_desc">
         <label class="pointer right" for="define_desc">描述：</label>
       </div>
       <div class="item margin-bottom left">
         <label class="pointer" for="public_flag">共享</label>
         <input type="checkbox" name="public_flag" id="public_flag">
         <label class="pointer" for="default_flag">默认</label>
         <input type="checkbox" name="default_flag" id="default_flag">
         <label class="pointer" for="autoquery_flag">自动查询</label>
         <input type="checkbox" name="autoquery_flag" id="autoquery_flag">
         <button class='pointer' data-config="save" >保存</button>
       </div>
       <div class="item left">
         <label class="pointer" for="loding_format">选择配置:</label>
         <select id="loding_format"></select>
         <button class='pointer' data-config="load">加载</button>
       </div>
     </div>
   </div>
   <!-- 个人配置区域 end -->
   
   <!-- 更新区域 start -->
   <div id='uf' class='update_frame'>     
     <div class='title'>      
       <span><i class="fa fa-user fa-1x" aria-hidden="true"></i>更新员工</span>
     </div>
     <a class="close-update-frame">&#215;</a>
     <div class='line'></div>
     <div class='content'>
       <form id='updateData'>
       <input type='hidden' id='id'>
       <label for='eno' class='left'>工号<span>*</span></label>
       <input type='text' id='eno' name='eno' class='left'>
       <label for='name' class='left'>全名</label>
       <input type='text' readonly='readonly' id='name' name='name' class='left'>
       <label for='sex' class='left'>性别<span>*</span></label>
       <select class='left' id='sex' name='sex'>
         <option value='M'>男</option>
         <option value='F'>女</option>
         <option value='U'>不明</option>
       </select>       
       <label for='pno' class='left'>电话</label>
       <input type='text' id='pno' name='pno' class='left'>
       <label for='sal' class='left'>薪酬</label>
       <input type='text' id='sal' name='sal' class='left'>
       <label for='hdate' class='left'>雇佣日期</label>
       <input type='text' id='hdate' name='hdate' class='left'>
       <label for='job' class='left'>职位<span>*</span></label>
       <input type='text' id='job' name='job' class='left short' readonly='readonly'>
       <input type='hidden' id='jobid' name='jobid'>
       <input type='button' id='lovid' class='left button pointer' data-reveal-id="lov" data-animation="none" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职位查询" data-th=["职务编号","职务名称"] data-td=["job_id","job_name"] data-selectname=["职务编号","职务名称"] data-selectvalue=["job_id","job_name"] data-choose=[".job_id",".job_name"] data-recid=["#jobid","#job"] data-lovsetting='{"defaultquery":true,"lovclass":".lov_frame","title":".lov_title","querybox":".querybox","contentbox":".contentbox","tablename":"job_query","prevpage":"#lov_prev","nextpage":"#lov_next","query":"#lov_query","pageno":"#lov_page_no","jsontype":"job","queryurl":"job.do","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' value="...">
       <label for='dept' class='left'>部门<span>*</span></label>
       <input type='text' id='dept' name='dept' class='left short' readonly='readonly'>
       <input type='hidden' id='deptid' name='deptid'>
       <input type='button' id='' class='left button pointer' data-reveal-id="lov" data-animation="none" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="部门查询" data-th=["部门编号","部门名称","部门分类","部门主管","部门地点","启用日期","备注"] data-td=["dept_id","dept_name","dept_type_desc","manager_name","location_name","enable_date","remark"] data-selectname=["部门名称","部门分类","部门主管"] data-selectvalue=["dept_name","dept_type_desc","manager_name"] data-choose=[".dept_id",".dept_name"] data-recid=["#deptid","#dept"] data-lovsetting='{"defaultquery":false,"lovclass":".lov_frame","title":".lov_title","querybox":".querybox","contentbox":".contentbox","tablename":"job_query","prevpage":"#lov_prev","nextpage":"#lov_next","query":"#lov_query","pageno":"#lov_page_no","jsontype":"dept","queryurl":"dept.do","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' value="...">
       <label for='remark' class='left'>备注</label>
       <textarea class='left' id='remark' name='remark'></textarea>  
     </div>
     </form>
     <div class='foot'>       
         <button class="right update_confirm pointer" data-dismissmodalclass="close-update-frame" data-crudtype="update" data-updateurl="update.do" data-queryurl="Paging/page.do" data-jsontype="table" data-updateparam=["id","#id"] data-crudsetting='{"loading":".ajax_loading","refresh":"#refresh","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#uf"}'>确认更新</button>
     </div>
   </div> 
   <!-- 更新区域 start -->
   
   <!-- Lov区域 start -->
   <div class='lov_frame' id='lov'>
     <a class="close-lov">&#215;</a>
     <div class='lov_title'>
     <h1>此处填写标题</h1>
     </div>
     <div class='blackline'></div>
     <div class='querybox'>
       <div class="left">
         Search  
       </div>  
       <select id="lov_select" class="left">
       </select>  
       <input type="text" id="lov_query_value" class="left" >
       <div class="left lov_button">
         <i class="fa fa-search pointer" id="lov_query" aria-hidden="true" data-crudtype="query" data-crudsetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}'></i>
       </div>
       <div class="left lov_button">
         <i class="fa fa-arrow-circle-left pointer" id="lov_prev" aria-hidden="true" data-pagetype="prevpage"  data-pagesetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}'></i>
       </div>
       <!-- <div id="lov_page_no">1</div>  -->
       <input type="type" id="lov_page_no" class="left" value="1" readonly='readonly'>
       <div class="left lov_button">
         <i class="fa fa-arrow-circle-right pointer" id="lov_next" aria-hidden="true" data-pagetype="nextpage"  data-pagesetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' ></i> 
       </div>                  
     </div>
     <div class='contentbox'>
       <table></table>
     </div>
     <div class='footer'>
     </div>
   </div>
   <div class='lov-modal-bg'>
   </div>
   <!-- lov区域 end -->
   
   <!-- hidden属性存放区域 start -->
   <input type="hidden" id="page_size" value="10">
   <input type="hidden" id="page_no" value="1">
   <input type="hidden" id="lov_page_size" value="10">
   <input type="hidden" id="lov_queryurl">
   <input type="hidden" id="lov_jsontype"> 
   <input type="hidden" id="order_para" value="emp_id">  
   <input type="hidden" id="user_id" value="224677">  
   <input type="hidden" id="interact_code" value="USER_INFO"> 
   <!-- hidden属性存放区域 end --> 
   
   <script type="text/javascript" src="js/jquery.pop-up.js"></script> 
   <script type="text/javascript" src="js/jquery.page.js"></script>
   <script type="text/javascript" src="js/jquery.lov.js"></script> 
   <script type="text/javascript" src="js/jquery.crud.js"></script>  
   <script>
        $(function(){
    		$('.setting #refresh').click();
        });
        
        $(function(){
            user_id=$('#user_id').val();
            interact_code=$('#interact_code').val();
            param='user_id='+user_id+'&interact_code='+interact_code;
            $.ajax({
				type:'post', 
				data:param,
				url:'getDefaultIRR.do',
				dataType:'json',
				success: function (data) {
				    if(data.EXISTS=='Y'){
				        $('#tb th').css('display','none');
            			$('#tb td').css('display','none');
            			linenum=$('#tb tr').length-1;
            			setnum=data.jsonRoot.SEQ.length;
            			for(i=0;i<setnum;i++){		   
							if(data.jsonRoot.SEQ[i].COLUMN_NAME!=null){			  				
								row=$('.'+data.jsonRoot.SEQ[i].COLUMN_NAME);
								rowindex=row.index();				
								$('.'+data.jsonRoot.SEQ[i].COLUMN_NAME).css('display','');
								if(rowindex!=0){
									$('th:eq('+rowindex+')').insertBefore($('th:eq(0)'));
									for(j=0;j<=linenum;j++){
     	                				$('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq(0)',$('tr:eq('+j+')')));
     	            				}
								}
								else{
									$('th:eq('+rowindex+')').insertBefore($('th:eq(1)'));
									for(j=0;j<=linenum;j++){
     	                				$('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq(1)',$('tr:eq('+j+')')));
     	            				}
								}
							}
				    	}	
				    }
				    else{
				    
				    }			
				},
				error: function () {
					alert("获取Json数据失败");
				}
			}); 
        });
   
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.EMP_ID',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].id); 
                   	 	$('.EMP_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].eno); 
                    	$('.FULL_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].name); 
                    	$('.SEX_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].sex); 
                    	$('.PHONE_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].pno); 
                    	$('.SALARY',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].sal);  
                    	$('.HIRE_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].hdate);
                    	$('.JOB_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].job); 
                    	$('.DEPT_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.jsonRoot[i].dept);	  
                    	$('.ACTION',$('#tb tr:eq('+(i+1)+')')).html('<i class=\'fa fa-trash-o delete pointer\' data-crudtype=\"del\" data-delurl=\"delete.do\" data-jsontype=\"table\" data-delparam=[\"id\",\".EMP_ID\"] data-crudsetting=\'{\"loading\":\".ajax_loading\",\"refresh\":\"#refresh\",\"firstpage\":\"#first\",\"lastpage\":\"#last\",\"prevpage\":\"#previous\",\"nextpage\":\"#next\",\"setpagesize\":\".set_page_size\",\"pagesize\":\"#page_size\",\"pageno\":\"#page_no\",\"pagerow\":\"#pageRow\",\"tablename\":\"#tb\"}\'></i>\&nbsp;<i class=\'fa fa-pencil fa-fw update pointer\' data-reveal-id=\'uf\' data-animation=\'fade\' data-dismissmodalclass=\'close-update-frame\' data-crudtype=\"pre-update\" data-preupdateurl=\"preupdate.do\" data-updateparam=[\"id\",\".EMP_ID\"] data-crudsetting=\'{\"loading\":\".ajax_loading\"}\' ></i>');           
                	}
                	$().crudListener();
                	$().revealListener();
        	    }
        	    else if(JSONtype=='job'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].JOB_ID);       	                    
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].JOB_NAME);       	               	        
       	            	} 
        	        }       	            	    
        	    }  
        	    else if(JSONtype=='dept'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	        	for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            		$('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].DEPT_ID);       	                    
       	            		$('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].DEPT_NAME); 
       	            		$('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].DEPT_TYPE_DESC);       	                    
       	            		$('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].MANAGER_NAME); 
       	            		$('td:eq(4)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].LOCATION_NAME);       	                    
       	            		$('td:eq(5)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].ENABLE_DATE);  
       	            		$('td:eq(6)',$('.contentbox tr:eq('+(i+1)+')')).html(data.jsonRoot[i].REMARK);     	               	        
       	            	}
       	            }   	    
        	    }   	            	   	
       	    },	
       	    getMSG:function(data){
       	        pageMinRow=parseInt(data.pageMinRow);
        	    pageMaxRow=parseInt(data.pageMaxRow);
        	    firstPageFlag=data.firstPageFlag;
        	    lastPageFlag=data.lastPageFlag;
        	   	totalPages=data.totalPages;
       	    },
       	    getUpdateJSON:function(data){   
       	        $('#id').val(data.jsonRoot[0].id); 	        
       	        $('#eno').val(data.jsonRoot[0].eno); 
       	        $('#name').val(data.jsonRoot[0].name);
       	        $('#sex option[value='+data.jsonRoot[0].sex+']').attr('selected',true);
       	        $('#sex option[value='+data.jsonRoot[0].sex+']').insertBefore($('option:eq(0)',$('#sex')));
       	        //$('#sex option[value='+data.jsonRoot[0].sex+']').attr('selected',true);
       	        $('#pno').val(data.jsonRoot[0].pno);
       	        $('#sal').val(data.jsonRoot[0].sal);
       	        $('#hdate').val(data.jsonRoot[0].hdate);
       	        $('#job').val(data.jsonRoot[0].job);
       	        $('#jobid').val(data.jsonRoot[0].jobid);
       	        $('#dept').val(data.jsonRoot[0].dept);
       	        $('#deptid').val(data.jsonRoot[0].deptid);
       	        $('#remark').val(data.jsonRoot[0].remark);
       	        $('#sex option').attr('selected',false);      
       	    },
       	    setUpdateParam:function(){
       	        param=$('#updateData').serialize();
       	        console.log(param);
       	    },
       	    setQueryParam:function(){
       	        param=$('#lov_select option:selected').val();
       	        value=$('#lov_query_value').val();
       	        console.log(value);
       	        if(!value){
       	            param=null;
       	        }       	        
       	        else{
       	            /***************************************
       	                 	因为%在url中为转义符，%25表达%字符本身，
       	                 	所以需要使用正则表达式全局替换，把字符串中
       	                 	所有的%替换为%25
       	            *****************************************/
       	            value=value.replace(/%/g,'%25');       
       	            param=param+'='+value;
       	        }
       	        console.log(param);
       	    }
       	}
       	
       	jQuery.crud={
       	    add:function(){
       	        width='-'+parseInt($('#lov').css('width'))/2+'px';      	    
      	        $('#lov').css('margin-left',width);
       	    }       	
       	}
   </script>
   <script type="text/javascript" src="js/jquery.row_define.js"></script>
   <script type="text/javascript" src="js/jquery.orderby.js"></script>		  
   
  </body>
</html>
