<div style="margin-left: 15%;height:40px;width:75%;margin-top:15px;border-bottom: 2px solid rgb(224, 224, 224);">
     <p style="width: 20%;  color: rgb(102, 102, 102); font-size: 23px;float:left;">
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
     </#if>
     </p>
     <#if pageName = "teachCreate">
     <div style="float: left; margin-left: 30%;">
         <a id="teachCreate_save" href="javascript:void(0);;" style="font-size: 20px;">确定保存</a>
     </div>
     </#if>
     
</div>