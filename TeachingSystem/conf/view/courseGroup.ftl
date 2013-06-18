<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "courseGroup" />
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
   <div id="courseGroup_DIV" data_id="${courseId!0}" style="float: left; min-height: 193px; margin-top: 10px; width: 240px; margin-left: 5%; background-color: rgb(247, 247, 247);">
       <div style="border-bottom: 1px solid rgb(224, 224, 224); height: 31px; padding-top: 12px; margin: auto; width: 200px;">
           <p style="font-size: 15px;color: rgb(90, 90, 90);">未分组学生列表</p>
       </div>
       <ul style="margin: auto; width: 200px;">
          <#if courseGroupStudentVOs?exists>
          <#list courseGroupStudentVOs as courseGroupStudent>
          <#if !courseGroupStudent.coursePercentTypeGroupStudent?exists>
          <li style="border-bottom: 1px solid rgb(224, 224, 224);min-height:30px;padding-top:5px">
             <#if courseGroupStudent.profile?exists>
             <p style="color: rgb(139, 139, 139);width: 100px; display: inline; font-size: 14px;">${courseGroupStudent.profile.name!""}</p>
             </#if>
             <p style="color: rgb(139, 139, 139);width: 60px; display: inline; float: right; ">
                <span style="margin-right:2px;">组长:</span><input class="courseGroup_checkbox_leader" data_id="${courseGroupStudent.profile.userId!0}" type="checkbox" style="height: 16px; width: 16px;"/>
             </p>
             <p style="color: rgb(139, 139, 139);width: 60px; display: inline; float: right; ">
                <span style="margin-right:2px;">组员:</span><input class="courseGroup_checkbox_teamer" data_id="${courseGroupStudent.profile.userId!0}" type="checkbox" style="height: 16px; width: 16px;"/>
             </p>
          </li>
          </#if>
          </#list>
          </#if>
       </ul>
       <div style="margin-top: 15px; width: 185px;">
           <a class="w-btn" id="courseGroup_New" style="margin-left: 20px; display: block; text-align: center; color: white; float: right; background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px; margin-top: 19px;" href="javascript:void(0);;">新建分组</a>
       </div>
   </div>
   <table cellspacing="0" style="float: left; margin-left: 40px; width: 600px; margin-top: 20px; font-size: 20px;cellspacing:0px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">分组</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">姓名</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">是否组长</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">编辑</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#if coursePercentTypeGroups?exists>
            <#list coursePercentTypeGroups as group>
            <#assign trindex = 0 />
            <#if courseGroupStudentVOs?exists>
            <#list courseGroupStudentVOs as courseGroupStudent>
            <#if courseGroupStudent.coursePercentTypeGroupStudent?exists>
            <#if group.id == courseGroupStudent.coursePercentTypeGroupStudent.groupId>
            <#if trindex==0>
            <tr style="height:30px;">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">第${group_index + 1}组</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"></th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"></th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a href="javascript:void(0);;" onClick="deleteGroup(${group.id!0}); return;">删除</a></th>
            </tr>  
            </#if>
            <tr style="height:50px;">
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"></th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><#if courseGroupStudent.profile?exists>${courseGroupStudent.profile.name!""}</#if></th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><#if courseGroupStudent.coursePercentTypeGroupStudent.level==1>是<#else>否</#if></th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"></th>
            </tr>  
            <#assign trindex = 1 />
            </#if>
            </#if>
            </#list>
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