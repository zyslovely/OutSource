<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachSemester" />
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
       <div style="margin-left: 5%;">
           <ul style="height:30px;width:100%;">
              <li style="float:left;width:300px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">新学期名称:</p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div  style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                          <input onClick="if(this.value=='学期名称')this.value=''"  id="newSemester_Name"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 14px;color: rgb(143, 143, 143);" value="学期名称"/>
                      </div>
              </li>
              <li style="float:left;width:200px;">
                  <a   class="w-btn" id="newSemester_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; line-height: 55px;" href="javascript:void(0);;">新建学期</a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="width: 1024px; margin: auto;">
   <#if semesterList?exists>
   <div style="float:left;margin-left:5%;height:160px;margin-top: 30px;">
       <img src="/img/newSpecialty/specialty.png" style="width:160px;height:160px;"/>
   </div>
   
   <table cellspacing="0" style="float: left; margin-left: 80px; width: 500px; margin-top: 20px; font-size: 20px;cellspacing:0px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">学期名称</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">操作</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list semesterList as semester>
            <tr style="height: 40px;">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${semester.name!""}</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a href="javascript:void(0);" class="new_teachSemester_delete" data_id="${semester.id!0}">删除</a></th>
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