<div style="background: url('/img/teachCreate/nav_bg.png') repeat scroll center top transparent; height: 122px;">
<div >
   <div style=" margin: auto;width: 1024px;">
   <div style="margin-left: 5%; margin-top: 23px; width: 40%; float: left;">
       <img src="/img/webIndex/logo.png"/>
   </div>
   <div style="float: right;  margin-top: 23px;height:52px;">
       <ul style="width:350px;">
          <li style="float: left; width: 100px; text-align: center;">
             <img src="/img/teachCreate/user.png" style="width: 14px; height: 14px;"/>
             <a href="/user/profile/" style="color: white;">${visitName!""}</a>
          </li>
          <li style="float: left; width: 3px; text-align: center;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <img src="/img/teachCreate/message.png" style="width: 17px; height: 11px;"/>
             <a href="/teach/feedback/" style="color: white;">信箱(${unreadCount!0})</a>
          </li>
          <li style="float: left; width: 3px; text-align: center;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <a href="/logout/" style="color: white;">退出</a>
          </li>
          <li style="float: left; width: 3px; text-align: center;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <a href="/help/" style="color: white;">帮助</a>
          </li>
       </ul>
   </div>
   </div>
   <div style="display: inline-block; width: 100%; background-color: rgb(51, 133, 210); height: 39px; margin-top: 9px;">
     <div style="width:1024px;margin:auto;">
     <ul style="margin-left:5%;">
        <#if level!=3>
        <li style="font-size:14px;text-align: center; float: left; width: 100px; padding: 12px 15px; height: 15px;<#if pageName=='teachIndex' || pageName='courseInfo'|| pageName='courseScore'|| pageName='EachStudentScore'|| pageName='courseGroup'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/index/" class="<#if pageName!='teachIndex' && pageName!='courseInfo'&& pageName!='courseScore'&& pageName!='EachStudentScore'&& pageName!='courseGroup'>top_li_selected_color<#else>top_li_diselected_color</#if>">课程列表</a>
        </li>
        </#if>
        <#if level==1>
        <li style="font-size:14px;text-align: center; float: left; width: 100px; padding: 12px 15px; height: 15px;<#if pageName=='teachCreate'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/create/" class="<#if pageName!='teachCreate'>top_li_selected_color<#else>top_li_diselected_color</#if>">创建课程</a>
        </li>
        </#if>
        <#if level==3>
        <li class="top_li" style="<#if pageName=='newSpecialty'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/specialty/list/" class="<#if pageName!='newSpecialty'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加专业</a>
        </li>
        <li class="top_li" style="<#if pageName=='newClass'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/class/list/" class="<#if pageName!='newClass'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加班级</a>
        </li>
        <li class="top_li" style="<#if pageName=='newCourseType'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/course/type/list/" class="<#if pageName!='newCourseType'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加课程类型</a>
        </li>
        <li class="top_li" style="<#if pageName=='newTeacher'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/teacher/list/" class="<#if pageName!='newTeacher'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加老师账号</a>
        </li>
        <li class="top_li" style="<#if pageName=='newStudent'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/student/list/" class="<#if pageName!='newStudent'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加学生信息</a>
        </li>
        <#--
        <li class="top_li" style="<#if pageName=='newSchoolInfo'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/schoolInfo/list/" class="<#if pageName!='newSchoolInfo'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加学校学院信息</a>
        </li>
        -->
        </#if>
     </ul>
     </div>
   </div>
</div>
</div>