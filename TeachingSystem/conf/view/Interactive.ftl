<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "interactive" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <div style=" width: 100%; margin-top:225px;">
     <div style="width:600px;margin:auto;">
     <ul>
        <#if interactiveList?exists>
        <#list interactiveList as interactive>
             <li style="padding: 10px 0px;">
                <p style="font-size: 24px;">${interactive.name!""}</p>
                <p style="word-wrap:break-word; word-break:normal;font-size:13px;color: rgb(123, 123, 123);">${interactive.content!""}</p>
                <div style="margin-top: 5px;">
                    <#if interactive.courseName?exists><a href="" style="">课程:${interactive.courseName!""}</a></#if>
                    <a href="" style="float: right; margin-right: 28px;">评论</a>
                </div>
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