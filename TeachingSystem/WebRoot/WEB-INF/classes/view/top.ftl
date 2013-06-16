<div style="background: url('/img/teachCreate/nav_bg.png') repeat scroll center top transparent; <#if pageName!="interactive">height: 122px;<#else>height: 80px;</#if>">
<div >
   <div style=" margin: auto;width: 1024px;">
   <div style="margin-left: 5%; margin-top: 23px; width: 40%; float: left;">
       <a href="/"><img src="/img/webIndex/logo.png"/></a>
   </div>
   <div style="float: right; height: 52px; margin-top: 29px;">
       <ul style="width:450px;">
          <li style="float: left; width: 80px; text-align: center;">
             <img src="/img/top/interactive.png" style="width: 24px; height: 24px;"/>
             <a target="_blank" href="/teach/user/interactive/" style="color: white; position: relative; top: -6px;">互动</a>
          </li>
          <li style="float: left; width: 3px; text-align: center; height: 24px; font-size: 17px;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <img src="/img/top/message.png" style="width: 24px; height: 24px;"/>
             <a href="/teach/feedback/" style="color: white; position: relative; top: -6px;">信箱(${unreadCount!0})</a>
          </li>
          <li style="float: left; width: 3px; text-align: center; height: 24px; font-size: 17px;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <img src="/img/top/search.png" style="width: 24px; height: 24px;"/>
             <a href="/teach/search/" style="color: white; position: relative; top: -6px;">搜索</a>
          </li>
          <li style="float: left; width: 3px; text-align: center; height: 24px; font-size: 17px;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 100px; text-align: center;">
             <img src="/img/top/user.png" style="width: 24px; height: 24px;"/>
             <a href="/teach/user/profile/" style="color: white; position: relative; top: -6px;">${visitName!""}</a>
          </li>
          <li style="float: left; width: 3px; text-align: center; height: 24px; font-size: 17px;">
             <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <img src="/img/top/quit.png" style="width: 24px; height: 24px;"/>
             <a href="/logout/" style="color: white; position: relative; top: -6px;">退出</a>
          </li>
          
          
       </ul>
   </div>
   </div>
   <#if pageName!="interactive">
   <div style="display: inline-block; width: 100%; background-color: rgb(51, 133, 210); height: 39px; margin-top: 9px;">
     <div style="width:1024px;margin:auto;">
     <ul style="margin-left:5%;">
        <#if level!=3>
        <#if isVisitor?exists&&isVisitor==1>
        <li style="font-size:14px;text-align: center; float: left; width: 100px; padding: 12px 15px; height: 15px;<#if pageName=='teachIndex' || pageName='courseInfo'|| pageName='courseScore'|| pageName='EachStudentScore'|| pageName='courseGroup'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/index/" class="<#if pageName!='teachIndex' && pageName!='courseInfo'&& pageName!='courseScore'&& pageName!='EachStudentScore'&& pageName!='courseGroup'>top_li_selected_color<#else>top_li_diselected_color</#if>">课程列表</a>
        </li>
        <#else>
        <li style="font-size:14px;text-align: center; float: left; width: 100px; padding: 12px 15px; height: 15px;">
           <a href="/teach/index/" class="top_li_selected_color">课程列表</a>
        </li>
        </#if>
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
        <li class="top_li" style="<#if pageName=='teachSemester'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/admin/semester/list/" class="<#if pageName!='teachSemester'>top_li_selected_color<#else>top_li_diselected_color</#if>">添加学期</a>
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
   <#else>
    <div style="display: inline-block; width: 100%; background-color: rgb(51, 133, 210); height: 230px; ">
     <div style="width:600px;margin:auto;">
         <p style="margin: 10px 0px; font-size: 18px;">师生互动</p>
         <textarea id="interactive_send_text" rows="3" cols="80" style="font-size:20px;resize:none;width: 600px; height: 130px; overflow-x: hidden; overflow-y: auto; border-style: none; border-width: 0px; word-wrap: break-word;"></textarea>
         <div>
             <p style="color: white; float: left; margin-top: 18px; width: 54px;">选择课程:</p>
             <div style="width: 140px; height: 30px; float: left; margin-top: 15px; margin-left: 9px;">
                 <#if courseList?exists>
                 <select style="float:right" id="interactive_send_select" >
                   <#list courseList as course>
                       <option value="${course.id!0}" <#if course_index == 0>selected="selected"</#if>>${course.name!""}</option>
                   </#list>
                 </select>
             </#if>
             </div>
             <div style="width: 100px; height: 30px; float: left; margin-left: 20px; margin-top: 20px;"> 
                 <p style="float: left; color: white; width: 55px;">是否保密:</p><input style="width: 16px; height: 16px;" type="checkbox" id="interactive_checkbox"/>
             </div>
             <div class="w-filebtn" style="float:right;width: 200px;cursor: pointer;">
            <a class="w-btn" id="interactive_send" style="margin-left: 20px; display: block; text-align: center; color: white; float: right; background: url('/img/courseInfo/button_bg_small.png') no-repeat scroll 0px 0px transparent; height: 45px; width: 135px; line-height: 33px; margin-top: 9px;" href="javascript:void(0);;">发布</a>
                           
             </div>
             
         </div>
         
     </div>
    </div>
   </#if>
</div>
</div>