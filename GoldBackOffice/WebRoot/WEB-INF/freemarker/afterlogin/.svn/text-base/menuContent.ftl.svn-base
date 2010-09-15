<html>
<head>
</head>
<body>
<#if menus??>
	<#assign x=0 />
	<#list menus as menu>
	  <#if x%2==0>
	        <li class="tree_li">
			<span class="list_img"><img src="${base}/images/list_img.gif" /></span>${menu.authorityName} 
			<#if x=menus.size()-1 >
			 </li>
			</#if>
	  <#else>
			<span class="list_img"><img src="${base}/images/list_img.gif" /></span>${menu.authorityName}</li>
	  </#if>
	  <#assign x=x+1/>
	</#list>
</#if>
</body>
</html>
