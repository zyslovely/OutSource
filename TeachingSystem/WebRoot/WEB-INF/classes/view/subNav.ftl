<div style="margin-left: 15%; width: 75%; margin-top: 15px; border-bottom: 2px solid rgb(224, 224, 224); height: 78px;">
     <p style="width: 300px; color: rgb(102, 102, 102); font-size: 23px; float: left; margin-left: 20px; margin-top: 36px;">
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
     </#if>
     </p>
     <#if pageName = "teachCreate">
     <div style="float: left; margin-left: 50%; margin-top: 15px; width: 200px;">
           <a id="teachCreate_save" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">确定保存</a>
     </div>
     <#elseif pageName="teachIndex">
      <div style="float: left;  margin-top: 45px; width: 200px;">
           <#if semesters?exists>
           <select onChange="semestersChange(this);return;">
                 <option value="-1"></option>
             
                 <#list semesters as semester>
                     <option value="${semester.id!0}" <#if semesterId ==semester.id>selected="selected"</#if> >${semester.name!0}</option>
                 </#list>
           </select>  
           </#if>
      </div>
      <#elseif pageName="newTeacher">
      <div style="float: left; margin-left: 50%; margin-top: 15px; width: 200px;">
                 <a id="newTeacher_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">添加老师账号</a>
      </div>
     <#elseif pageName="newCourseType">
      <div style="float: left; margin-left: 50%; margin-top: 15px; width: 200px;">
                 <a id="newCourseType_Create" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">添加课程类型</a>
      </div>
     <#elseif pageName="newStudent">

     <div style="float: left; margin-left: 30%; margin-top: 15px;">
         <div class="w-filebtn" style="float:left;width: 200px;cursor: pointer;">
                 <a id="newStudent_download" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">下载名单模版</a>
         </div>
         <form class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/teacher/upload/addStudent/?classId=${classId!0}" >
             <div class="w-filebtn" style="width: 200px;cursor: pointer;">
                 <input type="file" name="" id="" value=""  class="file-field"/>
                 <a id="" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">上传学生名单</a>
             </div>
         </form>
     </div>

     </#if>
     
</div>