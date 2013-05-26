<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<#escape x as x?html>
<#assign pageName = "webIndex" />
<#include "head.ftl">

<body style="min-width:1500px;min-height:900px">
   <div style="height:90px;width:100%;">
       <div style="margin-left:15%;margin-top:23px;">
           <img src="/img/webIndex/logo.png"/>
       </div>
   </div>
   <div style="min-height:810px;min-width:100%;background:url('/img/webIndex/bg.png') repeat scroll center top transparent;">
       <div style="padding-top: 40px; padding-left: 15%;">
          <p style="font-size: 20px; color: rgb(95, 95, 95);">数字媒体技术应用专业学业评价系统</p>
       </div>
       <div style="margin-left: 15%; margin-top: 46px;">
          <div style="float:left;width: 667px; height: 432px; background: url('/img/webIndex/pic_bg.png') no-repeat scroll center top transparent; ">
              <ul style="height: 421px; width: 657px; margin-left: 5px; margin-top: 5px;">
                 <li id="img_1" style="position: absolute;"> 
                    <img src="/img/webIndex/pic1.png" style=""/>
                 </li>
                 <li id="img_2" style="position: absolute;display:none;">
                    <img src="/img/webIndex/pic2.png" style=""/>
                 </li>
              </ul>
              <div style="margin-left: 5px; background: url('/img/webIndex/pic_mask.png') no-repeat scroll center top transparent; width: 657px; height: 50px; position: relative; top: -50px; border-radius: 5px 5px 5px 5px;">
                   <span style="position: absolute; color: white; margin: 10px 0px 0px 40px; font-size: 18px;">教学楼</span>
                   <span id="dot_1" style="cursor: pointer;background: url('/img/webIndex/pic_mask_dot_select.png') no-repeat scroll center top transparent; height: 14px; position: absolute; width: 14px; margin-top: 19px; margin-left: 88%;"></span>
                   <span id="dot_2" style="cursor: pointer;background: url('/img/webIndex/pic_mask_dot.png') no-repeat scroll center top transparent; height: 14px; position: absolute; width: 14px; margin-top: 19px; margin-left: 92%;"></span>
              </div>
          </div>
          <div style="float:left;margin-left:40px;width: 358px; height: 425px;margin-left:75px;background: url('/img/webIndex/login_bg.png') no-repeat scroll center top transparent; ">
              <p style="width: 100%; text-align: center; margin-top: 27px; color: rgb(95, 95, 95); font-size: 27px;">登录</p>
              <span style="height: 2px; background: url('/img/webIndex/line.png') repeat-x scroll center top transparent; position: absolute; margin-top: 14px; margin-left: 8px; width: 344px;"></span>
              <div id="username_div" style="background: url('/img/webIndex/input_bg_focus.png') no-repeat scroll center top transparent;  width: 312px; margin-left: 22px; margin-top: 50px;padding-top:15px\9; ">
                  <input id="username" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;"/>
              </div>
              <div id="password_div" style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 312px; margin-left: 22px; margin-top: 15px;padding-top:15px\9; " >
                  <input id="password" name="password" type="password" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;"/>
              </div>
              <div style="margin-top: 20px; margin-left: 35px; height: 35px;">
                  <p style="width:150px;float:left;">
                     <img src="/img/webIndex/radio_button_select.png" style="width: 18px; height: 18px;cursor: pointer;" id="remember_me" data-remember="1" name="remember" value="1"/>
                     <span style="margin-left: 10px; position: absolute;">记住我<span>
                  </p>
                  <span style="float: right; margin-right: 50px;"><a href="">忘记密码?</a></span>
              </div>
              <div style="background: url('/img/webIndex/login_button_normal.png') no-repeat scroll center top transparent; width: 299px; margin-top: 10px; margin-left: 27px;">
                  <input id="submit_login" type="button" style="cursor: pointer;border: 0px none; background: none repeat scroll 0% 0% transparent; font-size: 30px; width: 100%; text-align: center; height: 60px; color: #ffffff;" value="登录"/>
              </div>
          </div>
       </div>
   </div>
</body>
</html>
</#escape>		
<#include "js.ftl">
