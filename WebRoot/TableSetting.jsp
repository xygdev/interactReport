<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
     <div class="setting">
       <i class="fa fa-cog fa-2x pointer" data-reveal-id="setting" data-animation="none"></i>
     </div>
     <div class="setting">
       <i id='refresh' class="fa fa-refresh fa-2x pointer" data-pagetype='refresh' data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
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
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 5</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o" aria-hidden="true"></i>
                 10</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 15</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 25</a>
               </li>
               <li>
                 <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="Paging/page.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                 <i class="fa fa-dot-circle-o hidden" aria-hidden="true"></i>
                 50</a>
               </li> 
             </ul>
           </li>
           <li><a class="pointer">更多设置</a></li>
           <li><a class="pointer">更多设置</a></li>
         </ul>
       </div>      
     </div>