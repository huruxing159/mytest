<#assign page=JspTaglibs["/WEB-INF/sitemesh-page.tld"]>
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<html>
<head>
<title>My Site - ${title}</title>
<link href="${base}/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/themes/icon.css">

<link rel="stylesheet" type="text/css" href="${base}/css/web.css"></link>
<link rel="stylesheet" type="text/css" href="${base}/css/jmesa.css"></link>

<script type="text/javascript" src="${base}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${base}/js/jquery.ajaxQueue.js"></script>
<script type="text/javascript" src="${base}/js/jquery.easyui.min.js"></script>


<script type="text/javascript" src="${base}/js/jquery.jmesa.js"></script>
<script type="text/javascript" src="${base}/js/jmesa.js"></script>
<script type="text/javascript" src="${base}/js/goldselling.js"></script>
<script>
if(window.addEventListener) window.addEventListener('beforeunload', function(){}, false);
</script>
<script type="text/javascript" >
$(document).ready(function(){
	 $("#img").click(function(){
		if($("#left").css("display")=="none"){
			$("#left").show();
		}else{
			$("#left").hide();
		}
	});
});
</script>
${head}
</head>

<body>

     <div id="top">
	      <div id="logo"></div>
		  <div id="user">欢迎您: ${username?if_exists}   IP:${userIp?if_exists}</div>	 
	 </div>
	 
	 
	 <div id="left">
		       <div id="left_menu">
               </div>
			   <div id="left_tree">
			        <div id="tree_icon">
					   <div id="yh"><img src="${base}/images/user.gif" />用户信息</div>
					   <div id="system"><img src="${base}/images/system.gif" />系统管理</div>
					</div>
					<div id="tree_text">
					<#include "menuContent.ftl" />
					</div>
			   </div>
			   <div id="tree_down">
               </div>
       </div>
       
       
	   <div id="right_top">
			   <div id="img"><img src="${base}/images/close.gif"/></div>
			   <span class="imgtext">打开/关闭左栏</span>
			   <div id="loginout">
			        <div id="loginoutimg"><img src="${base}/images/loginout.gif" /></div>
			        <a href="${base}/afterlogin/logout"><span class="logintext">退出系统</span></a> 
			   </div>			   		
			   </div>
			   <div id="right_font"><img src="${base}/images/main_14.gif"/> 您现在所在的位置：首页 →  <span class="bfont">${location?default("welcome")}</span>
			    </div>
        </div>

         <div id="innerbody" >
         <#include "error.ftl" />
         <#include "message.ftl" />
         ${body}
         
         </div>
         
</body>
</html>

