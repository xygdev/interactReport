/*********************************************************
                    基于Jquery1.11.3版本
                      Juery排序功能
                      creat by brid
*********************************************************/

(function($) {
    /*******************************************************
                         修改日志
             2016.06.29 创建文件
    *******************************************************/
                                       	
	/******************listener start***********************
	              监听带有 data-orderby 属性的<th>标签
    *********************************************************/
	$.fn.orderListener = function(){ 
		/****绑定<a>标签****/
		$('a[data-ordertable]').on('click', function(e) {	
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).orderby($(this).data());
		});
		$('a[data-config]').on('click', function(e) {	
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).config($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-order]').on('click', function(e) {	
			$(this).order($(this).data());
		});
		$('button[data-config]').on('click', function(e) {	
			$(this).config($(this).data());
		});
		
	}   
	/******************listener end***********************/	
	
	$().orderListener();//执行监听函数

    $.fn.orderby = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			ordertable:null
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	lastindex=($('#tb td').length/($('#tb tr').length-1))-1;
        	$('#setting').css('visibility','hidden');
        	$('#orderby select').html('');
        	$("#orderby input[value='ASC']").prop("checked","checked");
        	$('#orderby select').append('<option value="blank"></option>');
        	for(i=0;i<lastindex;i++){
        		value=$('#tb th:eq('+i+')').attr('class');
        		name=$('#tb th:eq('+i+')').text();
        		column=$('#tb th:eq('+i+')').attr('data-column');
        		if(column=="db"){
        			$('#orderby select').append('<option value="'+value+'" >'+name+'</option>');	
        		}
        		else{
        			continue;
        		}	
        	}
		});
	}
    
    $.fn.order = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			ordertable:false
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	orderby=null;
        	col1=$('#col1 option:selected').val();
        	col2=$('#col2 option:selected').val();
        	col3=$('#col3 option:selected').val(); 	
        	if(col1!='blank'){
        		order1=$('input[name="col1"]:checked ').val(); 
        		orderby=col1+' '+order1;
        		if(col2!='blank'){
            		order2=$('input[name="col2"]:checked ').val(); 
            		orderby=orderby+','+col2+' '+order2;
            		if(col3!='blank'){
                		order3=$('input[name="col3"]:checked ').val(); 
                		orderby=orderby+','+col3+' '+order3;
                	}
            	}
        		else{
        			if(col3!='blank'){
        				alert('排序二不能为空！');
        				return;
                	}
        		}
        	}
        	else{
        		if(col2=='blank'){
        			if(col3=='blank'){
        				alert('排序不能为空！');
        			}
        			else{
        				alert('排序一，排序二不能为空！');
        			}
        		}
        		else{
        			alert('排序一不能为空！');
        		}
        	}
        	
        	if(orderby!=null){
        		$('#order_para').val(orderby);
        		$('#page_no').val(1);
				$('#refresh').click();
				$('#orderby .close-reveal-modal').click();
        	}
		});
	}
    
    
    $.fn.config = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			config:null
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	configType=options.config;
        	if(configType=='init'){
        		$('#setting').css('visibility','hidden');
        		
        	}
        	else if(configType=='save'){
        		interact_code=$('#interact_code').val();
        		user_id=$('#user_id').val();
        		user_interact_name=$('#define_name').val();
        		order_by=$('#order_para').val();
        		page_size=$('#page_size').val();
        		if(user_interact_name==''){
        			alert('自定义名称不能为空');
        			return;
        		}
        		description=$('#define_desc').val();
        		seq = new Array();
        		count=$('#tb th').length-1;//获取总列数（排除最后一列的空列）
         	    for(i=0;i<count;i++){//遍历标题列（除最后一列空列外）
         	        if($('#tb th:eq('+i+')').css('display')!='none'){
    				    seq[i]=$('#tb th:eq('+i+')').attr('class');
         	        }
         	    }
         	    seq.reverse();
        		if($('#public_flag').prop("checked")==true){
        			public_flag='Y';
        		}
        		else{
        			public_flag='N';
        		}
        		if($('#default_flag').prop("checked")==true){
        			default_flag='Y';
        		}
        		else{
        			default_flag='N';
        		}
        		if($('#autoquery_flag').prop("checked")==true){
        			autoquery_flag='Y';
        		}
        		else{
        			autoquery_flag='N';
        		}
        		param='interact_code='+interact_code+'&user_id='+user_id+'&user_interact_name='+user_interact_name+'&order_by='+order_by+'&page_size='+page_size+'&description='+description+'&seq='+seq+'&public_flag='+public_flag+'&default_flag='+default_flag+'&autoquery_flag='+autoquery_flag;
        		$.ajax({
    				type:'post', 
    				data:param,
    				url:'getSaveData.do',
    				dataType:'json',
    				success: function (data) {
    					alert("保存成功");
    					$('#config .close-reveal-modal').click();
    				},error: function(){
    				    alert("未知错误");
    				}
    			});
        	}
        	else if(configType=='load'){
        		alert('load');
        	}
        	else{
        		alert('发生错误，请联系IT部门');
        	}
        	
		});
	}
})(jQuery);