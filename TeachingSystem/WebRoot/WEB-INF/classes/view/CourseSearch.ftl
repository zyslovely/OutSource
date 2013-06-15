<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "courseSearch" />
<#include "head.ftl">
<style type="text/css">

html,body,#gmap{height:100%; margin:0;}
body{min-width:1024px;min-height:600px}

</style>
<body >

   <#include "top.ftl"/>
   <div>
       <table>
            <tbody>
                 <tr>
                    <th>专业:</th>
                    <th>
                       <select>
                            <#list> 
                               <option val=""></option>
                            </#list>
                       </select>
                    </th>
                 </tr>
                 <tr>
                    <th>班级:</th>
                    <th>
                       <select>
                            
                       </select>
                    </th>
                 </tr>
                 <tr>
                    <th>属性:</th>
                    <th>
                       
                    </th>
                 </tr>
            </tbody>
       </table>
   </div>
   
</body>
</html>
</#escape>
		
<#include "js.ftl">