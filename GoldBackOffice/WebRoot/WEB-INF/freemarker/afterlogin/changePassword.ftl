<html>
<head>
<title>
</title>
</head>
<script>
	$(document).ready(function(){
	        $('#submit').linkbutton('enable');//easyui linkbutton
	        $('#reset').linkbutton('enable');//easyui linkbutton
	});
</script>

<body>
<div  id="change_password_tt" class="easyui-tabs" style="width:auto;height:auto;" >
	<div style="align:center;" title="修改密码">
	
	<form action="${base}/afterlogin/changePassword" method="post">
	
	<table>
		<tr>
			<td>旧密码：</td>
			<td><input  type="password" name="oldPassword" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="密码不能为空"/></td>
		</tr>
		
		<tr>
			<td>新密码：</td>
			<td><input  type="password" name="newPassword" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="密码不能为空"/></td>
		</tr>
	
		<tr>
			<td>确认新密码：</td>
			<td><input  type="password" name="confirmPassword" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="密码不能为空"/></td>
		</tr>
	
		<tr>
			<td><input  type="submit" value="保存" id="submit"/></td>
			<td><input  type="reset" value="清空" id="reset"/></td>
		</tr>
	</table>
	
	
	
	</form>
	</div>
</div>

</body>





</html>