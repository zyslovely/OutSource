<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newSchoolInfo" />
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
              <li style="float:left;width:300px;"><p>课程类型名称:</p></li>
              <li style="float:left;width:300px;"><p>评分标准 <a href="javascript:void(0);;" style="margin-left:10px">增加评分类型</a></p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:300px;">
                 <div  style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                          <input id="newSpecialty_Name"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="专业名称"/>
                      </div>
              </li>
              <li style="float:left;width:300px;">
                 <div  style="margin-top: 20px; padding-top:15px\9; ">
                      <select style="text-align:center;"><#include "CoursePercentType.ftl"/></select>
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
 
       
</body>
</html>
</#escape>
		
<#include "js.ftl">