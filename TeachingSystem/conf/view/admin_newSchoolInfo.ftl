<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newSchoolInfo" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="width: 1024px; margin: auto;">
   <#if infoId<0 >
   <#if schoolInfos?exists>
   <table id="create_newCourseType_list" cellspacing="0" style="margin-top: 20px; font-size: 20px; width: 1024px;">
       <thead style="height:60px;">
            <tr>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">标题</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">内容</th>
               <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">操作</th>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list schoolInfos as schoolInfo>
            <tr style="">
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfo.title}</th>
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfo.content}</th>
               
               <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">
               <#if schoolInfo.infoType==1>
               <a href="/teach/admin/schoolInfo/list/?infoId=${schoolInfo.id!0}&type=${schoolInfo.type!0}" >人员名单</a>
               </#if>
               <a href="javascript:void(0);" onClick="deleteSchoolInfo(${schoolInfo.id!0});" >删除</a>
               </th>
          
            </tr>
            </#list>
       </tbody>
   </table> 
   <#if totalCount gt limit>
   <div style="margin-top: 10px;"><div id="jpage_schoolInfo"></div></div>
   </#if>
   </#if>
   <div id="create_newSchoolInfo" style="width: 1024px; margin: auto;display:none;min-height:700px">
       <table style="margin: auto;">
            <tbody>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">标题</p></th>
                   <th style="">
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input onClick="if(this.value=='标题')this.value=''" id="newSchoolInfo_title"  type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="标题"/>
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">内容</p></th>
                   <th style="">
                      <textarea id="newSchoolInfo_content" rows="3" cols="80" style="font-size: 20px; resize: none; overflow-y: auto; word-wrap: break-word; border-width: 1px; height: 300px; width: 500px; overflow-x: hidden;"></textarea>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">信息类型</p></th>
                   <th style="">
                      <select id="newschoolInfo_type_select" style=" ">
                          <option value="0" selected="selected">学校信息</option>
                          <option value="1" >专业信息</option>
                      </select>  
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">活动类型</p></th>
                   <th style="">
                      <select id="newschoolInfo_infoType_select" style="">
                          <option value="0" selected="selected">不可参加</option>
                          <option value="1" >可参加</option>
                      </select>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">封面图片</p></th>
                   <th style="">
                      <form style="margin-top:17px" onsubmit="return saveImage2();" class="t" size="37" enctype="multipart/form-data" method="post" target="uploadFrame" action="/teach/admin/web/authUpload/" id="uploadFrame_2">
                         <input type="file" class="t file l " size="37" name="file" value="浏览">
                         <input type="submit" value="上传图片">
                      </form>
                      <div style="margin-top:20px;max-height:160px;">
                         <img id="image_2" src="/img/webIndex/pic2.png" style="max-height:160px;display:none;">
                      </div>
                   </th>
                </tr>
               
            </tbody>
       </table>
   </div>
   <#else>
   <#if schoolInfoJoins?exists>
   <div>
   <table cellspacing="0" style=" margin-top: 20px; font-size: 20px; width: 1024px;">
       <thead style="height:60px;">
            <tr>
               <#if type==0>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">姓名</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">联系方式</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">初中毕业学校</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">籍贯</th>
               <#else>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">姓名</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">联系方式</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">班级</th>
                  <th class="f2" style="color: rgb(94, 94, 94);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">专业</th>
                  
               </#if>
            </tr>
       </thead>
       <tbody  style="font-size: 16px;">
            <#list schoolInfoJoins as schoolInfoJoin>
            <tr style="">
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.name!""}</th>
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.phoneNum!0}</th>
               <#if type==0>
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.graduateSch!0}</th>
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.origin!""}</th>
               <#else>
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.className!""}</th>
                   <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);">${schoolInfoJoin.specialtyName!""}</th>
               </#if>
            </tr>
            </#list>
       </tbody>
   </table> 
   </div>
   <#if totalCount gt limit>
   <div style="margin-top: 10px;"><div id="jpage_schoolInfo"></div></div>
   </#if>
   </#if>
   </#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
$("#jpage_schoolInfo").paginate({
				count 		: ${totalCount!0},
				start 		: ${page!0},
				display     : ${limit!0},
				border_color			: '#BEF8B8',
				text_color  			: '#68BA64',
				background_color    	: '#E3F2E1',	
				border_hover_color		: '#68BA64',
				text_hover_color  		: 'black',
				background_hover_color	: '#CAE6C6', 
				images		: false,
				mouse		: 'press',
				border		: true,
				onChange    : function(page){
								location.href="/teach/admin/schoolInfo/list/?page="+page;
							}
			});
 
</script>