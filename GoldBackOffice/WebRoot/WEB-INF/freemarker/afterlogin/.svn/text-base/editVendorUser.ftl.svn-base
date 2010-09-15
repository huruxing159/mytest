<html>
<head>
</head>

<body>
<#if !userMap.isEmpty()>
<form action="${base}/afterlogin/modifyVendorUser" method="post" >
供应商名：${vendor.name}
<input type="hidden" name="vendorId" value="${vendor.id?c}"/>
<br/>
<br/>
<br/>

<#list userMap?keys as key>
     <input type="checkbox" ${userMap.get(key)}  name="users" value="${key}" />${key}
     <br/>
</#list>
<br/>
<input  value="修改" type="submit" />

</form>
</#if>
</body>



</html>