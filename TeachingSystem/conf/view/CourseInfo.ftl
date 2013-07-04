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
   <div style="width: 1024px; margin: auto;">
   <div style="float:left;margin-left:5%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/pic_list.png" style="width:195px;height:193px;"/>
   </div>
   <div style="float:left;width: 698px;height:400px;margin-top: 30px;margin-left:60px">
       <div style="border-bottom: 1px solid rgb(224, 224, 224); height: 64px;">
           <p style="min-width: 100px; float: left; line-height: 64px; font-size: 18px;">${course.name!""}</p>
           <#if isEachStudent==1&&course.teacherId==visitUserId>
           <a class="w-btn" style="margin-left: 20px; display: block; text-align: center; color: white; float: right; background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px; margin-top: 19px;float: right;" href="/teach/course/${courseId!0}/group/create/">编辑分组</a>
           </#if>
           <#if course.status==0&&course.teacherId==visitUserId>
           <a class="w-btn" id="courseInfo_endCourse" data_id="${course.id!0}" style="margin-left: 20px; display: block; text-align: center; color: white; float: right; background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px; margin-top: 19px;" href="javascript:void(0);;">结束课程</a>
           </#if>
       </div>
       <div style="border-bottom: 1px solid rgb(224, 224, 224); min-height: 64px;">
           <p style="margin-top:15px;margin-left:20px;">${course.description!""}</p>
       </div>
           <table style="width:698px">
                <thead style="height: 60px;">
            <tr>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">评分点</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">成绩构成</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">打分教师</th>
               <#if level==1||level==2>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">打分</th>
               <#else>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">成绩</th>
               </#if>
            </tr>
            </thead>
                <tbody>
                     <#if courseScorePercents?exists>
                     <#list courseScorePercents as courseScorePercent>
                     <#--如果是多次课程-->
                     <#if courseScorePercent.percentType == 2>
                     <#list 1..courseScorePercent.objectCount as t>
                     <tr style="font-size: 16px;height: 70px;">
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 150px;">${courseScorePercent.name!""}</th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 246px;">第${t}次课</th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 246px;">${courseScorePercent.teacherName!""}</th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">
                           <#if (courseScorePercent.teacherId == visitUserId||visitUserId==course.teacherId)&&course.status=0>
                           <a class="w-btn" style="margin:10px auto 0; display: block; text-align: center; color: white;background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px;" href="/teach/score/?percentTypeId=${courseScorePercent.percentType!0}&courseId=${courseScorePercent.courseId!0}&stage=${t}">打分</a>
                           <#elseif level==0>
                           <#if coursePercentTypeStages?exists>
                           <#list  coursePercentTypeStages as stage>
                           <#if stage.stageIndex==t>
                           <p style="color: rgb(139, 139, 139);">${stage.score!0}</p>
                           <#break>
                           </#if>
                           </#list>
                           </#if>
                           </#if>
                        </th>
                     </tr>
                     </#list>
                     <#else>
                     <tr style="font-size: 16px;height: 70px;">
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 150px;">${courseScorePercent.name!""}</th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 246px;">${courseScorePercent.percent!0}%</th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);width: 246px;"><#if courseScorePercent.percentType!=4>${courseScorePercent.teacherName!""}<#else>互评小组</#if></th>
                        <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">
                           <#if (courseScorePercent.teacherId == visitUserId||visitUserId==course.teacherId)&&courseScorePercent.percentType!=4&&course.status=0>
                           
                           <a class="w-btn" style="display: block; text-align: center; color: white; margin:10px auto 0; background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px; " href="/teach/score/?percentTypeId=${courseScorePercent.percentType!0}&courseId=${courseScorePercent.courseId!0}">打分</a>
                           
                           </#if>
                        </th>
                     </tr>
                     </#if>
                     </#list>
                     </#if>
                </tbody>
           </table>
   </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">