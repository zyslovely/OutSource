<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "courseInfo" />
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
   <div style="float:left;width: 600px;height:400px;margin-top: 30px;margin-left:60px">
       <div style="border-bottom: 2px solid rgb(224, 224, 224); height: 64px;">
           <p style="width: 100px; float: left; line-height: 64px; font-size: 20px;">课程名称</p>
           <#if isPercentType==1>
           <a id="" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;width:180px; float: right;" href="javascript:void(0);;">编辑分组</a>
           </#if>
           <#if course.status==0>
           <a id="courseInfo_endCourse" data_id="${course.id!0}" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;width:180px; float: right;" href="javascript:void(0);;">结束课程</a>
           </#if>
       </div>
       <div style="border-bottom: 2px solid rgb(224, 224, 224); min-height: 64px;">
           <p style="margin-top:15px;margin-left:20px;">${course.description!""}</p>
       </div>
           <table style="width:600px">
                <tbody>
                     <#if courseScorePercents?exists>
                     <#list courseScorePercents as courseScorePercent>
                     <tr style="font-size: 20px;height: 70px;">
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 150px;">${courseScorePercent.name!""}</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 246px;">${courseScorePercent.percent!0}%</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">
                           <a  style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">打分</a>
                        </th>
                     </tr>
                     </#list>
                     </#if>
                </tbody>
           </table>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">