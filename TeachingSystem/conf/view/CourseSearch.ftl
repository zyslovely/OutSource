<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "courseSearch" />
<#include "head.ftl">
<style type="text/css">

html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>
<body >

   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="height: 200px; border-bottom: 1px solid rgb(224, 224, 224); width: 1024px; margin: 15px auto 0px;">
       <div style="margin-left: 5%;">
           <ul style="height:30px;width:100%;">
              <li style="float:left;width:170px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">专业:</p></li>
              <li style="float:left;width:170px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">班级:</p></li>
              <li style="float:left;width:170px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">学期:</p></li>
              <li style="float:left;width:170px;"><p style="font-size: 18px; padding-left: 7px;color: rgb(106, 106, 106);">属性:</p></li>
              <li style="float:left;"><p></p></li>
           </ul>
           <ul style=" height: 60px;width:100%;">
              <li style="float:left;width:170px;">
                  <div class="select-wrapper wide" style="margin-top: 11px;">
                          <select  id="courseSearch_Specialty_select" name="receiver">
                              <#if specialties?exists>
                              <#list specialties as specialty>
                                  <option value="${specialty.id!0}" <#if specialtyId==specialty.id>selected="selected"</#if>>${specialty.specialty!""}</option>
                              </#list>
                              </#if>
                          </select>
                      </div>
              </li>
              <li style="float:left;width:170px;">
                  <div class="select-wrapper wide" style="margin-top: 11px;">
                          <select class="select-receiver" id="courseSearch_Class_select" name="receiver" >
                              <#if classList?exists>
                              <#list classList as class>
                                  <option value="${class.id!0}" <#if classId==class.id>selected="selected"</#if>>${class.name!""}</option>
                              </#list>
                              </#if>
                          </select>
                      </div>
              </li>
              <li style="float:left;width:170px;">
                  <div class="select-wrapper wide" style="margin-top: 11px;">
                          <select class="select-receiver" id="courseSearch_Semester_select" name="receiver" >
                              <#if semesters?exists>
                              <#list semesters as semester>
                                  <option value="${semester.id!0}" <#if semesterId==semester.id>selected="selected"</#if>>${semester.name!""}</option>
                              </#list>
                              </#if>
                          </select>
                      </div>
              </li>
              <li style="float:left;width:170px;">
                  <#if courseProperties?exists>
                  <#list courseProperties as property>
                  <div class="select-wrapper wide" style="margin-top: 8px; margin-left: 8px;">
                          <span>${property.name!""}</span>
                          <span>大于等于</span>
                          <input type="text" style="width:20px;height:15px;" class="courseSearch_property_input" data_id="${property.id!0}"/>
                  </div>
                  </#list>
                  </#if>
              </li>
              <li style="float:left;width:200px;">
                  <a class="w-btn" id="courseSearch_search" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; line-height: 55px;" href="javascript:void(0);;">搜索</a>
              </li>
           </ul>
       </div>
       
   </div> 
   <div style="width: 1024px; margin: auto;">
   <#if seachProfileList?exists>

   
   <table cellspacing="0" style="float: left; margin-left: 80px; width: 800px; margin-top: 20px; font-size: 20px;cellspacing:0px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">姓名</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:300px;border-bottom: 1px solid rgb(224, 224, 224);">属性成绩</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">查看</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list seachProfileList as seachProfile>
            <tr style="height: 40px;">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><#if seachProfile.profile?exists>${seachProfile.profile.name!""}</#if></th>
               <th class="f3" style="color: rgb(139, 139, 139);width:300px;border-bottom: 1px solid rgb(224, 224, 224);">
               
                  <#if seachProfile.courseStudentPropertySemesterScoreList?exists>
                    <div style="width:300px;">
                    <canvas width="300px" height="300px" style="border:0px" id="sample_${seachProfile.profile.userId}"></canvas>
             
                    </div>
                  </#if>
               </th>
               
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a target="_blank" href="/teach/index/?semesterId=${semesterId!0}&userId=${seachProfile.profile.userId!0}">成绩查看</a></th>
            </tr>
            </#list>
       </tbody>
   </table> 
   </#if>
       </div>
   
</body>
</html>
</#escape>
<script>
var _specialtyId=${specialtyId!0};
var _classId=${classId!0};
var _semesterId=${semesterId!0};
</script>
<#include "js.ftl">
<script type="text/javascript">
<#if seachProfileList?exists>

window.onload = function() {
    <#list seachProfileList as profile>
	var rc = new html5jp.graph.radar("sample_${profile.profile.userId}");
	if( ! rc ) { return; }
	var items = [
		["属性值", 
		<#list profile.courseStudentPropertySemesterScoreList as propertyScore>
		<#if propertyScore_index !=0 >,</#if>
		${propertyScore.score!0}
		</#list>
		]
	];
	var params = {
		aCap: [
		<#list courseProperties as property>
		<#list profile.courseStudentPropertySemesterScoreList as propertyScore>
		    <#if propertyScore.propertyId == property.id>
		    <#if property_index != 0>,</#if>
		    "${property.name!""}"
		    </#if>
		</#list>
		</#list>
		]
	}
	rc.draw(items, params);
	</#list>
};

</#if>
</script>