<html>

<head>
<script type="text/javascript">
function desideShow(value){
   		switch(value){
   			case "onOrderData":
   				$("#onOrderDataDiv").show();
   				$("#onOrderStateDiv").hide();
   				$("#onOrderNumberDiv").hide();
   				$("#orderSelect").attr("value","onOrderData");
   				break
   			case "onOrderState":
   				$("#onOrderStateDiv").show();
   				$("#onOrderDataDiv").hide();
   				$("#onOrderNumberDiv").hide();
   				$("#orderSelect").attr("value","onOrderState");
   				break
   			case "onOrderNumber":
   			    $("#onOrderNumberDiv").show();
   				$("#onOrderDataDiv").hide();
   				$("#onOrderStateDiv").hide();
   				$("#orderSelect").attr("value","onOrderNumber");
   				break
   			default:
   		}
   }
$(document).ready(function(){
    $("#win_upload_picture").window('close');
	$('#upload_picture_form').form({
	        url:"${base}/afterlogin/uploadPictureForOrder",
	        onSubmit: function(){             
	        },
	        success:function(data){
	            if(data.search("j_spring_security_check")!=-1){
		        		 location.replace(location) ;
		        		 return;
		        }
	        		$("#win_upload_picture").html(data);
	        }
	});
	
   $("#onOrderStateDiv").hide();
   $("#onOrderNumberDiv").hide();
   $("#orderSelect").change(function(){
   		desideShow($(this).val());
   });
   desideShow("${selectType?if_exists}");
   <#if orderState?? >
   $("#orderStateSelect").attr("value","${orderState}");
   </#if>
   
    window.setInterval(function(){onInvokeAction("NewOrderJmesaTemplate");},10000);
});
</script>

<script type="text/javascript">
function onInvokeAction(id) {
    if(id=="NewOrderJmesaTemplate"){
	    $.get('${base}/afterlogin/newOrder?ajax=true', function(data) {
	        if(data.search("j_spring_security_check")!=-1){
	        		 location.replace(location) ;
	        		 return;
	        }
	        $("#newOrderJmesa").html(data);
	        $("#newOrderJmesa").ready(function(){
	       		 $("a[name='upload_picture_link']").linkbutton({iconCls:"icon-save"});
	       		 $("a[name='accept_link']").linkbutton({iconCls:"icon-add"});
	       		 $("a[name='reject_link']").linkbutton({iconCls:"icon-cancel"});
	        });
	    });
    }else{
    		setExportToLimit(id, '');
   		createHiddenInputFieldsForLimitAndSubmit(id);
    }
}
</script>
</head>
<body>
<div  id="vendor_order_management_tt" class="easyui-tabs" style="width:auto;height:auto;" >
		<div title="订单管理">
		    <form action="${base}/afterlogin/newOrder" id="newOrder"/>
			    <h2>最新订单</h2>
					<div id="newOrderJmesa">
						${jmesaCodeViewNewOrder?if_exists}
					</div>
			</form>
			<form action="${base}/afterlogin/vendorOrderManagement" method="post"/>
				
				<br/>
				<br/>
				<hr/>
				<h2>查询历史交易</h2>
				<br/>
				<div>   
				    <div style="float:left">
					     <select size="1" id="orderSelect" name="selectType">
		                        <option value="onOrderData" >按日期查询</option>
		                        <option value="onOrderState">按订单状态查询</option>
		                        <option value="onOrderNumber">按订单号查询</option>
		                </select>
				    </div>
				    <div id="onOrderDataDiv" style="float:left">
				   	 	从：<input type="textfield" name="fromDate" value="${formatFromDate?if_exists}" class="easyui-datebox" required="true" missingMessage="起始日期不能为空"/>至：<input type="textfield" name="toDate" value="${formatToDate?if_exists}" class="easyui-datebox" required="true" missingMessage="结束日期不能为空"/>
				    </div>
				    <div id="onOrderStateDiv" style="float:left">
				    		<select  id="orderStateSelect" name="orderState">
					    		<#list orderStateMap?keys as key>
					    			<option value="${key}" >${orderStateMap.get(key)}</option>
					    		</#list>	
				    		</select>
				    </div>
				    <div id="onOrderNumberDiv" style="float:left">
				    		订单号：<input type="textfield" name="orderNumber" value="${orderNumber?if_exists}" class="easyui-validatebox" required="true" validType="length[1,40]" missingMessage="订单号不能为空"/>
				    </div>
				    <div  style="float:left">
				    		<input type="submit" value="查询" />
				    </div>
			    <div>
			    
			   	<div style="clear:both">
					${jmesaCodeView?if_exists}
				</div>
			</form>
			
		</div>
	
</div>

</body>


</html>
