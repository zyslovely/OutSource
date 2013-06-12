<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "feedback" />
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
   <div id="normal_feedback" style="margin-left:5%;width:75%;height:105px;margin: 20px auto 0px;">
   <#if feedbacks?exists>
       <ul>
       <#list feedbacks as feedback>
       
       <li style="margin: auto; width: 660px; border-bottom: 2px solid rgb(224, 224, 224); min-height: 120px; display: block;">
          <div style="margin:10px 0 0px;min-height:38px;">
             <p style="width: 100px; font-size: 25px; display: inline;color: rgb(123, 123, 123);">${feedback.fromName!""}</p>
             <p style="float: right; margin-top: 10px;color: rgb(123, 123, 123);">${feedback.createTimeStr!""}</p>
          </div>
          <div style="min-height:62px;">
             <p style="word-wrap:break-word; word-break:normal;color: rgb(123, 123, 123);">${feedback.content!""}</p>
          </div>
          <div style="min-height:20px;">
             <#if feedback.course?exists>
             <p style="display: inline;"><a href="/teach/course/${feedback.course.id!0}/">来自${feedback.course.name!""}</a></p>
             </#if>
             <p style="float: right; margin-right: 25px;"><a onClick="feedbackBackClick(${feedback.id!0},${feedback.course.id!0},${feedback.fromUserId!0});return;" href="javascript:void(0);;"">回复</a></p>
          </div>
       </li>
       </#list>
       </ul>
   </#if>
   </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">