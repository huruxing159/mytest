<html>
<head>
<title>汇率设置</title>
<script type="text/javascript">
function onInvokeAction(id) {
    setExportToLimit(id, '');
    createHiddenInputFieldsForLimitAndSubmit(id);
}
</script>
</head>
<body>
<div  id="currency_setting_tt" class="easyui-tabs" style="width:auto;height:auto;" >
	<div  title="汇率设置">
		<form action="${base}/afterlogin/currencySetting" method="post">
			${currencyJmesaCode?if_exists}
		</form>
	</div>
</div>
</body>

</html>