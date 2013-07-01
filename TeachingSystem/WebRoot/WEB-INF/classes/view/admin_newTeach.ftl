<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "newTeach" />
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
   <div id="normal_newTeach" style="height: 105px; width: 75%; margin: 20px auto 0px;">
       <#if teachList?exists>
       <table style="" cellspacing="0">
            <thead style="height: 60px;">
            <tr>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">课程名称</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">课程类型</th>
               <th class="f2" style="border-bottom: 1px solid rgb(224, 224, 224);font-size: 20px; width: 300px;color: rgb(94, 94, 94);">操作</th>
            </tr>
            </thead>
            <tbody>
                 
                 <#list teachList as teach>
                  <tr  style="height:40px;font-size: 16px;border-bottom: 1px solid rgb(224, 224, 224); ">

                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${teach.name!""}</th>
                  <th class="f3" style="border-bottom: 1px solid rgb(224, 224, 224);color: rgb(139, 139, 139);">${teach.demoName!""}</th>
                  <th class="f3" style="color: rgb(139, 139, 139);width:190px;border-bottom: 1px solid rgb(224, 224, 224);"><a href="javascript:void(0);" class="admin_newTeach_delete" data_id="${teach.id!0}">删除</a></th>
         
                  </tr>
                 </#list>
                 
            </tbody>
       </table>
       <div style="margin-top: 10px;"><div id="jpage_teach"></div></div>
       </#if>
   </div>
   <div id="create_newTeach_Profile" style="height: 105px; width: 500px;  margin: 20px auto 0px;display:none;">
       <table>
            <tbody>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">课程名称</p></th>
                   <th style="">
                      <div style="background: url('/img/webIndex/input_bg.png') no-repeat scroll center top transparent;  width: 295px; padding-top:15px\9; ">
                        <input onClick="if(this.value=='课程名称')this.value=''" id="newTeach_name" name="username" type="text" style="border: 0px none; height: 60px; background: none repeat scroll 0% 0% transparent; width: 90%; padding-left: 20px; font-size: 20px;" value="课程名称"/>
                      </div>
                   </th>
                </tr>
                <tr>
                   <th style="width: 100px; float: right; font-size: 16px;"><p style="line-height: 53px;">类型</p></th>
                   <th>
                      <div style="margin-left:5px;">
                      <select id="newTeach_demo"> 
                           <#if coursePercentTypeDemos?exists>
                           <#list coursePercentTypeDemos as demo>
                           <option value="${demo.id!0}">${demo.name!""}</option>
                           </#list>
                           </#if>
                      </select>
                      </div>
                   </th>
                </tr>
            </tbody>
       </table>
       <div class="w-btn" style="width: 500px; margin-left: 126px;" > <a id="newTeach_Submit" style="background: url('/img/teachCreate/button_edit.png') no-repeat scroll 0px 0px transparent; height: 59px; display: block; color: white;  line-height: 55px; margin-left: 20px; text-align: center; width: 174px;" href="javascript:void(0);;">添加课程模板</a></div>
       
   </div>
       </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
$("#jpage_teach").paginate({
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
								location.href="/teach/admin/teach/list/?page="+page;
							}
			});
 
</script>