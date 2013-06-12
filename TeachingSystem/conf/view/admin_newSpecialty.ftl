<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newSpecialty" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="height: 105px; border-bottom: 2px solid rgb(224, 224, 224); width: 1024px; margin: 15px auto 0px;">
       <div style="margin-left: 10%;">
           <ul style="height:30px;width:100%;">
              <li style="float:left;width:300px;"><p>专业名称:</p></li>
              <li style="float:left;width:300px;"><p>专业名称缩写:</p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div  style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                          <input id="newSpecialty_Name"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="专业名称"/>
                      </div>
              </li>
              <li style="float:left;width:300px;">
                 <div  style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                          <input id="newSpecialty_ShortName"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="专业拼音"/>
                      </div>
              </li>
              <li style="float:left;width:200px;">
                  <a id="newSpecialty_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">新建专业</a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="width: 1024px; margin: auto;">
   <div style="float:left;margin-left:5%;height:193px;margin-top: 30px;">
       <img src="/img/newSpecialty/specialty.png" style="width:195px;height:193px;"/>
   </div>
   <table cellspacing="0" style="float: left; margin-left: 80px; width: 500px; margin-top: 20px; font-size: 20px;cellspacing:0px;">
       <thead style="height:60px;">
            <tr>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">专业名称</th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">专业拼音</th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">学期数</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list specialties as specialty>
            <tr style="height: 40px;">
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">${specialty.specialty!""}</th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">${specialty.shortSpecialty!""}</th>
               <th style="color: rgb(123, 123, 123);width:190px;border-bottom: 2px solid rgb(224, 224, 224);">${specialty.semesterCount!0}</th>
            </tr>
            </#list>
       </tbody>
   </table> 
       </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">