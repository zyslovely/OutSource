<#-- js文件加载ftl -->
<script>
     var  _cfg_host="${cfg_host!""}"
</script>
<#if pageName?exists>
    <script type="text/javascript" src="/js/base/engine.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/util.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery-1.7.2.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.easyui.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/alert/jquery.easydrag.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/alert/jquery.alert.js" charset="utf-8"></script>

    <script src="/DropDown/shared/syntaxhighlighter/shCore.js"></script>
    <script src="/DropDown/dropdown/jquery.jgd.dropdown.js"></script>
    
	<#switch pageName>
    	<#case "webIndex">
    	
   	       <script type="text/javascript" src="/js/webIndex.js" charset="utf-8"></script>
    	<#break>
    	<#case "teachIndex">
    	  <script type="text/javascript">
    	   $('#teachIndex_semester_select').jgdDropdown({callback: function(obj, val) { semestersChange(obj,val) }});
    	  </script>
    	   <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
    	   <!--[if IE]><script type="text/javascript" src="/js/radar/radarc.js"></script><![endif]-->
    	   <script type="text/javascript" src="/js/radar/radar.js"></script>
   	       <script type="text/javascript" src="/js/upload.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/teachIndex.js" charset="utf-8"></script>
   	       
    	<#break>
    	<#case "teachCreate">
    	   <script type="text/javascript">
    	   
    	   $('#semester_selector').jgdDropdown({callback: function(obj, val) { semesterChange(obj,val) }});
    	   
    	   $('#specialty_selector').jgdDropdown({callback: function(obj, val) { specialtyChange(obj,val) }});
    	   
    	   $('#teachSelector').jgdDropdown({callback: function(obj, val) { teachChange(obj,val) }});
    	   
    	   
    	   </script>
   	       <script type="text/javascript" src="/js/teachCreate.js" charset="utf-8"></script>
    	<#break>
    	
    	<#case "newClass">
    	  <script>
                $('#newClass_Create_Specialty').jgdDropdown({callback: function(obj, val) { newSpecialtyCreateClassChange(obj,val) }});
          </script>
   	       <script type="text/javascript" src="/js/admin_newClass.js" charset="utf-8"></script>
    	<#break>
    	<#case "newSpecialty">
    	  
   	       <script type="text/javascript" src="/js/admin_newSpecialty.js" charset="utf-8"></script>
    	<#break>
    	<#case "newStudent">
    	   <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/subNav.js" charset="utf-8"></script>
   	       <script type="text/javascript">
           $("#jpage_student").paginate({
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
			 $('#newStudent_Specialty_list').jgdDropdown({callback: function(obj, val) { newStudentCreateSpecialtyChange(obj,val) }});
			 $('#newStudent_Class_list').jgdDropdown({callback: function(obj, val) { newStudentCreateClassChange(obj,val) }});
           </script>
   	       <script type="text/javascript" src="/js/admin_newStudent.js" charset="utf-8"></script>
   	       
    	<#break>
    	<#case "newTeacher">
    	  <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
    	  <script type="text/javascript">
    	  $('#newTeacher_level').jgdDropdown({callback: function(obj, val) { teacherTypeChoice(obj,val) }});
    	  </script>
   	       <script type="text/javascript" src="/js/admin_newTeacher.js" charset="utf-8"></script>
    	<#break>
    	<#case "newTeach">
    	  <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
    	  <script type="text/javascript">
    	  $('#newTeach_demo').jgdDropdown({callback: function(obj, val) { teachTypeChoice(obj,val) }});
    	  </script>
   	       <script type="text/javascript" src="/js/admin_newTeach.js" charset="utf-8"></script>
    	<#break>
    	<#case "newCourseType">
    	  
    	  <script>
             <#list 1..5 as index>
                $('.newCourseType_scoreType_select_${index}').jgdDropdown({callback: function(obj, val) { scoreType_select(obj,val) }});
             </#list>
          </script>
   	       <script type="text/javascript" src="/js/admin_newCourseType.js" charset="utf-8"></script>
    	<#break>
    	<#case "courseInfo">
    	  
   	       <script type="text/javascript" src="/js/courseInfo.js" charset="utf-8"></script>
    	<#break>
    	
    	<#case "courseScore">
    	  
   	       <script type="text/javascript" src="/js/courseScore.js" charset="utf-8"></script>
    	<#break>

    	<#case "EachStudentScore">
    	  
   	       <script type="text/javascript" src="/js/EachStudentScore.js" charset="utf-8"></script>
    	<#break>
    	
    	<#case "feedback">
    	<script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/feedback.js" charset="utf-8"></script>
    	<#break>
    	<#case "courseGroup">
   	       <script type="text/javascript" src="/js/courseGroup.js" charset="utf-8"></script>
    	<#break>
    	<#case "interactive">
    	   <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/interactive.js" charset="utf-8"></script>
    	<#break>
    	<#case "userProfile">
    	  <!--[if IE]><script type="text/javascript" src="/js/radar/radarc.js"></script><![endif]-->
    	   <script type="text/javascript" src="/js/radar/radar.js"></script>
   	       <script type="text/javascript" src="/js/userProfile.js" charset="utf-8"></script>
    	<#break>
    	<#case "teachSemester">
   	       <script type="text/javascript" src="/js/teachSemester.js" charset="utf-8"></script>
    	<#break>
    	
    	<#case "newHeadImage">
   	       <script type="text/javascript" src="/js/admin_newHeadImage.js" charset="utf-8"></script>
    	<#break>
    	<#case "courseSearch">
    	   <script type="text/javascript">
			 $('#courseSearch_Specialty_select').jgdDropdown({selected: '${specialtyId!-1}',callback: function(obj, val) { courseSearchSepcialtyChange(obj,val) }});
			 $('#courseSearch_Class_select').jgdDropdown({selected: '${classId!-1}',callback: function(obj, val) { courseSearchClassChange(obj,val) }});
			 $('#courseSearch_Semester_select').jgdDropdown({selected: '${classId!-1}',callback: function(obj, val) { courseSearchSemesterChange(obj,val) }});
           
           </script>
           <!--[if IE]><script type="text/javascript" src="/js/radar/radarc.js"></script><![endif]-->
    	   <script type="text/javascript" src="/js/radar/radar.js"></script>
   	       <script type="text/javascript" src="/js/courseSearch.js" charset="utf-8"></script>
   	       
    	<#break>
    	<#case "newSchoolInfo">
    	   <script type="text/javascript">
			 $('#schoolInfo_type_select').jgdDropdown({selected: '${type!-1}',callback: function(obj, val) { schoolInfoTypeChange(obj,val) }});
			 $('#newschoolInfo_type_select').jgdDropdown({callback: function(obj, val) { newschoolInfoTypeChange(obj,val) }});
			 $('#newschoolInfo_infoType_select').jgdDropdown({callback: function(obj, val) { newschoolInfoInfoTypeChange(obj,val) }});
			
           </script>
           <script type="text/javascript" src="/jPaginate/jquery.paginate.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/admin_newSchoolInfo.js" charset="utf-8"></script>
   	       
    	<#break>
    	
    </#switch>
</#if>