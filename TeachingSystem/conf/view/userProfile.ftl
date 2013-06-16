<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "userProfile" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="width: 1024px; margin: auto;">
   <div id="" style="height: 105px; width: 500px;  margin: 20px auto 0px;">
       <table>
            <tbody>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">姓名</p></th>
                   <th style="">
                      <p style="font-size:24px">${visitName!""}</p>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">账号</p></th>
                   <th style="">
                      <p style="font-size:24px">${visitUserName!""}</p>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">密码</p></th>
                   <th>
                      <p style="font-size:24px">${passWord!""}</p>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">新密码</p></th>
                   <th>
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newProfile_password_1" name="username" type="password" style="text-align: center;border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" />
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">重输密码</p></th>
                   <th>
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newProfile_password_2" name="username" type="password" style="text-align: center;border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" />
                      </div>
                   </th>
                </tr>
            </tbody>
       </table>
       <#if courseStudentPropertySemesterScores?exists>
       <div><canvas width="400px" height="300px" style="border:0px" id="semesterAll"></canvas></div>
       </#if>   
   </div>
       </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
<#if profileProperties?exists>
window.onload = function() {
	var rc = new html5jp.graph.radar("semesterAll");
	if( ! rc ) { return; }
	var items = [
		["属性值", 
		<#list profileProperties as property>
		<#if property_index !=0 >,</#if>
		${property.score!0}
		</#list>
		]
	];
	var params = {
		aCap: [
		<#list coursePropertyList as property>
		<#list courseStudentPropertySemesterScores as propertyScore>
		    <#if propertyScore.propertyId == property.id>
		    <#if property_index != 0>,</#if>
		    "${property.name!""}"
		    </#if>
		</#list>
		</#list>
		]
	}
	rc.draw(items, params);
};
</#if>
</script>