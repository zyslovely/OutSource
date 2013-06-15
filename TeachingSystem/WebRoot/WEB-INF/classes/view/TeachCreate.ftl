<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachCreate" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body>
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="width: 1024px; margin: auto;">
   <div style="float:left;margin-left:5%;height:160px;margin-top: 30px;">
       <img src="/img/teachCreate/create_pic.png" style="width:160px;height:160px;"/>
   </div>
       <table style="float: left;  margin-top: 20px; margin-left: 40px;">
            <tbody cellSpacing="3px">
                 <tr >
                    <th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">课程名称*</th><th >
                       <div style="background: url('/img/teachCreate/input_bg_create.png') no-repeat scroll center top transparent; width: 250px; ">
                       <input type="text" style="width: 250px;border: 0px none; height: 34px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 10px; font-size: 20px;" id="course_name"/>
                       </div>
                    </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">课程类别选择*</th >
                    <th>
                       <div style="width:252px;">
                       <select  style="" id="demoselector" style="margin:auto;">
                           <option value=""></option>
                           <#list coursePercentTypeDemos as demo>
                           <option value="${demo.id!""}">${demo.name!""}</option>
                           </#list>
                       </select>
                       </div>
                    </th>
                 </tr>
                 <tr>
                     <th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">选择学期*</th>
                     <th>
                        <div style="width:252px;">
                        <select style="" id="semester_selector">
                           <#list semesters as semester>
                           <option value="${semester.id!0}">${semester.name!""}</option>
                           </#list>
                       </select>
                       </div>
                     </th>
                 </tr>
                 <tr>
                     <th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">选择班级*</th>
                     <th>
                        <div style="width:252px;">
                        <select style="" id="class_selector">
                           <#list classList as class>
                           <option value="${class.id!0}">${class.name!""}</option>
                           </#list>
                       </select>
                       </div>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">评分标准*</th>
                     <th>
                        <#if coursePercentTypeDemos?exists>
                        <#list coursePercentTypeDemos as demo>
                        <ul style="display:none;" class="coursePercentTypeDemos_ul" id="coursePercentTypeDemos_${demo.id!0}">
                            <#if demo.coursePercentTypes?exists>
                            <#list demo.coursePercentTypes as type>
                            <li class="clearfix coursePercentTypes_biaozhun_li_${demo.id!0}" style="width:100%"  data_type_id="${type.getValue()!0}">
                               <p style="text-align: left;margin-right:10px;float:left;width:150px;">${type.getName()!""}</p>
                               <div style="float:left;">
                                    <#if demo.percents?exists>
                                    <#list demo.percents as percent>
                                        <#if percent_index==type_index>
                                            <input type="text" value="${percent!0}" style="margin-right: 10px; width: 30px; text-align: center;" class="coursePercentTypes_biaozhun_input_${demo.id!0}"/>%
                                        </#if>
                                    </#list>
                                    </#if>
                                    <span style="<#if type.getValue() ==4>display:none</#if>">
                                    <span style="margin-left:5px">打分老师:</span>
                                    <select class="coursePercentTypes_biaozhun_selectteacher_${demo.id!0}">
                                        <#if teacherProfiles?exists>
                                        <#list teacherProfiles as profile>
                                            <option value="${profile.userId!0}">${profile.name!0}</option>
                                        </#list>
                                        </#if>
                                    </select>
                                    </span>
                                    
                                    <span style="<#if type.getValue() != 2&&type.getValue()!=4>display:none</#if>">
                                    <span style="margin-left:5px"><#if type.getValue()==2>课程次数:<#elseif type.getValue()==4>团队数量:</#if></span>
                                    <input type="text" class="coursePercentTypes_biaozhun_input_objectCount_${demo.id!0}" style="width:20px;text-align:center;"/>
                                    </span>
                                    
                               </div>
                            </li>
                            </#list>
                            </#if>
                        </ul>
                        </#list>
                        </#if>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">相关属性标准*</th>
                     <th>
                        <#list coursePercentTypeDemos as demo>
                        <div style="display:none;" class="coursePercentTypeDemos_property_ul" id="coursePercentTypeDemos_property_${demo.id!0}">
                            <#list demo.coursePercentTypes as type>
                            <ul style="margin: 5px 0; height:20px;" class="coursePercentTypes_shuxing_li_${demo.id!0}" data_type_id="${type.getValue()!0}">
                                   <li style="float:left">
                                   <p style="color: rgb(139, 139, 139);margin-right:10px;width:150px;text-align: left;">${type.getName()!""}</p></li>
                                   <#list courseProperties as property>
                                        <li style="float:left;margin:0 2px;"><input type="checkbox" style="height:13px;" class="coursePercentTypes_shuxing_checkbox_${demo.id!0}_${type.getValue()!0}" data_property_id="${property.id}"/>${property.name!""}</li>
                                   </#list>
                            </ul>
                            </#list>
                        </div>
                        </#list>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;font-size: 16px;color: rgb(106, 106, 106);">课程描述</th><th ><div style="width: 250px;"><textarea id="desc_textarea" rows="3" cols="80" style="width: 250px;height:100px;"></textarea></div></th></tr>
                 
            </tbody>
       </table>
       </div>
</body>
</html>
</#escape>

<#include "js.ftl">