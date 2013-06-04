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
                          <select class="select-receiver" id="newStudent_Create_Specialty" name="receiver" onChange="newStudentCreateSpecialtyChange(this);return;">
                              <#list specialties as specialty>
                                  <option value="${specialty.id!0}">${specialty.specialty!""}</option>
                              </#list>
                          </select>
                 </div>
              </li>
              <li style="float:left;width:300px;">
                 <div class="select-wrapper wide">
                          <select class="select-receiver" id="newClass_Create_Specialty" name="receiver" >
                              
                          </select>
                 </div>
              </li>
           </ul>
       </div>
       
   </div> 
       
</body>
</html>
</#escape>
		
<#include "js.ftl">