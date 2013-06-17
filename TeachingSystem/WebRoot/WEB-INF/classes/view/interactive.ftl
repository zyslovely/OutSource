<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "interactive" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body style="background-color: rgb(251, 251, 251);">
   
   <#include "top.ftl"/>
   <div style=" width: 100%; margin-top: 225px;">
     <div style="width:600px;margin:auto;">
     <ul>
        <#if interactiveList?exists>
        <#list interactiveList as interactive>
             <li style="border-bottom: 1px solid rgb(94, 94, 94); padding: 10px 0px;">
                <p style="margin-left: 10px;font-size: 18px;color:rgb(94, 94, 94);">${interactive.name!""}</p>
                <p style="margin-left: 10px;word-wrap:break-word; word-break:normal;font-size:13px;color: rgb(139, 139, 139);">${interactive.content!""}</p>
                <div style="margin-left: 10px;margin-top: 5px;">
                    <#if interactive.courseName?exists><a href="" style="color: rgb(46, 127, 203);">课程:${interactive.courseName!""}</a></#if>
                    <#if interactive.forwardFromStr?exists><span>${interactive.forwardFromStr!""}</span></#if>
                    <a href="javascript:void(0);;" onClick="back(${interactive.id!0});return;" style="float: right; margin-right: 28px;">回复</a>
                    <a href="javascript:void(0);;" onClick="forward(${interactive.id!0});return;" style="float: right; margin-right: 28px;">转发</a>
                    <a href="javascript:void(0);;" onClick="comment(${interactive.id!0});return;" style="float: right; margin-right: 28px;">评论(<#if interactive.subInteractiveBackList?exists>${interactive.subInteractiveBackList?size}<#else>0</#if>)</a>
                </div>
                <#if interactive.subInteractiveBackList?exists>
                <div style="border: 1px solid #8b8b8b; margin-top: 16px;width:95%;display:none;" id="interactive_${interactive.id!0}">
                    <img src="/img/interactive/triangle.png" style="width: 31px; height: 15px; position: relative; top: -15px; right: -430px;"/>
                    <ul style="margin: 0px 20px;">
                    
                    <#list interactive.subInteractiveBackList as interactiveBack>
                       <li style="<#if interactiveBack_index!=0>border-top: 1px solid #8B8B8B;</#if>">
                          <p style="word-wrap:break-word; word-break:normal;padding-top: 10px;">${interactiveBack.name!""} : ${interactiveBack.content!""}</p>
                          <p style="word-wrap:break-word; word-break:normal;padding-bottom: 23px;"><a href="javascript:void(0);;" style="float:right;" onClick="back(${interactiveBack.interactiveId!0});return;">回复</a></p>
                       </li>
                    </#list>
                    </ul>
                    
                </div>
                </#if>
             </li>
        </#list>
        </#if>
     </ul>
     <#if totalCount gt 10>
     <div style="margin-top: 10px;"><div id="jpage_interactive"></div></div>
     </#if>
     </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
$("#jpage_interactive").paginate({
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
								location.href="/teach/admin/student/list/?classId=${classId!0}&specialtyId=${specialtyId!0}&page="+page;
							}
			});
			$('#interactive_send_select').jgdDropdown();
</script>