<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "feedback" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>

<body >
   
   <#include "top.ftl"/>
   <#include "topNav.ftl"/>
   <#include "subNav.ftl"/>
   <div style="width: 1024px;">
   <div id="normal_feedback" style="margin-left:5%; width: 1024px;height:105px;margin: 20px auto 0px;">
   <#if feedbacks?exists>
       <ul>
       <#list feedbacks as feedback>
       
       <li style="margin: 0 auto 50px; width: 660px; border-bottom: 1px solid rgb(224, 224, 224); min-height: 120px; display: block;">
          <div style="margin:10px 0 0px;min-height:38px;">
             <p style="width: 100px; font-size: 16px; display: inline;color: color: rgb(94, 94, 94);">${feedback.fromName!""}</p>
             <p style="float: right; margin-top: 10px;color: rgb(123, 123, 123);">${feedback.createTimeStr!""}</p>
          </div>
          <div style="min-height:62px;">
             <p style="word-wrap:break-word; word-break:normal;color: rgb(123, 123, 123);font-size: 13px;">${feedback.content!""}</p>
          </div>
          <div style="min-height:20px;">
             <#if feedback.course?exists>
             <p style="display: inline;">来自${feedback.course.name!""}</p>
             <p style="float: right; margin-right: 25px;"><a onClick="feedbackBackClick(${feedback.id!0},${feedback.course.id!0},${feedback.fromUserId!0});return;" href="javascript:void(0);;">回复</a></p>
             </#if>
          </div>
          <#if feedback.subFeedBackList?exists>
                <div style="border: 1px solid #8b8b8b; margin-top: 16px;width:95%;" id="interactive_${feedback.id!0}">
                    <img src="/img/interactive/triangle.png" style="width: 31px; height: 15px; position: relative; top: -15px; right: -430px;"/>
                    <ul style="margin: 0px 20px;">
                    
                    <#list feedback.subFeedBackList as subFeedBack>
                       <li style="<#if subFeedBack_index!=0>border-top: 1px solid #8B8B8B;</#if>">
                          <p style="word-wrap:break-word; word-break:normal;padding-top: 10px;">${subFeedBack.fromName!""} : ${subFeedBack.content!""}</p>
                          <p style="word-wrap:break-word; word-break:normal;padding-bottom: 23px;"><a href="javascript:void(0);;" style="float:right;" onClick="feedbackBackClick(${subFeedBack.feedbackId!0},0,${subFeedBack.fromUserId!0});return;">回复</a></p>
                       </li>
                    </#list>
                    </ul>
                    
                </div>
                </#if>
       </li>
       </#list>
       </ul>
   </#if>
   <#if totalCount gt 1>
   <div style="margin-left: 40%; margin-top: 20px;"><div id="jpage_feedback"></div></div>
   </#if>
   </div>
   </div>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script type="text/javascript">
$("#jpage_feedback").paginate({
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
								location.href="/teach/admin/teacher/list/?page="+page;
							}
			});
 
</script>