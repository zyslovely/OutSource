<#-- js文件加载ftl -->
<#if pageName?exists>
    <script type="text/javascript" src="/js/base/engine.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/util.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery-1.7.2.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/base/jquery.easyui.min.js" charset="utf-8"></script>
    
	<#switch pageName>
    	<#case "webIndex">
    	
   	       <script type="text/javascript" src="/js/webIndex.js" charset="utf-8"></script>
    	<#break>
    	<#case "teachIndex">
    	
   	       <script type="text/javascript" src="/js/upload.js" charset="utf-8"></script>
    	<#break>
    </#switch>
</#if>