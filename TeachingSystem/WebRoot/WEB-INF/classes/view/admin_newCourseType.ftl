<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newCourseType" />
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
   <div style="float:left;margin-left:5%;height:193px;margin-top: 30px;">
       <img src="/img/newCourseType/courseType.png" style="width:195px;height:193px;"/>
   </div>
   <#if coursePercentTypeDemos?exists>
   <table id="create_newCourseType_list" cellspacing="0" style="float: left; margin-left: 80px; width: 600px; margin-top: 20px; font-size: 20px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">类型名称</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">评分标准</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">操作</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list coursePercentTypeDemos as demo>
            <tr style="">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">
               ${demo.name!""}
               </th>
               <th class="f3" style="width:220px;border-bottom: 1px solid rgb(224, 224, 224);">
               <#if demo.coursePercentTypes?exists>
               <#list demo.coursePercentTypes as type>
               <#list demo.percents as percent>
               <#if percent_index==type_index>
               <p style="color: rgb(139, 139, 139);">${type.getName()!""}占比:${percent!0}%</span></p>
               </#if>
               </#list>
               </#list>
               </#if>
               </th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a href="javascript:void(0);" class="admin_newCourseType_delete" data_id="${demo.id!0}">删除</a></th>
            </tr>
            </#list>
       </tbody>
   </table> 
   </#if>
   <div id="create_newCourseType" style="margin-left:40%;height:105px;margin-top: 20px;width: 500px;display:none;">
       <table>
            <tbody>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">类型名称</p></th>
                   <th style="">
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input id="newCourseType_name" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="类型名称"/>
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">评分类型</p></th>
                   <th style="" id="newCourseType_scoreType">
                      <#list 1..5 as index>
                      <div style="margin: 5px 0px;">
                      <div style="height: 35px;margin-left: 15px;float:left;">
                          <select id="${index}" class="newCourseType_scoreType_select_${index}">
                               <#include "CoursePercentType.ftl"/>
                          </select>
                      
                      </div>
                      <div style="height: 35px;">
                         <input class="newCourseType_scoreType_percent" type="text" value="" style="width:50px;margin-left:10px;margin-top:4px;"/><span style="margin-left:10px;">%</span>
                      </div>
                      </div>
                      </#list>
                   </th>
                </tr>
               
            </tbody>
       </table>
       <div style="width: 500px; margin-left: 126px;" > </div>
   </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">

