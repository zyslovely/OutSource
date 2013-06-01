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
   <div style="float:left;margin-left:15%;height:193px;margin-top: 30px;">
       <img src="/img/teachCreate/create_pic.png" style="width:195px;height:193px;"/>
   </div>
       <table style="float:left;margin-left:10px;width:600px">
            <tbody>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程名称*</th><th ><input type="text" style="width: 250px;" id="course_name"/></th></tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程类别选择*</th >
                    <th>
                       <select  style="width: 200px;"  onChange="coursePercentTypeChange(this);return;" id="demoselector">
                           <#list coursePercentTypeDemos as demo>
                           <option value="${demo.id!""}">${demo.name!""}</option>
                           </#list>
                       </select>
                    </th>
                 </tr>
                 <tr>
                     <th style="float: right; height: 40px; line-height: 40px; width: 150px;">选择学期*</th>
                     <th>
                        <select style="width: 200px;" id="semester_selector">
                           <#list semesters as semester>
                           <option value="${semester.id!0}">${semester.name!""}</option>
                           </#list>
                       </select>
                     </th>
                 </tr>
                 <tr>
                     <th style="float: right; height: 40px; line-height: 40px; width: 150px;">选择班级*</th>
                     <th>
                        <select style="width: 200px;" id="class_selector">
                           <#list classList as class>
                           <option value="${class.id!0}">${class.name!""}</option>
                           </#list>
                       </select>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">评分标准*</th>
                     <th>
                        <#if coursePercentTypeDemos?exists>
                        <#list coursePercentTypeDemos as demo>
                        <ul style="display:none;" class="coursePercentTypeDemos_ul" id="coursePercentTypeDemos_${demo.id!0}">
                            <#if demo.coursePercentTypes?exists>
                            <#list demo.coursePercentTypes as type>
                            <li style="width:100%" class="coursePercentTypes_biaozhun_li_${demo.id!0}" data_type_id="${type.getValue()!0}">
                               <p style="margin-right:10px;float:left;width:150px;">${type.getName()!""}</p>
                               <div style="">
                                    <#if demo.percents?exists>
                                    <#list demo.percents as percent>
                                        <#if percent_index==type_index>
                                            <input type="text" value="${percent!0}" style="margin-right: 10px; width: 30px; text-align: center;" class="coursePercentTypes_biaozhun_input_${demo.id!0}"/>
                                        </#if>
                                    </#list>
                                    </#if>
                                    <span style="<#if type.getValue() ==4>display:none;</#if>">
                                    <span>打分老师:</span>
                                    <select class="coursePercentTypes_biaozhun_selectteacher_${demo.id!0}">
                                        <#if teacherProfiles?exists>
                                        <#list teacherProfiles as profile>
                                            <option value="${profile.userId!0}">${profile.name!0}</option>
                                        </#list>
                                        </#if>
                                    </select>
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
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">相关属性标准*</th>
                     <th>
                        <#list coursePercentTypeDemos as demo>
                        <div style="display:none;" class="coursePercentTypeDemos_property_ul" id="coursePercentTypeDemos_property_${demo.id!0}">
                            <#list demo.coursePercentTypes as type>
                            <ul style="margin: 5px 0; height:20px;" class="coursePercentTypes_shuxing_li_${demo.id!0}" data_type_id="${type.getValue()!0}">
                                   <li style="float:left"><p style="margin-right:10px;width:100px;">${type.getName()!""}</p></li>
                                   <#list courseProperties as property>
                                        <li style="float:left;margin:0 2px;"><input type="checkbox" style="height:13px;" class="coursePercentTypes_shuxing_checkbox_${demo.id!0}_${type.getValue()!0}" data_property_id="${property.id}"/>${property.name!""}</li>
                                   </#list>
                            </ul>
                            </#list>
                        </div>
                        </#list>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程描述</th><th ><textarea id="desc_textarea" rows="3" cols="80" style="width: 250px;height:100px;"></textarea></th></tr>
                 
            </tbody>
       </table>
</body>
</html>
</#escape>
<script>
     var  _cfg_host="${cfg_host!""}"
</script>
<#include "js.ftl">