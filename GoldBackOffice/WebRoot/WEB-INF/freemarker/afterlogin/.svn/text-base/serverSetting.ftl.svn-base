<html>
<head>
<title>设定价格和数量</title>

<script>
$(document).ready(function(){
        $('#upload_price_link').linkbutton('enable');//easyui linkbutton
		$("#upload_price_link").click(function(){
          	$('#winUploadPrice').window('open');
         });
         $('#winUploadPrice').window({
		    minimizable:false,
		    maximizable:false
		});
         $('#winUploadResult').window({
		    minimizable:false,
		    maximizable:false
		});
		
		
		$('#uploadForm').form({
		        url:"${base}/afterlogin/operatorUploadPrice",
		        onSubmit: function(){
		              if(document.uploadForm.upload.value==""){
		              	alert("请选择文件");
		              	return false;
		              }
		        },
		        success:function(data){
		        	if(data!="false"){
		        		$('#winUploadPrice').window('close');
			        	$("#winUploadResult").empty().append(data);
			        	$("#winUploadResult").window('open');
		        	}
		        }
		});
		
	
});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		jmesa_a_link("ServerSettingJmesaTemplate");
		iefocus("input[name=checkboxSelectAll]");
		$('input[name=checkboxSelectAll]').change(function(){
			if($('input[name=checkboxSelectAll]').is(':checked')){
				$('input[name=id]').attr('checked','checked')
			}else{
				$('input[name=id]').removeAttr('checked')
			}
	    });
	    $('#deleteAllLink').click(function(){
	        if($('input[name=id]:checked').val()==undefined){
	        	   alert("请选择需要删除的服务器");
	        	   return;
	        }
	    		if(confirm("确定删除？")){
	    		$("#progressDiv").show();
	    			var postData="?";
	    		   $('input[name=id]:checked').each(function(){
	    		       postData=postData+"id="+$(this).val()+"&";
	    		   });
	    		   postData=postData.substring(0,postData.length-1);
	    		    $.get('${base}/afterlogin/deleteServer'+postData, function(data) {
		    		     onInvokeAction("ServerSettingJmesaTemplate");
		    		   });
	    	 	// window.location.replace('${base}/afterlogin/deleteServer'+postData);
			
	    		}
	    });
	});
</script>
<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
    createHiddenInputFieldsForLimitAndSubmit(id);
}
function onInvokeExportAction(id) {
    var parameterString = createParameterStringForLimit(id);
    location.href = '${base}/afterlogin/serverSetting?' + parameterString;
}

</script>
</head>
<body>

<!-- 上传文件窗口  -->
<div id="winUploadPrice" class="easyui-window" title="上传价格" closed="true"   modal="true" style="width:417px;height:128px;padding:5px;cursor: w-resize;">
	<#include "uploadPrice.ftl"/>
</div>
<!-- 上传文件窗口  -->

<!--上传文件结果 -->
<div id="winUploadResult" class="easyui-window" title="上传价格" closed="true"   modal="true" style="width:700px;height:400px;padding:5px;cursor: w-resize;">
</div>
<!--上传文件结果 -->


<div  id="server_setting_tt" class="easyui-tabs" style="width:auto;height:auto;" >
<div  title="服务器修改" >
	<div style="float:right; text-align:right;" ><a id="upload_price_link" href="#" class="easyui-linkbutton" icon="icon-add">批量上传</a></div>
	<div style="float:right; text-align:right;" ><a href="javascript:jQuery.jmesa.setExportToLimit('ServerSettingJmesaTemplate','jexcel');onInvokeExportAction('ServerSettingJmesaTemplate','export')" class="easyui-linkbutton" icon="icon-add">下载模板</a></div>
	<form action="${base}/afterlogin/serverSetting" method="post">
		${jmesaViewCode?if_exists}
	</form>
</div>
</div>
<div id="progressDiv" style="position: absolute; top: 400px; left: 600px; z-index: 65536000;display:none" >
			<img src="${base}/images/progress_bar.gif"/>
</div>
</body>

</html>