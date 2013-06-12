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
	<#switch pageName>
    	<#case "teachIndex">
    	   

    	<#break>
    	<#case "newClass">
    	      
    	    
    	<#break>
    	<#case "feedback">
    	      
    	      <link type="text/css" rel="stylesheet" href="/wbox/wbox/wbox.css"/>
    	<#break>
    	<#case "newStudent">
    	      
    	      <link type="text/css" rel="stylesheet" href="/jPaginate/css/style.css"/>
    	<#break>
    	
    </#switch>
</#if>