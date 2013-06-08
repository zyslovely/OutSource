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
   <#if courseList?exists>
   <table style="float: left; width: 600px; margin-top: 20px; margin-left: 60px;">
        <thead>
             <tr style="height: 40px; font-size: 20px;">
                <td style="width: 100px;"><a href="" style="color:black;color: rgb(123, 123, 123);">课程名称</a></td>
                <td style="color: rgb(123, 123, 123);width: 100px;">班级名称</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">老师姓名</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">状态</td>
             </tr>
        </thead>
        <tbody>
             
             <#list courseList as coursevo>
             <tr style="height: 40px; font-size: 16px;">
                <td style="width: 100px;"><a href="/teach/course/${coursevo.course.id}/" style="color:black;color: rgb(123, 123, 123);">${coursevo.course.name}</a></td>
                <td style="color: rgb(123, 123, 123);width: 100px;">${coursevo.class1.name}</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">${coursevo.teacher.name}</td>
                <td style="width: 100px;<#if coursevo.course.status==0>color:green<#else>color:red</#if>"><#if coursevo.course.status==0>进行中<#else>已结束</#if></td>
             </tr>
             </#list>
             
        </tbody>
   </table>
   </#if> 
</body>
</html>
</#escape>
		
<#include "js.ftl">