<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newHeadImage" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body style="">
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="height: 280px; border-bottom: 1px solid rgb(224, 224, 224); width: 1024px; margin: 15px auto 0px;">
       <ul>
          <li style="float: left; margin-left: 80px;">
             <p style="font-size: 18px; color: rgb(106, 106, 106);">第一张图(尺寸387px*605px)</p>
             <form onsubmit="return saveImage1();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/admin/update/head/?type=1" id="uploadFrame_1">
                 <input type="file" class="t file l " size="37" name="file" value="浏览">
                 <input type="submit" value="上传图片">
             </form>
             <div style="margin-top:20px;">
                 <img id="image_1" src="/img/webIndex/pic1.png" style="height: 200px; width: 300px;">
             </div>
          </li>
          <li style="float: left; margin-left: 80px;">
             <p style="font-size: 18px; color: rgb(106, 106, 106);">第二张图(尺寸387px*605px)</p>
             <form onsubmit="return saveImage2();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/admin/update/head/?type=2" id="uploadFrame_2">
                 <input type="file" class="t file l " size="37" name="file" value="浏览">
                 <input type="submit" value="上传图片">
             </form>
             <div style="margin-top:20px;">
                 <img id="image_2" src="/img/webIndex/pic2.png" style="height: 200px; width: 300px;">
             </div>
          </li>
       </ul>
       
   </div> 
</body>
</html>
</#escape>
		
<#include "js.ftl">