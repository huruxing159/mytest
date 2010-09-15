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
		        url:"${base}/afterlogin/addVendor",
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
		
		$("a.editLinkClass").click(function(event) {
			event.preventDefault();
			$.get($(this).attr('href'), function(data) {
				$("#winEditRole").html(data);
				$("#winEditRole").window('open');
			});
	
		});
		
         
			
});
</script>

<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
    var parameterString = createParameterStringForLimit(id);
    $.get('${base}/afterlogin/vendorManagement?ajax=true&' + parameterString, function(data) {
        if(data.search("j_spring_security_check")!=-1){
        		 location.replace(location) ;
        		 return;
        }
        $("#vendor").html(data)
    });
    

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
			<td>供应商名字:</td>
			<td><input name="name" class="easyui-validatebox" required="true" validType="length[1,20]" missingMessage="供应商名不能为空"></td>
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


<div style="float:right; text-align:right;" ><a id="add_user_link" href="javascript:void(0)" class="easyui-linkbutton" icon="icon-add">添加供应商</a></div>
<form id="userManagerment_form" action="${base}/afterlogin/vendorManagement" method="post">
 <div id="vendor">
    ${jmesaView}
 </div>
</form>
    

</body>
</html>