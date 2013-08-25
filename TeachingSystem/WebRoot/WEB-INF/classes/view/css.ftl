<#-- css文件加载ftl -->
<#if pageName?exists>
     <link type="text/css" rel="stylesheet" href="/css/base.css"/>
     <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
     <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
     <!--
     <link type="text/css" rel="stylesheet" href="/selector/css/jquery.ui.selectmenu.css"/>
     <link type="text/css" rel="stylesheet" href="/selector/css/styles.css"/>
     -->
     <link type="text/css" rel="stylesheet" href="/css/subNav.css"/>
    
     <link rel="stylesheet" type="text/css" href="/DropDown/dropdown/jquery.jgd.dropdown.css">
     <link rel="stylesheet" type="text/css" href="/DropDown/shared/syntaxhighlighter/shCore.css">
     <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="/DropDown/dropdown/jquery.jgd.dropdown.ie.css">
    <link rel="stylesheet" type="text/css" href="/DropDown/shared/ie.css">
    <![endif]-->
	<#switch pageName>
    	
    	<#case "newClass">
    	      
    	     <link type="text/css" rel="stylesheet" href="/css/admin_newclass.css"/>
    	<#break>
    	<#case "newCourseType">
    	      
    	     <link type="text/css" rel="stylesheet" href="/css/admin_newCourseType.css"/>
    	<#break>
    	<#case "newTeacher">
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	     <link type="text/css" rel="stylesheet" href="/css/admin_newTeacher.css"/>
    	<#break>
    	<#case "newTeach">
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	     <link type="text/css" rel="stylesheet" href="/css/admin_newTeach.css"/>
    	<#break>
    	<#case "newStudent">
    	
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	     <link type="text/css" rel="stylesheet" href="/css/admin_newStudent.css"/>
    	<#break>
    	<#case "feedback">
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	      <link type="text/css" rel="stylesheet" href="/wbox/wbox/wbox.css"/>
    	<#break>  
    	<#case "teachIndex"> 
    	     
    	     <link type="text/css" rel="stylesheet" href="/css/teachIndex.css"/>
    	     <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	<#break>
    	<#case "teachCreate"> 
    	     
    	     <link type="text/css" rel="stylesheet" href="/css/teachCreate.css"/>
    	<#break>
    	<#case "interactive"> 
    	
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	      <link type="text/css" rel="stylesheet" href="/css/interactive.css"/>
    	<#break>
    	<#case "courseSearch"> 
    	
    	      <link type="text/css" rel="stylesheet" href="/css/courseSearch.css"/>
    	<#break>
    	<#case "newSchoolInfo"> 
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	      <link type="text/css" rel="stylesheet" href="/css/admin_newSchoolInfo.css"/>
    	<#break>
    	
    </#switch>
</#if>