<html>

<head>

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
   			default:
   		}
   }
   
function dealWithAjax(){


		iefocus("input[name=checkboxSelectAll]");
		$('input[name=checkboxSelectAll]').change(function(){
			if($('input[name=checkboxSelectAll]').is(':checked')){
				$('input[name=id]').attr('checked','checked')
			}else{
				$('input[name=id]').removeAttr('checked')
			}
	    });
	    
	    $('#balanceLink').click(function(){
	      if($('input[name=id]:checked').val()==undefined){
	        	   alert("请选择需要结算的订单");
	        	   return;
	        }
	    		if(confirm("确定结算？")){
	    		$("#progressDiv").show();
		    		 var postData="?";
	    		   $('input[name=id]:checked').each(function(){
	    		       postData=postData+"id="+$(this).val()+"&";
	    		   });
	    		   postData=postData.substring(0,postData.length-1);
		    		   $.get('${base}/afterlogin/balanceOrder'+postData, function(data) {
		    		     onInvokeAction("FinancialQueryOrderJmesaTemplate");
		    		   });
			}
	    });
	    




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
      
      		$("#processDiv").css("left",event.pageX);
  			$("#processDiv").css("top",event.pageY);
  			$("#processDiv").show();
	   		event.preventDefault();
	   		$.get($(this).attr('href'), function(data) {
				onInvokeAction("FinancialQueryOrderJmesaTemplate");
  				$("#processDiv").hide();
				
			});
	   });
			   
		
	dealWithAjax();
		
    
});
</script>

<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
     createHiddenInputFieldsForLimitAndSubmit(id);
}

function onInvokeExportAction(id) {
    var parameterString = createParameterStringForLimit(id);
    parameterString=parameterString+"&selectType="+$("#orderSelect").val();
	parameterString=parameterString+"&fromDate="+$("#fromDateId").val();
	parameterString=parameterString+"&toDate="+$("#toDateId").val();
	parameterString=parameterString+"&orderState="+$("#orderStateSelect").val();
	parameterString=parameterString+"&orderNumber="+$("#orderNumberId").val();
	parameterString=parameterString+"&orderVendor="+$("#orderVendorSelect").val();
    location.href = '${base}/afterlogin/queryOrder?' + parameterString;
}

</script>
</head>
<body>
<div  id="financial_order_management_tt" class="easyui-tabs" style="width:auto;height:auto;" >
		<div title="交易记录">
		    
			<form id="order_form" action="${base}/afterlogin/queryOrder" method="post"/>
				<br/>
				<div >
					    <select size="1" id="orderSelect" name="selectType">
		                        <option value="onOrder">查询订单</option>
		                        <option value="onOrderNumber">按订单号查询</option>
		                </select>
				 </div>
				<hr/>
				<br/>
				<div>   
				    
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
			    <div>
			   
			   	<div style="clear:both" id="customerServiceDiv">
					${jmesaCodeView?if_exists}
				</div>
			</form>
			
		</div>
	
</div>


<div id="processDiv" style="position: absolute; top: 300px; left: 600px; z-index: 65536000;display:none" >
			<img src="${base}/images/process.gif"/>
</div>
<div id="progressDiv" style="position: absolute; top: 300px; left: 600px; z-index: 65536001;display:none" >
			<img src="${base}/images/process.gif"/>
</div>
</body>


</html>
