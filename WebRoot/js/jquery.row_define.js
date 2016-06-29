/*********************************************************
                    基于Jquery1.11.3版本
*********************************************************/
/*********************************************************
       			要使用本jquery文件对表格添加定义列功能 ，       
	       		需要在表格最后一列添加一列空列，以空列作         
	      				为列移动的参考系                                 
*********************************************************/
(function($) {
	var lastindex=($('#tb td').length/($('#tb tr').length-1))-1;//设置全局变量lastindex（最后一列空白列的列索引）
    //var linenum=($('#tb tr').length-1);//设置全局变量linenum(除标题行外的总行数)	
	
	/****定义列按钮****/	         	        	        
    $('#row-define').click(function(){ 
    	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
    	$('#setting').css('visibility','hidden');
     	$('#show').empty();//清空show <option>中的内容
     	$('#hide').empty();//清空hide <option>中的内容
		count=$('th').length-1;//获取总列数（排除最后一列的空列）
     	for(i=0;i<count;i++){//遍历标题列（除最后一列空列外）
     	    if($('th:eq('+i+')').css('display')!='none'){
				//如果标题列不隐藏，则标题名加入show <option>中
     	        $('#show').append('<option value='+$('th:eq('+i+')').attr('class')+'>'+$('th:eq('+i+')').text()+'</option>')
     	    }
     	    else{
				//如果标题列隐藏，则标题名加入hide <option>中
     	        $('#hide').append('<option value='+$('th:eq('+i+')').attr('class')+'>'+$('th:eq('+i+')').text()+'</option>')
     	    }
     	}      
    });  
	
	/****隐藏按钮****/	
    $('#leftarrow').click(function(){ 
     	value=[];//定义数组value[]		
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option:selected').each(function(){
			//遍历show <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	});
		$('#hide').append($('#show option:selected'));//将show <option>中的选中项加入到hide <option>中
     	$('#show option:selected').hide();//将show <option>中的选中项删除
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值 
     	        $('th:eq('+rowindex+')').insertBefore($('th:eq('+lastindex+')'));//将索引值为rowindex的<th>标题单元格插入到lastindex空白列的前一列
     	        for(j=0;j<=linenum;j++){
					//遍历除了标题行外的所有表格行
     	            $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$('tr:eq('+j+')')));
					//将每一行中索引值为rowindex的<td>单元格插入到空白列相应单元格的前一格
     	        } 
     	        $('.'+value[i]).css('display','none');//将class为value[i]的元素隐藏
     	    }
     	    else{
				//如果遍历到的值为空，则跳出循环
     	        break;
     	    }
     	}  	     
    });
     	         	     
    /****显示按钮****/	
    $('#rightarrow').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
		hiddenindex=($('#show option').length);//设置当前第一列隐藏列的索引值totalindex为show <option>选项的总数（如果所有都显示，则totalindex=lastindex,即第一列隐藏列为空白列
     	$('#hide option:selected').each(function(){
			//遍历hide <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	});
        value=value.reverse();//将value数组的元素逆序排列
		$('#show').append($('#hide option:selected'));//将hide <option>中的选中项加入到show <option>中
     	$('#hide option:selected').remove(); //将hide <option>中的选中项删除
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空  
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值 
				if(rowindex!=hiddenindex){
					//如果选中项不为隐藏列中的第一列
					$('th:eq('+rowindex+')').insertBefore($('th:eq('+hiddenindex+')')); //将索引值为rowindex的<th>标题单元格插入到totalindex隐藏列第一列的前一列
					for(j=0;j<=linenum;j++){
						//遍历除了标题行外的所有表格行
     	                $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$('tr:eq('+j+')')));
						//将每一行中索引值为rowindex的<td>单元格插入到隐藏列第一列相应单元格的前一格
     	            }
				}
				$('.'+value[i]).css('display','');//将class为value[i]的元素显示
     	    }
     	    else{
				//如果遍历到的值为空，则跳出循环
     	        break;
     	    }
     	}   	         
    });
     	     
    /****隐藏所有按钮****/	
    $('#leftarrow_all').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option').each(function(){
			//遍历show <option>，将所有项的值存入value数组
     	    value.push($(this).val());
     	});
		$('#hide').append($('#show option'));//将show <option>中的所有项加入到hide <option>中
     	$('#show option').remove(); //将show <option>中的所有项删除
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){ 
			    //如果遍历到的值不为空  
     	        rowindex=$('.'+value[i]).index(); //设置列索引值rowindex为<option>中value的索引值 
     	        $('th:eq('+rowindex+')').insertBefore($('th:eq('+lastindex+')'));//将索引值为rowindex的<th>标题单元格插入到lastindex空白列的前一列  
     	        for(j=0;j<=linenum;j++){
					//遍历除了标题行外的所有表格行
     	            $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$('tr:eq('+j+')')));
					//将每一行中索引值为rowindex的<td>单元格插入到空白列相应单元格的前一格
     	        } 
     	        $('.'+value[i]).css('display','none');//将class为value[i]的元素隐藏
     	    }
     	    else{
				//如果遍历到的值为空，则跳出循环
     	        break;
     	    }
     	}
    });
	
    /****显示所有按钮****/	
    $('#rightarrow_all').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
		hiddenindex=($('#show option').length);//设置当前第一列隐藏列的索引值totalindex为show <option>选项的总数（如果所有都显示，则totalindex=lastindex,即第一列隐藏列为空白列
     	$('#hide option').each(function(){
			//遍历hide <option>，将所有项的值存入value数组
     	    value.push($(this).val());
     	}); 	
     	value=value.reverse();//将value数组的元素逆序排列
		$('#show').append($('#hide option'));//将hide <option>中的所有项加入到show <option>中
     	$('#hide option').remove();//将hide <option>中的所有项删除 
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值 
     	        $('th:eq('+rowindex+')').insertBefore($('th:eq('+hiddenindex+')'));//将索引值为rowindex的<th>标题单元格插入到totalindex隐藏列第一列的前一列  
     	        for(j=0;j<=linenum;j++){
					//遍历除了标题行外的所有表格行
     	            $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$('tr:eq('+j+')')));
					//将每一行中索引值为rowindex的<td>单元格插入到隐藏列第一列相应单元格的前一格
     	        }
                $('.'+value[i]).css('display','');//将class为value[i]的元素显示				
     	    }
     	    else{
				//如果遍历到的值为空，则跳出循环
     	        break;
     	    }
     	} 
    });
     	          	     
    /****上移按钮****/
    $('#uparrow').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option:selected').each(function(){
			//遍历show <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	});
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值 
     	        if(rowindex!=0){
					//如果列索引值rowindex不为0，即当前列不为显示列的第一列
     	            $('th:eq('+rowindex+')').insertBefore($('th:eq('+(rowindex-1)+')')); //将索引值为rowindex的<th>标题单元格插入到前一列的标题单元格之前
     	            for(j=0;j<=linenum;j++){
						//遍历除了标题行外的所有表格行
     	                $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq('+(rowindex-1)+')',$('tr:eq('+j+')')));
						//将每一行中索引值为rowindex的<td>单元格插入到前一列相应的单元格的前一格
     	            }
     	            $('#show option:eq('+rowindex+')').insertBefore($('#show option:eq('+(rowindex-1)+')')); 
					//将show <opotion>中索引值为rowindex的项插入到上一项之前
     	        }
     	        else{
					//如果列索引值为0，跳出当前循环
     	            break;
     	        }
     	    }	
     	    else{
				//如果遍历到的值为空，跳出当前循环
     	        break;
     	    }
     	}   
    });
     	     
    /****下移按钮****/
    $('#downarrow').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option:selected').each(function(){
			//遍历show <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	}); 
     	showindex=($('#show option').length-1);//设置当前最后一列显示列的索引值totalindex为show <option>选项的总数-1
     	value=value.reverse();//将value数组的元素逆序排列
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值 
     	        if(rowindex!=showindex){
					//如果列索引值rowindex不等于showindex,即当前列不为显示列的最后一列
     	            $('th:eq('+rowindex+')').insertAfter($('th:eq('+(rowindex+1)+')'));//将索引值为rowindex的<th>标题单元格插入到后一列的标题单元格之后 
     	            for(j=0;j<=linenum;j++){
						//遍历除了标题行外的所有表格行
     	                $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertAfter($('td:eq('+(rowindex+1)+')',$('tr:eq('+j+')')));
						//将每一行中索引值为rowindex的<td>单元格插入到后一列相应的单元格之后
     	            }
     	            $('#show option:eq('+rowindex+')').insertAfter($('#show option:eq('+(rowindex+1)+')')); 
					//将show <opotion>中索引值为rowindex的项插入到下一项之后
     	        }
     	        else{
					//如果列索引值等于showindex，跳出当前循环
     	            break;
     	        }
     	    }
     	    else{
				//如果遍历到的值为空，跳出当前循环
     	        break;
     	    }
     	}
    });
     	         	     
    /****上移至顶部按钮****/
    $('#toparrow').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option:selected').each(function(){
			//遍历show <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	});
     	value=value.reverse();//将value数组的元素逆序排列
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值  
     	        if(rowindex!=0){
					//如果列索引值rowindex不为0，即当前列不为显示列的第一列
     	            $('th:eq('+rowindex+')').insertBefore($('th:eq(0)'));//将索引值为rowindex的<th>标题单元格插入到第一列的标题单元格之前 
     	            for(j=0;j<=linenum;j++){
						//遍历除了标题行外的所有表格行
     	                $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertBefore($('td:eq(0)',$('tr:eq('+j+')')));
						//将每一行中索引值为rowindex的<td>单元格插入到第一列相应的单元格的前一格
     	            }
     	            $('#show option:eq('+rowindex+')').insertBefore($('#show option:eq(0)')); 
					//将show <opotion>中索引值为rowindex的项插入到第一项之前
     	        }
     	        else{
					//如果列索引值为0，跳出当前循环
         	        break;
     	        }
     	    }
     	    else{
				//如果遍历到的值为空，跳出当前循环
     	        break;
     	    }
     	}
    });
     	         	     
    /****下移至底部按钮****/
    $('#bottomarrow').click(function(){ 
     	value=[];//定义数组value[]
     	linenum=($('#tb tr').length-1);//获取表格除标题栏外的总行数
     	$('#show option:selected').each(function(){
			//遍历show <option>，将选中项的值存入value数组
     	    value.push($(this).val());
     	}); 
     	showindex=($('#show option').length-1);//设置当前最后一列显示列的索引值totalindex为show <option>选项的总数-1
     	for(i=0;i!=-1;i++){
			//遍历value数组
     	    if(value[i]!=null){
				//如果遍历到的值不为空
     	        rowindex=$('.'+value[i]).index();//设置列索引值rowindex为<option>中value的索引值  
     	        if(rowindex!=showindex){
					//如果列索引值rowindex不等于showindex,即当前列不为显示列的最后一列
     	            $('th:eq('+rowindex+')').insertAfter($('th:eq('+showindex+')'));//将索引值为rowindex的<th>标题单元格插入到当前显示列最后一列的标题单元格之后  
     	            for(j=0;j<=linenum;j++){
						//遍历除了标题行外的所有表格行
     	                $('td:eq('+rowindex+')',$('tr:eq('+j+')')).insertAfter($('td:eq('+showindex+')',$('tr:eq('+j+')')));
						//将每一行中索引值为rowindex的<td>单元格插入到当前显示列最后一列的相应的单元格之后
     	            }
     	            $('#show option:eq('+rowindex+')').insertAfter($('#show option:eq('+showindex+')')); 
					//将show <opotion>中索引值为rowindex的项插入到最后一项之后
     	        }
     	        else{
					//如果列索引值等于showindex，跳出当前循环
     	            break;
     	        }
     	    }
     	    else{
				//如果遍历到的值为空，跳出当前循环
     	        break;
     	    }
     	}
    });
		
})(jQuery);