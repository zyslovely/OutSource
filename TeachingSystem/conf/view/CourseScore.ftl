<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "courseScore" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
    <div id="CourseScoreDiv" data_percentType=${percentTypeId!0} data_stage="${stage!0}" data_id="${courseId!0}" style="height: 105px; width: 1024px; margin: auto;">
       <#if studentList?exists>
       <table style="width:500px" cellspacing="0">
            <thead style="height: 60px;">
            <tr>
               <th style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">姓名</th>
               <th style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">成绩</th>
            </tr>
            </thead>
            <tbody>
                 <#list studentList as student>
                  <tr style="height:40px;font-size: 16px;border-bottom: 1px solid rgb(224, 224, 224); ">
                  <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.name!""}</th>
                  <th style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(123, 123, 123);"><input type="text" value="${student.score!0}" style="text-align:center;" class="courseScore_input" data_id="${student.userId!0}"/></th>
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
       </#if>
       <div style="float: left; width: 200px; margin: 20px auto auto 150px;">
           <a  class="w-btn" id="courseScore_save" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">确定保存</a>
     </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">