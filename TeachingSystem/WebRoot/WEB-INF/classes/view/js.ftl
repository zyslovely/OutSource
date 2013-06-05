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
    <script type="text/javascript" src="/selector/js/jquery-ui-1.8.16.custom.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="/selector/js/jquery.ui.selectmenu.js" charset="utf-8"></script>
    <script type="text/javascript" src="/selector/js/scripts.js" charset="utf-8"></script>
    
    
	<#switch pageName>
    	<#case "webIndex">
    	
   	       <script type="text/javascript" src="/js/webIndex.js" charset="utf-8"></script>
    	<#break>
    	<#case "teachIndex">
    	
   	       <script type="text/javascript" src="/js/upload.js" charset="utf-8"></script>
   	       
   	       
    	<#break>
    	<#case "teachCreate">
    	  
   	       <script type="text/javascript" src="/js/teachCreate.js" charset="utf-8"></script>
    	<#break>
    	<#case "newClass">
    	  
   	       <script type="text/javascript" src="/js/admin_newClass.js" charset="utf-8"></script>
    	<#break>
    	<#case "newSpecialty">
    	  
   	       <script type="text/javascript" src="/js/admin_newSpecialty.js" charset="utf-8"></script>
    	<#break>
    	<#case "newStudent">
    	  
   	       <script type="text/javascript" src="/js/subNav.js" charset="utf-8"></script>
   	       <script type="text/javascript" src="/js/admin_newStudent.js" charset="utf-8"></script>
    	<#break>

    	
    	
    	
    </#switch>
</#if>