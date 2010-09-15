<html>
<head>
<script>
$(document).ready(function(){
        $('#add_user_link').linkbutton('enable');//easyui linkbutton
		$("#add_user_link").click(function(){
          	$('#winAddUser').window('open');
         });
         $('#winAddUser').window({
		    minimizable:false,
		    maximizable:false
		});
		 $('#winEditRole').window({
		    minimizable:false,
		    maximizable:false
		});
		
		$('#addUserForm').form({
		        url:"${base}/afterlogin/addUser",
		        onSubmit: function(){
		                 return $(this).form('validate');
		        },
		        success:function(data){
		               if(data.search("j_spring_security_check")!=-1){
				        		 location.replace(location) ;
				        		 return;
				        }
		        			$("#addUserResult").empty().append(data);
		                setTimeout(function(){$("#userManagerment_form").submit();},1000);//刷新jmesa的报表
		        }
		});
		jmesa_a_link("UserManagementJmesaTemplate");//define in goldselling.js file
		
		
         
			
});
</script>
<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
    var parameterString = createParameterStringForLimit(id);
    switch(id)
		{
		case "UserManagementJmesaTemplate":
		  $.get('${base}/afterlogin/userManagement?ajax=true&' + parameterString, function(data) {
	        if(data.search("j_spring_security_check")!=-1){
	        		 location.replace(location) ;
	        		 return;
	        }
	        $("#userJmesaView").html(data);
	        $("#userJmesaView").ready(function(){
	        		jmesa_a_link("UserManagementJmesaTemplate");//define in goldselling.js file 
	        });
	      });
		  break;
		case "RoleManagementJmesaTemplate":
		  $.get('${base}/afterlogin/roleManagement?ajax=true&' + parameterString, function(data) {
	        if(data.search("j_spring_security_check")!=-1){
	        		 location.replace(location) ;
	        		 return;
	        }
	        $("#roleJmesaView").html(data);
	      });
		  break;
		case "AuthorityManagementJmesaTemplate":
		  $.get('${base}/afterlogin/authorityManagement?ajax=true&' + parameterString, function(data) {
	        if(data.search("j_spring_security_check")!=-1){
	        		 location.replace(location) ;
	        		 return;
	        }
	        $("#authorityJmesaView").html(data);
	      });
		  break;
		default:
		  
		}

}
</script>
</head>
<body>

<!--添加用户的窗口 -->
<div id="winAddUser" class="easyui-window" title="添加用户" closed="true"   modal="true" style="width:400px;height:400px;padding:5px;cursor: w-resize;">
    <form id="addUserForm" >
    		<div id="addUserResult" style="text-align:center"></div>
       <table>
		<tr>
			<td>用户名:</td>
			<td><input name="user.username" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="用户名不能为空"></td>
		</tr>
		<tr>
			<td>密  码:</td>
			<td><input name="user.password" type="password" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="密码不能为空"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input name="user.email" class="easyui-validatebox" validType="email" required="true" missingMessage="email不能为空" invalidMessage="请输入正确的email"></td>
		</tr>
	
		<tr>
			<td>备  注:</td>
			<td><textarea  name="user.description" class="easyui-validatebox"  style="height:100px;"></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" value="添加"></td>
			<td><input type="reset" value="重置"></td>
		</tr>
		
	</table>
	
    </form>
</div>
<!-- -->
<div  id="winEditRole" class="easyui-window" title="修改角色" closed="true"   modal="true" style="width:400px;height:400px;padding:5px;cursor: w-resize;" >

</div>

<!-- tab-->
<div  id="userManagement_tt" class="easyui-tabs" style="width:auto;height:auto;" >
    <div title="用户列表" class="userManagement_tabs" >
    		<div style="float:right; text-align:right;" ><a id="add_user_link" href="#" class="easyui-linkbutton" icon="icon-add">添加用户</a></div>
    		<form id="userManagerment_form" action="${base}/afterlogin/userManagement" method="post">
           <div id="userJmesaView">
        			${jmesaView}
    			</div>
       </form>
    </div>
    <div title="角色列表"  class="userManagement_tabs" >
       <form id="roleManagerment_form" action="${base}/afterlogin/roleManagement" method="post">
           <div id="roleJmesaView">
        			${jmesaViewRole}
    			</div>
       </form>
    </div>
    <div title="权限列表" class="userManagement_tabs" icon="icon-reload" >
        <form id="authorityManagerment_form" action="${base}/afterlogin/authorityManagement" method="post">
           <div id="authorityJmesaView">
        			${jmesaViewAuthority}
    			</div>
       </form>
    </div>
</div>

</body>
</html>