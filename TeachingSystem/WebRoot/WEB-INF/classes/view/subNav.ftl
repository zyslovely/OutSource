<div style="margin-left: 15%; width: 75%; margin-top: 15px; border-bottom: 2px solid rgb(224, 224, 224); height: 78px;">
     <p style="width: 20%; color: rgb(102, 102, 102); font-size: 23px; float: left; margin-left: 20px; margin-top: 36px;">
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
     </#if>
     </p>
     <#if pageName = "teachCreate">
     <div style="float: left; margin-left: 30%;">
         <a id="teachCreate_save" href="javascript:void(0);;" style="font-size: 20px;">确定保存</a>
     </div>
     <#elseif pageName="newStudent">

     <div style="float: left; margin-left: 30%; margin-top: 15px;">
         <div class="w-filebtn" style="float:left;width: 200px;cursor: pointer;">
                 <a id="newStudent_download" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="/teach/teacher/download/addStudent/">下载名单模版</a>
         </div>
         <form class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="" id="">
             <div class="w-filebtn" style="width: 200px;cursor: pointer;">
                <input type="file" name="" id="" value=""  class="file-field"/>
                 <a id="" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; margin-left: 20px; height: 59px; display: block; text-align: center; color: white; font-size: 20px; line-height: 55px;" href="javascript:void(0);;">上传学生名单</a>
             </div>
         </form>
     </div>

     </#if>
     
</div>