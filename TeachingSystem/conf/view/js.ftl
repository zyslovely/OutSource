
<#-- js文件加载ftl -->
<#if pageName?exists>
    <script type="text/javascript" src="/js/base/engine.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/util.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery-1.7.2.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.easyui.min.js" charset="utf-8"></script>
    
	<#switch pageName>
    	<#case "phoneList">
    	   <script type="text/javascript" src="/js/flexigrid/flexigrid.js" charset="utf-8"></script>
 
    	   <script type="text/javascript" src="/js/alert/jquery.easydrag.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/alert/jquery.alert.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/phonelist.js" charset="utf-8"></script>
    	<#break>
    	<#case "phoneAdd">
    	   <script type="text/javascript" src="/js/flexigrid/flexigrid.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/phonelist.js" charset="utf-8"></script>
    	<#break>
    	<#case "accessoryList">
    	   <script type="text/javascript" src="/js/flexigrid/flexigrid.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/alert/jquery.easydrag.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/alert/jquery.alert.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/accessorylist.js" charset="utf-8"></script>
    	<#break>
    	<#case "accessoryadd">
    	   <script type="text/javascript" src="/js/flexigrid/flexigrid.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/js/accessoryadd.js" charset="utf-8"></script>
    	<#break>
    	<#case "profitList">
    	   <script type="text/javascript" src="/js/flexigrid/flexigrid.js" charset="utf-8"></script>
    	   <script type="text/javascript" src="/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
    	<#break>
    </#switch>
</#if>