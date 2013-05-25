<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}

</style>
	
<body style="height:1000px;">
    
      ${levelName!''}
      <#if level = 0>
      
      <#elseif level=1>
      <#elseif level=2>
      <#elseif level=3>
      </#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">

<script>


</script>