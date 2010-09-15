<html>
<head>
<script src="${base}/js/galleria.js"></script>
<script>Galleria.loadTheme('${base}/galleria/themes/classic/galleria.classic.js');</script>
<script>
$(document).ready(function(){
$('.images').galleria();
});
</script>
</head>

<body>

<div class="images">

<#list pictures as picture >
 	<img src="${base}/${picture.location}" />

</#list>
</div>

 
</body>



</html>