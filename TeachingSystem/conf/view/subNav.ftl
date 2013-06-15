<div style="border-bottom: 1px solid rgb(224, 224, 224); height: 78px; margin: 15px auto 0px; width: 1024px;">
     <p style="<#if pageName = "courseScore">min-width: 400px;<#else>min-width: 300px;</#if> color: rgb(102, 102, 102); font-size: 20px; float: left; margin-left: 50px; margin-top: 36px;font-weight: bold;">
     <#if pageName = "teachIndex">
     课程列表
     <#elseif pageName = "teachCreate">
     创建课程
     <#elseif pageName = "newSpecialty">
     新建专业
     <#elseif pageName = "newClass">
     创建班级
     <#elseif pageName = "newCourseType">
     添加课程类型
     <#elseif pageName = "newTeacher">
     添加老师账号
     <#elseif pageName = "newStudent">
     添加学生信息
     <#elseif pageName = "courseInfo">
     课程列表 > 课程信息
     <#elseif pageName = "courseScore">
     课程列表 > <a href="/teach/course/${courseId!0}/">课程信息</a> > (${percentTypeName!""})<#if stageName?exists>${stageName!""}</#if>
     <#elseif pageName = "EachStudentScore">
     课程列表 > <a href="/teach/course/${courseId!0}/">课程信息</a> > (学生互评)
     <#elseif pageName = "feedback">
     课程反馈
     <#elseif pageName = "courseGroup">
     课程列表 > <a href="/teach/course/${courseId!0}/">课程信息</a> >编辑分组
     </#if>

     </p>
     <#if pageName = "teachCreate">
     <div style="float: left; margin-left: 40%; margin-top: 15px; width: 200px;">
           <a class="w-btn" id="teachCreate_save" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">确定保存</a>
     </div>
     <#elseif pageName = "courseInfo">
     <div style="float: left; margin-left: 40%; margin-top: 15px; width: 200px;">
           <a class="w-btn" href="/teach/teacher/download/score/?courseId=${courseId!0}" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; line-height: 55px;" href="javascript:void(0);;">成绩单下载</a>
     </div>
     <#elseif pageName = "courseScore">
     <div style="float: left; margin-left: 24%; margin-top: 15px;">
         <div class="w-filebtn" style="float:left;width: 200px;cursor: pointer;">
                 <a href="/teach/teacher/download/addScore/?courseId=${courseId!0}&percentTypeId=${percentTypeId!0}&stage=${stage!0}" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" class="w-btn" href="javascript:void(0);;">下载模版</a>
         </div>
         <form style="float: left;" id="fileUpdate_form" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addScore?courseId=${courseId!0}&percentTypeId=${percentTypeId!0}&stage=${stage!0}" >
             <div class="w-filebtn" style="width: 200px;cursor: pointer;">
                 <input type="file" name="file" id="file" value=""  class="file-field" onChange="newfileChange(this);return;"/>
                 <a class="w-btn" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">上传成绩</a>
             </div>
         </form>
     </div>
 
     <#elseif pageName="teachIndex">
      <div style="float: right;  margin-top: 36px; width: 400px;">
           <p style="float: left; font-size: 16px; line-height: 37px; margin-right: 15px;">选择学期:</p>
           <div style="float:left;">
           <#if semesters?exists>
           <select id="teachIndex_semester_select">
                 <option value="-1"></option>
             
                 <#list semesters as semester>
                     <option value="${semester.id!0}" <#if semesterId ==semester.id>selected="selected"</#if> >${semester.name!0}</option>
                 </#list>
           </select>  
           </#if>
           </div>
      </div>
      <#elseif pageName="newTeacher">
      <div style="float: left; margin-left: 30%; margin-top: 15px; width: 200px;">
                 <a class="w-btn" id="newTeacher_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">添加老师账号</a>
      </div>
     <#elseif pageName="newCourseType">
      <div style="margin-top: 15px; width: 200px; float: right; margin-right: 10%;">
                 <a class="w-btn" id="newCourseType_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">添加课程类型</a>
      </div>
     <#elseif pageName="newStudent">

     <div style="float: left; margin-left: 24%; margin-top: 15px;">
         <div class="w-filebtn" style="float:left;width: 200px;cursor: pointer;">
                 <a class="w-btn" id="newStudent_download" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">下载名单模版</a>
         </div>
         <form style="float: left;" id="fileUpdate_form" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addStudent/?classId=${classId!0}" >
             <div class="w-filebtn" style="width: 200px;cursor: pointer;">
                 <input type="file" name="file" id="file" value=""  class="file-field" onChange="newfileChange(this);return;"/>
                 <a class="w-btn" id="" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white;  line-height: 55px;" href="javascript:void(0);;">上传学生名单</a>
             </div>
         </form>
     </div>

     </#if>
     
</div>