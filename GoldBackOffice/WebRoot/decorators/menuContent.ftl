<html>
<head>
</head>
<body>
<#if menus??>
 <#assign x=0 />
 <#list menus  as menu>
	<#if x%2==0>
        <li class="tree_li">
     </#if>
		<span class="list_img"><img src="${base}/images/list_img.gif" /></span><a href="${base}${menu.authorityURI?if_exists}">${menu.authorityName?if_exists}</a> 
	<#if x%2==1>
        </li>
     </#if> 
	<#assign x=x+1/>
 </#list>
 <#if x%2==0>
	</li>
 </#if>
 
 </#if>
</body>
</html>
