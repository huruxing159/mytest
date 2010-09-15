<html>
<head>

</head>
<body>
	<font color="green" >
	<#if (!actionMessages.isEmpty())>
		<#list actionMessages as actionMessage>
			<div>${actionMessage}</div>
		</#list>
   </#if>
</font>

</body>






</html>