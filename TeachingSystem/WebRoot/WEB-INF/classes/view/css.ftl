<#-- css文件加载ftl -->
<#if pageName?exists>
     <link type="text/css" rel="stylesheet" href="/css/base.css"/>
	<#switch pageName>
    	<#case "teachIndex">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	    <link type="text/css" rel="stylesheet" href="/chosen/chosen.css" media="all"/>
    	<#break>
    	
    </#switch>
</#if>