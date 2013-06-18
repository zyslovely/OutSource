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
   <div style="height: 105px; border-bottom: 1px solid rgb(224, 224, 224); width: 1024px; margin: 15px auto 0px;">
       <div style="margin-left: 10%;">
           <ul style="height:30px;width:100%;">
              <li style="float:left;width:300px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">选择专业:</p></li>
              <li style="float:left;width:300px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">选择班级:</p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div class="select-wrapper wide">
                          <select class="select-receiver" id="newStudent_Specialty_list" name="receiver">
                              <#if specialtyId lt 0>
                                   <option value="0" selected="selected"></option>
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
                          <select class="select-receiver" id="newStudent_Class_list" name="receiver"  >
                              <#if classId lt 0>
                                   <option value="0" selected="selected"></option>
                              </#if>
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
   <#if studentList?exists>
   <div style="margin-left:15%;width:75%;height:105px;margin-top: 20px;">
      
       <table cellspacing="0" style="">
            <thead style="height: 60px;">
            <tr>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">专业</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">班级</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">学生姓名</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">账号</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">密码</th>  
                <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">操作</th>   
            </tr></thead>
            <tbody>
                 
                 <#list studentList as student>
                  <tr style="height:40px;font-size: 16px;border-bottom: 1px solid rgb(224, 224, 224); ">
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${student.specialtyName!""}</th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${student.className!""}</th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);"><a target="_blank" style="color: rgb(139, 139, 139);" href="/teach/index/?userId=${student.userId!0}">${student.name!""}</a></th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${student.userName!""}</th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${student.password!""}</th>
                  <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">
                     <a href="javascript:void(0);" class="admin_newStudent_delete" data_id="${student.userId!0}">删除</a>
                     <#if student.status==0><a href="javascript:void(0);" class="admin_newStudent_end" data_id="${student.userId!0}">结束学业</a></#if>
                  </th>
         
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
      
       <div style="margin-top: 10px;"><div id="jpage_student"></div></div>
        </#if>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">
