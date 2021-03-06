<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "EachStudentScore" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div id="EachStudentScoreDiv" data_courseId="${courseId!0}"  style="width: 75%; height: 105px; margin-left: auto; margin-top: 20px;">
       <#if coursePercentTypeGroupStudentVO?exists>
       <table style="width:500px" cellspacing="0">
            <thead style="height: 60px;">
            <tr>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">姓名</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">成绩</th>
            </tr>
            </thead>
            <tbody>
                 <#list coursePercentTypeGroupStudentVO as student>
                  <tr style="height:40px;font-size: 16px;border-bottom: 1px solid rgb(224, 224, 224); ">
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${student.name!""}</th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);"><input type="text" value="${student.score!18}" style="text-align:center;" class="EachStudentScore_input" data_userid="${student.userId!0}" data_groupId="${student.groupId!0}" "/></th>
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
       </#if>
       <p style="color: rgb(139, 139, 139); width: 500px; text-align: center; margin-top: 5px;">tips:打分成绩只允许在0~100分的区间内</p>
       <div style="float: left; width: 200px; margin: 20px auto auto 150px;">
           <a  class="w-btn" id="EachStudentScore_save" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">确定保存</a>
     </div>
   </div>
   
</body>
</html>
</#escape>
		
<#include "js.ftl">