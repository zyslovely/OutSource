<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newStudent" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="margin-left:15%;width:75%;height:105px;margin-top: 20px;border-bottom: 2px solid rgb(224, 224, 224);">
       <div style="margin-left: 10%;">
           <ul style="height:30px;width:100%;">
              <li style="float:left;width:300px;"><p>选择专业:</p></li>
              <li style="float:left;width:300px;"><p>选择班级:</p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div class="select-wrapper wide">
                          <select class="select-receiver" id="newStudent_Specialty_list" name="receiver" onChange="newStudentCreateSpecialtyChange(this);return;">
                              <#if specialtyId lt 0>
                                   <option value="0"></option>
                              </#if>
                              <#if specialties?exists>
                              <#list specialties as specialty>
                                  <option value="${specialty.id!0}" <#if specialtyId == specialty.id>selected="selected"</#if> >${specialty.specialty!""}</option>
                              </#list>
                              </#if>
                          </select>
                 </div>
              </li>
              <li style="float:left;width:300px;">
                 <div class="select-wrapper wide">
                          <select class="select-receiver" id="newStudent_Class_list" name="receiver" onChange="newStudentCreateClassChange(this);return;" >
                              <#if classList?exists>
                              <#list classList as class>
                                   <option value="${class.id!0}" <#if classId == class.id>selected="selected"</#if>>${class.name!""}</option>
                              </#list>
                              </#if>
                          </select>
                 </div>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="margin-left:15%;width:75%;height:105px;margin-top: 20px;">
      <#if studentList?exists>
       <table cellspacing="0" style="">
            <thead style="height: 60px;">
            <tr>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">专业</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">班级</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">学生姓名</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">账号</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">密码</th></tr></thead>
            <tbody>
                 
                 <#list studentList as student>
                  <tr style="height:40px;font-size: 16px;border-bottom: 2px solid rgb(224, 224, 224); ">
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.specialtyName!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.className!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.name!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.userName!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${student.password!""}</th>
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
       </#if>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">