<html>
<head>
<title>用户登录-login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${base}/js/jquery-1.4.2.min.js"></script>
<link href="${base}/css/login.css" rel="stylesheet" type="text/css" />

<script>
	$(document).ready(function(){
	       $("input[name='reset']").click(function(){
	      		 $("#error_message").hide();
	       }); 
	});
</script>
</head>
<body onload="document.loginForm.j_username.focus();">
<form action="${base}/j_spring_security_check" method="post" name="loginForm">
<div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="${base}/images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			       
			       <div id="user">用 户
			         <input type="text" name="j_username" />
			       </div>
				   <div id="password">密   码
				     <input type="password" name="j_password" />
				   </div>
				   <div id="btn"><input type="submit" name="submit" value="登录" /><input type="reset" name="reset" value="清空" /></div>
				 
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">管理信息系统 2010 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"> 
			  		<div id="error_message">
			    		      <#list actionMessages as actionMessage>
				        		<div class="message">${actionMessage}</div>
		          		 </#list>
		          	</div>
		      </div>		 
		 </div>

	</div>
</form>
</body>
</html>
