<div style="height:40px;width:100%;margin-top:15px;">
     <p style="width: 20%; margin-left: 16%; color: rgb(102, 102, 102); font-size: 23px;float:left;">
     <#if pageName = "teachIndex">
     课程列表
     <#elseif pageName = "teachCreate">
     创建课程
     </#if>
     </p>
     <#if pageName = "teachCreate">
     <div style="float: left; margin-left: 30%;">
         <a id="teachCreate_save" href="javascript:void(0);;" style="font-size: 20px;">确定保存</a>
     </div>
     </#if>
     
</div>