<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
    <div id="row-def">
      <div class="title">
     	<span class="title_name">定义列</span>
      </div>
	  <a class="close-reveal-modal">&#215;</a>
      <div class="line"></div>
      <div class="contain">
     	 <select class="option_frame" id="hide" multiple='multiple' title='隐藏'></select>
     	 <div class="button_frame">
     	   <button class="button" id="leftarrow"><i class="fa fa-angle-left fa-2x"></i></button>
     	   <button class="button" id="rightarrow"><i class="fa fa-angle-right fa-2x"></i></button>
     	   <button class="button" id="leftarrow_all"><i class="fa fa-angle-double-left fa-2x"></i></button>
     	   <button class="button" id="rightarrow_all"><i class="fa fa-angle-double-right fa-2x"></i></button>
         </div>
     	 <select class="option_frame" id="show" multiple='multiple' title='显示'></select>
     	 <div class="button_frame">
     	   <button class="button" id="uparrow"><i class="fa fa-angle-up fa-2x"></i></button>
     	   <button class="button" id="downarrow"><i class="fa fa-angle-down fa-2x"></i></button>
     	   <button class="button" id="toparrow"><i class="fa fa-angle-double-up fa-2x"></i></button>
     	   <button class="button" id="bottomarrow"><i class="fa fa-angle-double-down fa-2x"></i></button>
     	 </div>
      </div>
    </div>
    
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