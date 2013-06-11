<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>
<style type="text/css">
canvas {border:1px solid #4c4c4c;}
</style>

<body>
   <#if courseStudentPropertySemesterScores?exists>
   <div><canvas width="400" height="300px" id="sample"></canvas></div>
   </#if>
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="float:left;margin-left:15%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/pic_list.png" style="width:195px;height:193px;"/>
   </div>
   <#if courseList?exists>
   <table style="float: left; width: 600px; margin-top: 20px; margin-left: 60px;">
        <thead>
             <tr style="height: 40px; font-size: 20px;">
                <td style="width: 100px;color: rgb(123, 123, 123);">课程名称</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">班级名称</td>
                <#if level!=0>
                <td style="color: rgb(123, 123, 123);width: 100px;">课程老师</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">状态</td>
                <#else>
                <td style="color: rgb(123, 123, 123);width: 100px;">姓名</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">总成绩</td>
                </#if>
             </tr>
        </thead>
        <tbody>
             
             <#list courseList as coursevo>
             <tr style="height: 40px; font-size: 16px;">
                <td style="width: 100px;"><a href="/teach/course/${coursevo.course.id}/" style="color:black;color: rgb(123, 123, 123);">${coursevo.course.name}</a></td>
                <td style="color: rgb(123, 123, 123);width: 100px;">${coursevo.class1.name}</td>
                <td style="color: rgb(123, 123, 123);width: 100px;">${coursevo.user.name}</td>
                <#if level!=0>
                <td style="width: 100px;<#if coursevo.course.status==0>color:green<#else>color:red</#if>"><#if coursevo.course.status==0>进行中<#else>已结束</#if></td>
                <#else>
                <td style="width: 100px;"><#if coursevo.score < 0>未出<#else>${coursevo.score!0}</#if><#if coursevo.haveGroupToScore==1><a href="/teach/eachStudent/score/?courseId=${coursevo.course.id}">(等待互评)</a></#if></td>
                </#if>
             </tr>
             </#list>
             
        </tbody>
   </table>
   </#if> 
   
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
<#if courseStudentPropertySemesterScores?exists>
window.onload = function() {
	var rc = new html5jp.graph.radar("sample");
	if( ! rc ) { return; }
	var items = [
		["属性", 
		<#list courseStudentPropertySemesterScores as propertyScore>
		<#if propertyScore_index !=0 >,</#if>
		${propertyScore.score!0}
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
