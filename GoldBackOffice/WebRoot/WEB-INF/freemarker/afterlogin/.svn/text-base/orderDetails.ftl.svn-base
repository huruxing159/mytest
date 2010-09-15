<html>
	<head>
	</head>
	
	<body>
		<#if order??>
			<div>
			    ${order.purchaseAttemp.gh_transaction_id}</br>
				${order.purchaseAttemp.server}&nbsp;
				${order.purchaseAttemp.faction}&nbsp;
				${order.purchaseAttemp.character}&nbsp;
				${order.purchaseAttemp.gold_amount}gold</br>
				${order.purchaseAttemp.trade_method}</br>
				${order.purchaseAttemp.auction_item_name}</br>
				
				${order.purchaseAttemp.concurrencySymbol}${order.purchaseAttemp.price}&nbsp;paid by
				${order.purchaseAttemp.payment_method}</br></br>
				
				
				
			</div>
			</br>
			</br>
			<#if order.failReason??>
				<#list order.failReason as reason >
					发货失败原因：${reason.reason}</br>
					<#if reason.vendor?? >供货商：${reason.vendor.name}</#if>
					</br>
				</#list>				
			</#if>
			
		</#if>	
	</body>


</html>
