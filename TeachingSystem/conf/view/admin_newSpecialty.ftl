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
   <div style="margin-left:15%;width:75%;height:105px;margin-top: 20px;border-bottom: 2px solid rgb(224, 224, 224);">
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
                  <a id="newSpecialty_Create" style="margin-left: 20px;" href="javascript:void(0);;"><img src="/img/teachCreate/button_edit.png" style="width:174px;height:59px;"/></a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="float:left;margin-left:15%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/pic_list.png" style="width:195px;height:193px;"/>
   </div>
   <table style="float: left; margin-left: 40px; width: 500px; margin-top: 20px; font-size: 14px;">
       <thead>
            <tr>
               <th><p>专业名称</p></th>
               <th><p>专业拼音</p></th>
               <th><p>学期数量</p></th>
            </tr>
       </thead>
       <tbody>
            <#list specialties as specialty>
            <tr>
               <th><p>${specialty.specialty!""}</p></th>
               <th><p>${specialty.shortSpecialty!""}</p></th>
               <th><p>${specialty.semesterCount!0}</p></th>
            </tr>
            </#list>
       </tbody>
   </table> 
       
</body>
</html>
</#escape>
		
<#include "js.ftl">