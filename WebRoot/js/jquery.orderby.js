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
	$.fn.pageListener = function(){ 
		/****绑定th标签****/
		$('th[data-orderby]').on('click', function(e) {		
			$(this).page($(this).data());
		});
	}   
	/******************listener end***********************/	
	
	$().pageListener();//执行监听函数

    $.fn.page = function(options) {	        
		/*********************************************
                           设置默认属性
               orderby(true or false):设置排序功能是否开启
        *********************************************/	    
		var defaults={
			orderby:false
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
		/****全局函数****/
		jQuery.global={

       	}
			
        return this.each(function() {
        	if(options.orderby==true){
        		
        		
        	}
        	/*
        	$(options.pagesetting.loading).show();//显示加载数据加载动画
        	pageSize=parseInt($(options.pagesetting.pagesize).val());

			if(options.pagetype=='refresh'){
				pageNo=parseInt($(options.pagesetting.pageno).val());
				$('td',$('tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',$(options.pagesetting.tablename));
				$('td',$(options.pagesetting.tablename)).parent().remove();
				for(j=1;j<=pageSize;j++){
					$('tr:eq(0)',$(options.pagesetting.tablename)).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}

			else if(options.pagetype=='firstpage'){
				pageNo=parseInt(1);
				jQuery.global.main();
			}

			else if(options.pagetype=='lastpage'){
				pageNo=parseInt($(options.pagesetting.pageno).val())+1;
				jQuery.global.main();
			}

			else if(options.pagetype=='nextpage'){
				pageNo=parseInt($(options.pagesetting.pageno).val())+1;
				jQuery.global.main();
			}

			else if(options.pagetype=='prevpage'){	
				pageNo=parseInt($(options.pagesetting.pageno).val())-1;
				jQuery.global.main();
			}				

			else if(options.pagetype=='setpagesize'){
				pageSize=parseInt($(this).text());
				$('i',$(options.pagesetting.setpagesize)).css('visibility','hidden');
				$('i',$(this)).css('visibility','visible');
				$(options.pagesetting.pagesize).attr('value',pageSize);
				pageNo=parseInt(1);
				linenum=($(options.pagesetting.tablename+' tr').length-1);
				for(i=1;i<linenum;i++){
					$('tr:eq(1)',$(options.pagesetting.tablename)).remove();
				}
				$('td',$('tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',$(options.pagesetting.tablename));
				for(j=1;j<pageSize;j++){
					$('tr:eq(1)',$(options.pagesetting.tablename)).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}*/
		});
	}
})(jQuery);