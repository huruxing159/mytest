<html>

<head>
<script src="${base}/js/jquery.hoverIntent.js" type="text/javascript"></script> <!-- optional -->
<script src="${base}/js/jquery.cluetip.js" type="text/javascript"></script>
<script src="${base}/js/jquery.copy.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${base}/css/jquery.cluetip.css" />

<script type="text/javascript">
function desideShow(value){
	switch(value){
		case "onOrderNumber":
		    $("#onOrderNumberDiv").show();
			$("#onOrderDiv").hide();
			$("#orderSelect").attr("value","onOrderNumber");
			break
		case "onOrder":
			$("#onOrderDiv").show();
			$("#onOrderNumberDiv").hide();
			$("#orderSelect").attr("value","onOrder");
			break
		default:
	}
}

$(document).ready(function(){
	   		   $("#onOrderNumberDiv").hide();
			   $("#orderSelect").change(function(){
			   		desideShow($(this).val());
			   });
	   		   desideShow("${selectType?if_exists}");
	   		   
			   <#if orderState?? >
			   $("#orderStateSelect").attr("value","${orderState}");
			   </#if>
			   
			   <#if orderVendor?? >
			   $("#orderVendorSelect").attr("value","${orderVendor}");
			   </#if>
   
			   $("a[name='customerServiceLink']").click(function(event){
			    		$("#progressDiv").css("left",event.pageX);
  				    $("#progressDiv").css("top",event.pageY);
			   		$("#progressDiv").show();
			   		event.preventDefault();
			   		$.get($(this).attr('href'), function(data) {
						onInvokeAction("CustomerServiceOrderManagementJmesaTemplate");
						$("#progressDiv").hide();
					});
			   });
			   
     		   $("a[name='transferLink']").click(function(event){
     		   	
			   		event.preventDefault();
			   		$("#orderIdHidden").val($(this).attr("href"));
			   		$("#transferDiv").window('open');
  			   });
 
			  $('#transfer_form').form({
			        url:"${base}/afterlogin/customerServiceTransferOrder",
			        success:function(data){
			        			$("#order_form").submit();//刷新jmesa的报表;
			        }
			  });
			  
	          $("a.basic").cluetip();
	         
		
     		  window.setInterval(function(){onInvokeAction("CustomerServiceOrderManagementJmesaTemplate");},10000);
});
</script>

<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
    if(id=="CustomerServiceOrderManagementJmesaTemplate"){
	    var parameterString = createParameterStringForLimit(id);
	    parameterString=parameterString+"&selectType="+$("#orderSelect").val();
	    parameterString=parameterString+"&fromDate="+$("#fromDateId").val();
	    parameterString=parameterString+"&toDate="+$("#toDateId").val();
	    parameterString=parameterString+"&orderState="+$("#orderStateSelect").val();
	    parameterString=parameterString+"&orderNumber="+$("#orderNumberId").val();
	    parameterString=parameterString+"&orderVendor="+$("#orderVendorSelect").val();
	    $.get('${base}/afterlogin/customerServiceOrderManagement?ajax=true&' + parameterString, function(data) {
	        if(data.search("j_spring_security_check")!=-1){
	        		 location.replace(location) ;
	        		 return;
	        }
	        $("#customerServiceDiv").html(data);
	        $("#customerServiceDiv").ready(function(){
	       		 $("a.easyui-linkbutton").linkbutton();
	        		 $("a[name='transferLink']").click(function(event){
			   		event.preventDefault();
			   		$("#orderIdHidden").val($(this).attr("href"));
			   		$("#transferDiv").window('open');
  				 });
  				 
  				 $("a[name='customerServiceLink']").click(function(event){
  				    $("#progressDiv").css("left",event.pageX);
  				    $("#progressDiv").css("top",event.pageY);
  					 $("#progressDiv").show();
			   		event.preventDefault();
			   		$.get($(this).attr('href'), function(data) {
							onInvokeAction("CustomerServiceOrderManagementJmesaTemplate");
							$("#progressDiv").hide();
					});
   				});
   				$("a.basic").cluetip();
	        });
	        
	    });
    }else{
	    createHiddenInputFieldsForLimitAndSubmit(id);
    }
}
</script>
</head>
<body>
<div  id="vendor_order_management_tt" class="easyui-tabs" style="width:auto;height:auto;" >
		<div title="订单管理">
			<form id="order_form" action="${base}/afterlogin/customerServiceOrderManagement" method="post">
				<br/>
					<div >
						    <select size="1" id="orderSelect" name="selectType">
			                        <option value="onOrder">查询订单</option>
			                        <option value="onOrderNumber">按订单号查询</option>
			                </select>
					 </div>
				<hr/>
					     <div id="onOrderDiv" style="float:left">
					        供货商：
					        <select  id="orderVendorSelect" name="orderVendor">
					        		<option value="allVendors">全部</option>
						    		<#list allVendors as vendor>
						    			<option value="${vendor.name}" >${vendor.name}</option>
						    		</#list>	
					    		</select>
					    		订单状态：
					    		<select  id="orderStateSelect" name="orderState">
					    			<option value="allStates">全部</option>
						    		<#list orderStateMap?keys as key>
						    			<option value="${key}" >${orderStateMap.get(key)}</option>
						    		</#list>	
					    		</select>
					    		起始日期：<input type="textfield" id="fromDateId" name="fromDate" value="${formatFromDate?if_exists}" class="easyui-datebox" required="true" missingMessage="起始日期不能为空"/>
					   	 	结束日期：<input type="textfield" id="toDateId" name="toDate" value="${formatToDate?if_exists}" class="easyui-datebox" required="true" missingMessage="结束日期不能为空"/>
					
					    </div>
					    
					    <div id="onOrderNumberDiv" style="float:left">
					    		订单号：<input type="textfield" id="orderNumberId" name="orderNumber" value="${orderNumber?if_exists}" class="easyui-validatebox" required="true" validType="length[1,40]" missingMessage="订单号不能为空"/>
					    </div>
					   
					    <div  style="float:left">
					    		<input type="submit" value="查询" />
					    </div>
					 
			   	<div style="clear:both" id="customerServiceDiv">
					${jmesaCodeView?if_exists}
				</div>
		</form>
		</div>
	
</div>

	
<div id="transferDiv" class="easyui-window" title="移交" closed="true"   modal="true" style="width:400px;height:400px;padding:5px;cursor: w-resize;">
				<form  id="transfer_form" action="${base}/afterlogin/customerServiceTransferOrder" method="post">
					<input type="hidden" name="orderId" id="orderIdHidden"/>
					<#list customerServices as user>
						<input type="radio" name="customerServiceId" value="${user.id}"/>${user.username}<br/>	
					</#list>
					<br/>
					<input  type="submit" value="移交"  />
				</form>
</div>

<div id="progressDiv" style="position: absolute; top: 300px; left: 600px; z-index: 65536000;display:none" >
			<img src="${base}/images/process.gif"/>
</div>

</body>


</html>
