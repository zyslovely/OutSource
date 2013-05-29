<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "teachCreate" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:900px}

</style>

<body>
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="float:left;margin-left:15%;height:193px;">
       <img src="/img/teachCreate/create_pic.png" style="width:195px;height:193px;"/>
   </div>
   <div style="float:left;margin-left:10px;">
       <table>
            <tbody>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程名称*</th><th ><input type="text" style="width: 250px;" id="course_name"/></th></tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程类别选择*</th >
                    <th>
                       <select style="width: 200px;" onChange="coursePercentTypeChange(this);return;">
                           <#list coursePercentTypeDemos as demo>
                           <option value="${demo.id!""}">${demo.name!""}</option>
                           </#list>
                       </select>
                    </th>
                 </tr>
                 <tr>
                     <th style="float: right; height: 40px; line-height: 40px; width: 150px;">选择班级*</th>
                     <th>
                        <select style="width: 200px;" id="class_selector">
                           <#list classList as class>
                           <option value="${class.id!""}">${class.name!""}</option>
                           </#list>
                       </select>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">评分标准*</th>
                     <th>
                        <#list coursePercentTypeDemos as demo>
                        <ul style="display:none;" class="coursePercentTypeDemos_ul" id="coursePercentTypeDemos_${demo.id!0}">
                            <#list demo.coursePercentTypes as type>
                            <li style="width:100%">
                               <p style="margin-right:10px;float:left;width:150px;">${type.getName()!""}</p>
                               <div style="">
                                    <#list demo.percents as percent>
                                        <#if percent_index==type_index>
                                            <input type="text" value="${percent!0}" style="margin-right: 10px; width: 30px; text-align: center;"/>
                                        </#if>
                                    </#list>
                                    <span>打分老师:</span>
                                    <select>
                                        <#list teacherProfiles as profile>
                                            <option value="${profile.userId!0}">${profile.name!0}</option>
                                        </#list>
                                    </select>
                               </div>
                            </li>
                            </#list>
                        </ul>
                        </#list>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">相关属性标准*</th>
                     <th>
                        <#list coursePercentTypeDemos as demo>
                        <div style="display:none;" class="coursePercentTypeDemos_property_ul" id="coursePercentTypeDemos_property_${demo.id!0}">
                            <#list demo.coursePercentTypes as type>
                            <ul style="margin: 5px 0; height:20px;">
                                   <li style="float:left"><p style="margin-right:10px;width:100px;">${type.getName()!""}</p></li>
                                   <#list courseProperties as property>
                                        <li style="float:left;margin:0 2px;"><input type="checkbox" style="height:13px;" />${property.name!""}</li>
                                   </#list>
                            </ul>
                            </#list>
                        </div>
                        </#list>
                     </th>
                 </tr>
                 <tr><th style="float: right; height: 40px; line-height: 40px; width: 150px;">课程描述</th><th ><textarea rows="3" cols="80" style="width: 250px;height:100px;"></textarea></th></tr>
                 
            </tbody>
       </table>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">