<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<#escape x as x?html>
<#assign pageName = "webIndex" />
<#include "head.ftl">
<script type="text/javascript">

if(${error!0}==1){
  alert("账号或密码错误");
}
</script>
<body style="background:url('/img/webIndex/bg.png') repeat scroll center top transparent">
   <div style="background-color:white;">
   <div style="height: 60px; width: 980px; margin: auto;">
       <div style=" padding-top: 10px;">
           <img src="/img/webIndex/logo.png"/>
       </div>
   </div>
   </div>
   <section style="width:1024px; margin: auto;">
       <div style="padding-top: 20px; ">
          <p style="color: rgb(95, 95, 95); font-size: 22px; font-weight: bold;">数字媒体技术应用专业学业评价系统</p>
       </div>
       <div style=" margin-top: 26px;">
          <div style="float:left;width: 615px; height: 398px; background: url('/img/webIndex/pic_bg.png') no-repeat scroll center top transparent; ">
              <ul style="height: 387px; width: 605px; margin-left: 5px; margin-top: 5px;">
                 <li id="img_1" style="position: absolute;"> 
                    <img src="/img/webIndex/pic1.jpg" style=""/>
                 </li>
                 <li id="img_2" style="position: absolute;display:none;">
                    <img src="/img/webIndex/pic2.jpg" style=""/>
                 </li>
              </ul>
              <div style="margin-left: 5px; background: url('/img/webIndex/pic_mask.png') no-repeat scroll center top transparent; width: 605px; height: 52px; position: relative; top: -52px; border-radius: 5px 5px 5px 5px;">
                   <span style="position: absolute; color: white; margin: 10px 0px 0px 40px; font-size: 18px;">学生作品</span>
                   <span id="dot_1" style="cursor: pointer;background: url('/img/webIndex/pic_mask_dot_select.png') no-repeat scroll center top transparent; height: 14px; position: absolute; width: 14px; margin-top: 19px; margin-left: 88%;"></span>
                   <span id="dot_2" style="cursor: pointer;background: url('/img/webIndex/pic_mask_dot.png') no-repeat scroll center top transparent; height: 14px; position: absolute; width: 14px; margin-top: 19px; margin-left: 92%;"></span>
              </div>
          </div>
          <div style="float:left;width: 338px; height: 401px;margin-left:1%;background: url('/img/webIndex/login_bg.png') no-repeat scroll center top transparent; ">
              <p style="width: 100%; text-align: center; margin-top: 27px; color: rgb(95, 95, 95); font-size: 22px;">登录</p>
              <span style="height: 2px; background: url('/img/webIndex/line.png') repeat-x scroll center top transparent; position: absolute; margin-top: 14px; margin-left: 8px; width: 323px;"></span>
              <div id="username_div" style="background: url('/img/webIndex/input_bg_focus.png') no-repeat scroll center top transparent;  width: 295px; margin-left: 22px; margin-top: 50px;padding-top:15px\9; ">
                  <input id="username" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="用户名"/>
              </div>
              <div id="password_div" style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px;height:57px; margin-left: 22px; margin-top: 15px;padding-top:15px\9; " >
                  <input id="password" name="password" type="password" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" />
              </div>
              <div style="margin-top: 20px; margin-left: 35px; height: 35px;">
                  <p style="width:150px;float:left;">
                     <img src="/img/webIndex/radio_button_select.png" style="width: 18px; height: 18px;cursor: pointer;" id="remember_me" data-remember="1" name="remember" value="1"/>
                     <span style="margin-left: 10px; position: absolute; color: rgb(95, 95, 95); font-size: 15px; line-height: 15px;">记住我<span>
                  </p>
                  <span style="font-size: 15px; float: right; margin-right: 35px;"><a href="javascript:void(0);;" id="forget_pass">忘记密码?</a></span>
              </div>
              <div style="background: url('/img/webIndex/login_button_normal.png') no-repeat scroll center top transparent;height:60px; width: 283px;margin-left:27px;margin-left:16px\9">
                  <input id="submit_login" type="button" style="cursor: pointer;border: 0px none; background: none repeat scroll 0% 0% transparent; font-size: 20px; width: 100%; text-align: center; height: 60px; color: #ffffff;" value="登 录"/>
              </div>
          </div>
       </div>
   </section>
   
</body>
</html>
</#escape>		
<#include "js.ftl">
