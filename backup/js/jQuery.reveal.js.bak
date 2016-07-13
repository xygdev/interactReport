/*****************************************************
                基于Jquery1.11.3版本
*****************************************************/
(function($) {

    /*------------------------------------------------
                jquery页面点击弹出框
    ------------------------------------------------*/
	 
    /*------------------------------------------------
                监听 data-reveal-id 传递的值
	    暂时只设置对<input> <button> <a>三种标签的监听
	     如需为其他更多元素增加点击弹出框效果
	      请在listener区域绑定新的监听标签
    -------------------------------------------------*/
	
	/******************listener start***********************/
	$.fn.revealListener = function(){ 
		$('input[data-reveal-id]').on('click', function(e) {		
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});
	
		$('button[data-reveal-id]').on('click', function(e) {
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});
	
		$('a[data-reveal-id]').on('click', function(e) {
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});
	
		$('i[data-reveal-id]').on('click', function(e) {
			var modalLocation = $(this).attr('data-reveal-id');
			$('#'+modalLocation).reveal($(this).data());
		});
	}   
	/******************listener end***********************/
	
	$().revealListener();//执行监听函数
	
    /*---------------------------
                                           属性继承与执行
    ----------------------------*/

    $.fn.reveal = function(options) {
        
        /*---------------------------
               设置默认属性
        ----------------------------*/
        var defaults = {  
	    	animation: 'fadeAndPop', //设置弹出特效默认值（默认设为FadeAndPop）
		    animationspeed: 300, //设置弹出速度默认值（默认设为300）
		    closeonbackgroundclick: true, //设置点击背景是否关闭弹出框（默认设为是）
		    dismissmodalclass: 'close-reveal-modal' ,//设置弹出框关闭按钮的默认值（默认设为close-reveal-modal）
		    bg:'reveal-modal-bg'//设置默认背景
    	}; 
    	
        //继承默认属性
        var options = $.extend({}, defaults, options); 
	
        return this.each(function() {
        
        /*---------------------------
               设置全局变量
        ----------------------------*/
        	var modal = $(this),
        		topMeasure  = parseInt(modal.css('top')),
				topOffset = modal.height() + topMeasure,
          		locked = false,
				modalBG = $('.'+options.bg);
        	    //modalBG = $('.reveal-modal-bg');

        /*---------------------------
               创建背景元素
        ----------------------------*/
			if(modalBG.length == 0) {
				modalBG = $('<div class="'+options.bg+'" />').insertAfter(modal);
				//modalBG = $('<div class="reveal-modal-bg" />').insertAfter(modal);
			}		    
     
	    /*---------------------------
                弹出与关闭动作
        ----------------------------*/
			//打开弹出框动作
			modal.bind('reveal:open', function () {
			//modal.on('reveal:open', function () {
			    modalBG.unbind('click.modalEvent');
				//modalBG.off('click.modalEvent');
				$('.' + options.dismissmodalclass).unbind('click.modalEvent');
				//$('.' + options.dismissmodalclass).off('click.modalEvent');
				if(!locked) {
					lockModal();
					//渐显加弹出特效
					if(options.animation == "fadeAndPop") {
						modal.css({'top': $(document).scrollTop()-topOffset, 'opacity' : 0, 'visibility' : 'visible'});
						modalBG.fadeIn(options.animationspeed/2);
						modal.delay(options.animationspeed/2).animate({
							"top": $(document).scrollTop()+topMeasure + 'px',
							"opacity" : 1
						}, options.animationspeed,unlockModal());					
					}
					//渐显特效
					if(options.animation == "fade") {
						modal.css({'opacity' : 0, 'visibility' : 'visible'});
						modalBG.fadeIn(options.animationspeed/2);
						modal.delay(options.animationspeed/2).animate({
							"opacity" : 1
						}, options.animationspeed,unlockModal());					
					} 
					//无特效
					if(options.animation == "none") {
						modal.css('visibility','visible');
						modalBG.css({"display":"block"});	
						unlockModal()				
					}
				}
				modal.unbind('reveal:open');//终止打开弹出框函数
				//modal.off('reveal:open');//终止打开弹出框函数
			}); 	

			//关闭弹出框动作
			modal.bind('reveal:close', function () {
			//modal.on('reveal:close', function () {
			  if(!locked) {
					lockModal();
					//渐隐加弹出特效
					if(options.animation == "fadeAndPop") {
						modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
						modal.animate({
							"top":  $(document).scrollTop()-topOffset + 'px',
							"opacity" : 0
						}, options.animationspeed/2, function() {
							modal.css({'top':topMeasure, 'opacity' : 1, 'visibility' : 'hidden'});
							unlockModal();
						});					
					}  	
					//渐隐特效
					if(options.animation == "fade") {
						modalBG.delay(options.animationspeed).fadeOut(options.animationspeed);
						modal.animate({
							"opacity" : 0
						}, options.animationspeed, function() {
							modal.css({'opacity' : 1, 'visibility' : 'hidden'});
							unlockModal();
						});					
					}  	
					//无特效
					if(options.animation == "none") {
						modal.css('visibility','hidden');
						modalBG.css({'display' : 'none'});	
					}		
				}
				modal.unbind('reveal:close');//终止关闭弹出框函数
			    //modal.off('reveal:close');//终止关闭弹出框函数
			});     
   	
	    /*---------------------------
            弹出框打开与关闭监听器
        ----------------------------*/
        	//监听打开弹出框
    	    modal.trigger('reveal:open')
			
			//监听关闭按钮，添加关闭动作
			var closeButton = $('.' + options.dismissmodalclass).bind('click.modalEvent', function () {
    	    //var closeButton = $('.' + options.dismissmodalclass).on('click.modalEvent', function () {
			  modal.trigger('reveal:close')
			});
			
			//点击背景时关闭弹出框
			if(options.closeonbackgroundclick) {//如果closeonbackgroundclick属性为true
				modalBG.css({"cursor":"pointer"})
				modalBG.bind('click.modalEvent', function () {
			    //modalBG.on('click.modalEvent', function () {
				  modal.trigger('reveal:close')
				});
			}
			//设置键盘ESC键为关闭弹出框快捷键
			$('body').keyup(function(e) {
        		if(e.which===27){ modal.trigger('reveal:close'); } //键盘代码27为ESC键
			});
			
			
	     /*---------------------------
               设定弹出框虚拟状态
        ----------------------------*/
			function unlockModal() { //弹出框状态解锁
				locked = false;
			}
			function lockModal() {//弹出框状态锁定
				locked = true;
			}	
			
        });
    }
})(jQuery);
        
