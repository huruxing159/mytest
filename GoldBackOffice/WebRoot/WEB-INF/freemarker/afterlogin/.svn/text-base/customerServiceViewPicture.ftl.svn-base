<html>
<head>
<script src="${base}/js/galleria.js"></script>
<script>Galleria.loadTheme('${base}/galleria/themes/classic/galleria.classic.js');</script>
<script>
$(document).ready(function(){
$('.images').galleria();
});
</script>
</head>

<body>
	<div  id="view_picture" class="easyui-tabs" style="width:auto;height:auto;" >
		<div  class="images"  title="订单号：${orderNumber?if_exists}">
				<#list pictures as picture >
				 	<img src="${base}/${picture.location}"/>
				</#list>
		</div>
		<div title="详细">
		订单号：${orderNumber?if_exists}</br>
	    角色名：${charactorName?if_exists}</br>
	    金币：${goldAmount?if_exists}</br>
		</div>
    </div>
 
</body>



</html>