<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newClass" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px;}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="height: 105px; border-bottom: 1px solid rgb(224, 224, 224); width: 1024px; margin: 15px auto 0px;">
       <div style="width: 800px;  margin-left: 5%;">
           <ul style="height:30px;">
              <li style="float:left;width:200px;"><p style="color: rgb(106, 106, 106);padding-left: 7px;font-size: 18px;">选择专业:</p></li>
              <li style="float:left;width:300px;"><p style="color: rgb(106, 106, 106);padding-left: 7px;font-size: 18px;">班级编号:</p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;">
              <li style="float:left;width:200px;">
                 <div class="select-wrapper wide" style="margin-top: 11px;">
                          <select class="select-receiver" id="newClass_Create_Specialty" name="receiver" onChange="newSpecialtyCreateClassChange(this);return;">
                             <#if specialtyId <0>
                                <option value="0" selected="selected"></option>
                             </#if>
                              <#if specialties?exists>
                              <#list specialties as specialty>
                                  <option value="${specialty.id!0}" <#if specialtyId==specialty.id>selected="selected"</#if>>${specialty.specialty!""}</option>
                              </#list>
                              </#if>
                          </select>
                      </div>
              </li>
              <li style="float:left;width:300px;">
                 <div  style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                          <input onClick="if(this.value=='编号')this.value=''" id="newClass_Create_Name"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="编号"/>
                      </div>
              </li>
              <li style="float:left;width:200px;">
                  <a class="w-btn" id="newClass_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">创建班级</a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="width: 1024px; margin: auto;">
   <#if classList?exists>
   <div style="float:left;margin-left:5%;height:160px;margin-top: 30px;">
       <img src="/img/newClass/class.png" style="width:160px;height:160px;"/>
   </div>
   
   <table cellspacing="0" style="float: left; margin-left: 80px; width: 500px; margin-top: 20px; font-size: 20px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">班级</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">学期数量</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">学生数量</th>
                <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">操作</th>
            </tr>
       </thead>
       <tbody style="font-size: 16px;">
            <#list classList as class>
            <tr style="height:40px">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${class.shortSpecialty!""}${class.name!""}</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${class.semesterCount!0}</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${class.studentCount!0}</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a href="javascript:void(0);" class="admin_newClass_delete" data_id="${class.id!0}">删除</a></th>
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
