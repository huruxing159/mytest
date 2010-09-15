<html>
<head>

</head>

<body>
	<div  id="upload_picture" class="easyui-tabs" style="width:auto;height:auto;" >
		<div title="上传截图">
			<div>
			 <form id="upload_picture_form" action="${base}/afterlogin/uploadPictureForOrder" method="POST" enctype="multipart/form-data" style="padding:10px 20px 10px 40px;">
			       <input type="hidden" name="orderId" value="${orderId?c?if_exists}"/>
			        <p> 图片格式为：jpg/gif/png/bmp</p>
			        <p><@s.file name="upload" label="File"/></p>
			        <p><@s.file name="upload" label="File"/></p>
			        <p><@s.file name="upload" label="File"/></p>
			        <p><@s.file name="upload" label="File"/></p>
			        <p><@s.file name="upload" label="File"/></p>
			        	<input type="submit" value="添加" class="easyui-linkbutton" icon="icon-ok">
					<input type="reset" value="重置" class="easyui-linkbutton" icon="icon-cancel">
			   </form>
		   </div>
		   
		   <div>
		   </div>
		   
		  </div>
		  
	</div>
</body>



</html>