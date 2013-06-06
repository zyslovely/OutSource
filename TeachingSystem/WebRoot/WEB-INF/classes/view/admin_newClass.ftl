<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newClass" />
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
              <li style="float:left;width:300px;"><p>输入班级名称:</p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div class="select-wrapper wide">
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
                          <input id="newClass_Create_Name"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="用户名"/>
                      </div>
              </li>
              <li style="float:left;width:200px;">
                  <a id="newClass_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">创建班级</a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="float:left;margin-left:15%;height:193px;margin-top: 30px;">
       <img src="/img/newClass/class.png" style="width:195px;height:193px;"/>
   </div>
   <#if classList?exists>
   <table style="float: left; margin-left: 40px; width: 500px; margin-top: 20px; font-size: 20px;">
       <thead style="height:60px;">
            <tr>
               <th style="color: rgb(123, 123, 123);width:190px;">班级</th>
               <th style="color: rgb(123, 123, 123);width:190px;">学期数量</th>
               <th style="color: rgb(123, 123, 123);width:190px;">学生数量</th>
            </tr>
       </thead>
       <tbody style="font-size: 16px;">
            <#list classList as class>
            <tr style="">
               <th style="color: rgb(123, 123, 123);width:190px;">${class.name!""}</th>
               <th style="color: rgb(123, 123, 123);width:190px;">${class.semesterCount!0}</th>
               <th style="color: rgb(123, 123, 123);width:190px;">${class.studentCount!0}</th>
            </tr>
            </#list>
       </tbody>
   </table> 
   </#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">