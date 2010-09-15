<html>
<head>
</head>

<body>
<form action="${base}/afterlogin/changeRole" method="post" >
用户名：${changeUser.username}
<input type="hidden" name="userId" value="${changeUser.id?c}"/>
<br/>
<br/>
<br/>

<#list roleMap?keys as key>
     <input type="checkbox" ${roleMap.get(key)}  name="roleChanged" value="${key}" />${key}
     <br/>
</#list>
<br/>
<input  value="修改" type="submit" />

</form>
</body>



</html>