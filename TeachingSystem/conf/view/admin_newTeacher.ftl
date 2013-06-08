<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newTeacher" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div id="normal_newTeacher" style="margin-left:15%;width:75%;height:105px;margin-top: 20px;">
       <#if teacherList?exists>
       <table style="" cellspacing="0">
            <thead style="height: 60px;">
            <tr>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">姓名</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">账号</th>
               <th style="border-bottom: 2px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(123, 123, 123);">密码</th>
            </tr>
            </thead>
            <tbody>
                 
                 <#list teacherList as teacher>
                  <tr style="height:40px;font-size: 16px;border-bottom: 2px solid rgb(224, 224, 224); ">

                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${teacher.name!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${teacher.userName!""}</th>
                  <th style="border-bottom: 2px solid rgb(224, 224, 224);color: rgb(123, 123, 123);">${teacher.password!""}</th>
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
       </#if>
   </div>
   <div id="create_newTeacher_Profile" style="margin-left:30%;height:105px;margin-top: 20px;width: 500px;display:none;">
       <table>
            <tbody>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">老师姓名</p></th>
                   <th style="">
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newTeacher_name" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="老师姓名"/>
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">账号</p></th>
                   <th style="">
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newTeacher_username" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="账号"/>
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">密码</p></th>
                   <th>
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newTeacher_password" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="密码"/>
                      </div>
                   </th>
                </tr>
            </tbody>
       </table>
       <div style="width: 500px; margin-left: 126px;" > <a id="newTeacher_Submit" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; height: 59px; display: block; color: white; font-size: 20px; line-height: 55px; margin-left: 20px; text-align: center; width: 174px;" href="javascript:void(0);;">添加老师账号</a></div>
   </div>
       
</body>
</html>
</#escape>
		
<#include "js.ftl">