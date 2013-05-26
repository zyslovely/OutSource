<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "admin_newPercent" />
<#include "head.ftl">
	
<body >
<form action="" method="post" target="_self"/>
<div>
   <span>配件类型:</span>
   <div>
   <#if accessoryInfos?exists>
      <select name="accessoryInfoId">
      <#list accessoryInfos as info>
         <option value ="${info.id!0}" >${info.accessoryInfoName!""}</option>
      </#list>
      </select>
   </#if>
   </div>
   <span>配件名称:</span>
   <input type="text" value="" name="accessoryName"/>
   <input type="submit" value="确定"/>
</div>
</form>

</body>
</html>
</#escape>
		
<#include "js.ftl">
<script>

</script>