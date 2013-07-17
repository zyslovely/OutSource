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
   <#if schoolInfos?exists>
   
   <table id="create_newCourseType_list" cellspacing="0" style="float: left; margin-top: 20px; font-size: 20px; width: 1024px;">
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
               <a href="javascript:void(0);" onClick="" >人员名单</a>
               </#if>
               <a href="javascript:void(0);" onClick="" >删除</a>
               </th>
          
            </tr>
            </#list>
       </tbody>
   </table> 
   <div style="margin-top: 10px;"><div id="jpage_schoolInfo"></div></div>
   </#if>
   <div id="create_newSchoolInfo" style="width: 1024px; margin: auto;display:none;">
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
                      <textarea id="newSchoolInfo_content" rows="3" cols="80" style="font-size: 20px; resize: none; overflow-y: auto; word-wrap: break-word; border-width: 1px; height: 200px; width: 350px; overflow-x: hidden;"></textarea>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">信息类型</p></th>
                   <th style="">
                      <select id="newschoolInfo_type_select">
                          <option value="0" selected="selected">学校信息</option>
                          <option value="1" >学院信息</option>
                      </select>  
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">活动类型</p></th>
                   <th style="">
                      <select id="newschoolInfo_infoType_select">
                          <option value="0" selected="selected">不可参加</option>
                          <option value="1" >可参加</option>
                      </select>
                   </th>
                </tr>
               
            </tbody>
       </table>
   </div>
   </div>
       
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