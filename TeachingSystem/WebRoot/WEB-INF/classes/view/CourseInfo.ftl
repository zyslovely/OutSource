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
           <p style="min-width: 100px; float: left; line-height: 64px; font-size: 20px;">${course.name!""}</p>
           <#if isEachStudent==1&&course.teacherId==visitUserId>
           <a id="" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;width:180px; float: right;" href="javascript:void(0);;">编辑分组</a>
           </#if>
           <#if course.status==0&&course.teacherId==visitUserId>
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
                     <#--如果是多次课程-->
                     <#if courseScorePercent.percentType == 2>
                     <#list 1..courseScorePercent.objectCount as t>
                     <tr style="font-size: 16px;height: 70px;">
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 150px;">${courseScorePercent.name!""}</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 246px;">第${t}次课</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">
                           <#if courseScorePercent.teacherId == visitUserId>
                           <a  style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="/teach/score/?percentTypeId=${courseScorePercent.percentType!0}&courseId=${courseScorePercent.courseId!0}&stage=${t}">打分</a>
                           </#if>
                        </th>
                     </tr>
                     </#list>
                     <#else>
                     <tr style="font-size: 16px;height: 70px;">
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 150px;">${courseScorePercent.name!""}</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);width: 246px;">${courseScorePercent.percent!0}%</th>
                        <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">
                           <#if courseScorePercent.teacherId == visitUserId&&courseScorePercent.percentType!=4>
                           <a  style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="/teach/score/?percentTypeId=${courseScorePercent.percentType!0}&courseId=${courseScorePercent.courseId!0}">打分</a>
                           </#if>
                        </th>
                     </tr>
                     </#if>
                     </#list>
                     </#if>
                </tbody>
           </table>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">