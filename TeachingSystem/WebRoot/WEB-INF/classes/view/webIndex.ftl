<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "webIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}
#help{padding-top:20%; text-align:center;}
img{max-width: 100%;max-height: 80%}
div.outset {border-style: none;width: 20%;height: 300px;float:left;clean:both}

</style>
<style type="text/css">
.title{font-family: verdana, tahoma, sans-serif;FONT-SIZE: 10px;font-weight:bold}
.grey{FONT-SIZE: 12px;color:#666666}

.orange {font-family: verdana, tahoma, sans-serif;font-size:10px;color:#FF6600}
.orange A:link {font-family: verdana, tahoma, sans-serif;font-size:10px;color:#FF6600;text-decoration:underline}
.orange A:visited {font-family: verdana, tahoma, sans-serif;font-size:10px;color:#FF6600;text-decoration:underline}
.orange A:hover {font-family: verdana, tahoma, sans-serif;font-size:10px;color:#FF6600;text-decoration:underline}
.orange A:active {font-family: verdana, tahoma, sans-serif;font-size:10px;color:#FF6600;text-decoration:underline}

textarea, input, select{
    background: #FFFFFF;
    border: 1px solid #CCCCCC;
    color: #000000;
    font-family: verdana, tahoma, sans-serif;
    font-size: 0.95em;}
.username{
    background-image:url(/jscss/demoimg/200909/username.gif);
	background-position: 1px 1px;
	background-repeat:no-repeat;
	padding-left:20px;
	height:20px;
	FONT-SIZE: 12px;}
.password{
    background-image:url(/jscss/demoimg/200909/password.gif);
	background-position: 1px 1px;
	background-repeat:no-repeat;
	padding-left:20px;
	height:20px;
	FONT-SIZE: 12px;}
</style>
	
<body style="background-color: rgb(243, 243, 243);">
<div style="width: 50%; margin: 100px auto auto;">
    <h1 style="text-align: center; font-size: 60px;">登陆管理系统</h1>
    <#include "login.ftl">
</div>
    <form onsubmit="return uploadExcel();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addStudent/?classId=10000" id="uploadFrame">
        
         <input type="file" class="t file l " size="37" name="file" value="浏览">
         <input type="submit" value="上传成绩">
    </form>

</body>
</html>
</#escape>
		
<#include "js.ftl">

</script>