<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="float:left;margin-left:15%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/pic_list.png" style="width:195px;height:193px;"/>
   </div>
   <div>
       <ul>
       <#list courseList as coursevo>
           <li><a href="">${coursevo.course.name}</a></li>
       </#list> 
       </ul>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">