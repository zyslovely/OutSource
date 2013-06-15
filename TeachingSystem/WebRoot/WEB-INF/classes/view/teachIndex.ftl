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
   <div style="width: 1024px; margin: auto;">
   <div style="float:left;margin-left:5%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/pic_list.png" style="width:195px;height:193px;"/>
   </div>
   <#if courseList?exists>
   <div style="float: left; width: 600px; margin-top: 20px; margin-left: 60px;">
   <table style="width: 600px;">
        <thead>
             <tr style="height: 40px; font-size: 20px;">
                <td class="f2" style="width: 100px;color: rgb(94, 94, 94);">课程名称</td>
                <td class="f2" style="color: rgb(94, 94, 94);width: 100px;">班级名称</td>
                <#if level!=0>
                <td  class="f2" style="color: rgb(94, 94, 94);width: 100px;">课程老师</td>
                <td  class="f2" style="color: rgb(94, 94, 94);width: 100px;">状态</td>
                <td  class="f2" style="color: rgb(94, 94, 94);width: 100px;">操作</td>
                <#else>
                <td class="f2" style="color: rgb(94, 94, 94);width: 100px;">姓名</td>
                <td class="f2" style="color: rgb(94, 94, 94);width: 100px;">总成绩</td>
                </#if>
                
             </tr>
        </thead>
        <tbody>
             
             <#list courseList as coursevo>
             <tr style="height: 40px; font-size: 16px;">
                <td class="f3" style="width: 100px;"><a href="/teach/course/${coursevo.course.id}/" style="color: rgb(139, 139, 139);">${coursevo.course.name}</a></td>
                <td class="f3" style="color: rgb(139, 139, 139);width: 100px;">${coursevo.class1.name}</td>
                <td class="f3"style="color: rgb(139, 139, 139);width: 100px;">${coursevo.user.name}</td>
                <#if level!=0>
                <td class="f3" style="width: 100px;<#if coursevo.course.status==0>color:green<#else>color:red</#if>"><#if coursevo.course.status==0>进行中<#else>已结束</#if></td>
                <td class="f3" style="width: 100px;color: rgb(139, 139, 139);"><a href="javascript:void(0);" class="teachIndex_delete" data_id="${coursevo.course.id!0}">删除</a></th>
                <#else>
                <td class="f3" style="width: 100px;color: rgb(139, 139, 139);"><#if coursevo.score < 0>未出<#else>${coursevo.score!0}</#if><#if coursevo.haveGroupToScore==1><a href="/teach/eachStudent/score/?courseId=${coursevo.course.id}">(等待互评)</a></#if></td>
                </#if>
             </tr>
             </#list>
             
        </tbody>
   </table>
   <#if totalCount gt 10>
   <div style="margin-top: 10px;"><div id="jpage_teachIndex"></div></div>
   </#if>
   </div>
   </#if> 
   </div>
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
<script type="text/javascript">
$("#jpage_teachIndex").paginate({
				count 		: ${totalCount!0},
				start 		: ${page!0},
				display     : ${limit!0},
				border_color			: '#BEF8B8',
				text_color  			: '#68BA64',
				background_color    	: '#E3F2E1',	
				border_hover_color		: '#68BA64',
				text_hover_color  		: 'black',
				background_hover_color	: '#CAE6C6', 
				images		: false,
				mouse		: 'press',
				border		: true,
				onChange    : function(page){
								location.href="/teach/index/?semesterId=${semesterId!0}&page="+page;
							}
			});
</script>
