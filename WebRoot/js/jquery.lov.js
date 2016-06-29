/*********************************************************
                    基于Jquery1.11.3版本
                      Jquery LOV功能
*********************************************************/
(function($) {
    /*******************************************************
    						修改日志
				2016.05.10 新增Lov取值函数
     *******************************************************/
	
	/******************listener start***********************
    			监听 data-lovname 传递的值                      
		暂时只设置对<input> <button> <a> <i> 四种标签的监听绑定               
   				如需为其他更多元素增加点击弹出框效果                      
    			请在listener区域绑定新的监听标签
	 *******************************************************/
	$.fn.lovListener = function(){ 
		/****绑定input标签****/
		$('input[data-lovname]').on('click', function(e) {		
			$(this).lov($(this).data());
		});
		/****绑定button标签****/
		$('button[data-lovname]').on('click', function(e) {
			$(this).lov($(this).data());
		});
		/****绑定a标签****/
		$('a[data-lovname]').on('click', function(e) {
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).lov($(this).data());
		});
		/****绑定i标签****/
		$('i[data-lovname]').on('click', function(e) {
			$(this).lov($(this).data());
		});				
	}   
	/******************listener end***********************/	
	
	$().lovListener();//执行监听函数
	
	
    $.fn.lov = function(options) {	
        /**************************
        		设置默认属性
		**************************/	    
    	var defaults={
    		lovsetting:'{"defaultquery":"","lovclass":"","title":"","querybox":"","contentbox":"","tablename":"","prevpage":"","nextpage":"","query":"","pageno":"","jsontype":""}'
    	} 
    	/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
    	
        return this.each(function() {  
        	/****Lov取值函数，将Lov表格中的值选入预更新框并关闭lov框****/        	        	
        	$.fn.choose = function(){
            	$(options.lovsetting.lovclass+' td').on('click', function() {
            		for(k=0;k<2;k++){
            			text=$(this).parent().children(options.choose[k]).text();
            			if(!text){
            				alert('不能选择空值');	
            				return;
            			}else{
            				$(options.recid[k]).val(text);
            			}   			
            		}
            		$(options.lovsetting.lovclass+' .'+options.dismissmodalclass).click();
            	});
        	}       	
        	$(options.lovsetting.title+' h1').text(options.lovname);
        	$(options.lovsetting.pageno).val('1');
        	$(options.lovsetting.prevpage).css('display','none');
        	$(options.lovsetting.nextpage).css('display','none');
        	$(options.lovsetting.typetd).val(options.lovsetting.jsontype);
        	$(options.lovsetting.urltd).val(options.lovsetting.queryurl);
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).html('');       	
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<tr>');
        	for(i=0;i!=-1;i++){
        		if(options.th[i]!=null){
        			$('tr:eq(0)',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<th>'+options.th[i]+'</th>');
        		}else{
        			break;
        			return i;
        		}
        	}
        	for(m=1;m<=10;m++){
        		$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<tr>');
        		for(n=0;n<i;n++){
        			$('tr:eq('+m+')',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<td class=\''+options.td[n]+'\'></td>');
        		}
        	}
        	$(options.lovsetting.querybox+' select').html('');
        	$('#lov_query_value').val('');
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).attr('id',options.lovsetting.tablename);
        	for(j=0;j!=-1;j++){
        		if(options.selectname[j]!=null&&options.selectvalue[j]!=null){
        			$(options.lovsetting.querybox+' select').append('<option value='+options.selectvalue[j]+'>'+options.selectname[j]+'</option>');
        		}else{
        			break;
        		}
        	}
        	$().choose();
        	if(options.lovsetting.defaultquery==true){//默认查询参数如果为true，则默认打开Lov时点击一次查询按钮
        		$(options.lovsetting.query).click();
        	}
        	/****调整Lov框位置的函数,待改进，考虑设置监听替代****/
      	    width='-'+parseInt($(options.lovsetting.lovclass).css('width'))/2+'px';      	    
      	    $(options.lovsetting.lovclass).css('margin-left',width);   
      	    /****调整Lov框位置的函数,待改进，考虑设置监听替代****/
        });    	
    }
})(jQuery);