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
   <table style="float: left; width: 600px; margin-top: 40px; margin-left: 60px;">
        <tbody>
             <#if courseList?exists>
             <#list courseList as coursevo>
             <tr style="height: 59px; font-size: 20px;">
                <td style="width: 200px;"><a href="" style="color:black;">${coursevo.course.name}</a></td>
                <td style="width: 100px;">${coursevo.class1.name}</td>
                <td style="width: 100px;">${coursevo.teacher.name}</td>
                <td style="width: 174px;"><a href=""><img src="/img/teachCreate/button_edit.png" style="width:174px;height:59px;"/></a></td>
             </tr>
             </#list>
             </#if>
        </tbody>
   </table>
       
</body>
</html>
</#escape>
		
<#include "js.ftl">