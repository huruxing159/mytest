<#assign jmesa=JspTaglibs["/WEB-INF/jmesa.tld"]/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>uploadSuccess</title>
</head>
<body>

<@s.actionerror />
<@s.fielderror />
<#if servers??>
<#if servers.isEmpty()>
	请至少上传一条数据
<#else>
请核对以下数据，以确保正确设定价格、数量等，确定后点击"提交"按钮。<br/>
<#assign x=0 />
<form action="${base}/afterlogin/updateOperatorPrice" method="post" enctype="multipart/form-data">
		<@jmesa.tableFacade
			id="uploadPrice" 
			items=servers
			toolbar="com.goodhope.goldselling.web.view.jmesa.EmptyToolBar"
			maxRows=servers.size()
			var="temp"
			>
			<@jmesa.htmlTable 
				width="600px"
				>		
				<@jmesa.htmlRow>
					<input type=hidden name=servers[${x}].country value="${temp.country}" />
					<input type=hidden name=servers[${x}].server value="${temp.server}" />
					<input type=hidden name=servers[${x}].faction value="${temp.faction}" />
					<input type=hidden name=servers[${x}].price value="${temp.price}" />
					<input type=hidden name=servers[${x}].priceLimit value="${temp.priceLimit}" />
					<input type=hidden name=servers[${x}].amountLimit value="${temp.amountLimit}" />
					<#assign x=x+1 />
					<@jmesa.htmlColumn property="country" title="游戏区" sortable=false filterable=false/>
					<@jmesa.htmlColumn property="server" title="游戏服务器" sortable=false filterable=false/>
					<@jmesa.htmlColumn property="faction" title="阵营" sortable=false filterable=false/>
					<@jmesa.htmlColumn property="price" title="单价" sortable=false filterable=false/>
					<@jmesa.htmlColumn property="priceLimit" title="价格上限" sortable=false filterable=false/>
					<@jmesa.htmlColumn property="amountLimit" title="数量下限" sortable=false filterable=false/>
				</@jmesa.htmlRow>
			</@jmesa.htmlTable> 
		</@jmesa.tableFacade>
		<input type="submit" value="提交"/>
<form>
</#if>
</#if>
</body>
</html>