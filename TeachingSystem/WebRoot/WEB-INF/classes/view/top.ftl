<div style="height:120px;width:100%;background: url('/img/teachCreate/nav_bg.png') repeat-x scroll center top transparent; ">
   <div style="margin-left: 15%; margin-top: 23px; width: 40%; float: left;">
       <img src="/img/webIndex/logo.png"/>
   </div>
   <div style="float: right; margin-right: 10%; margin-top: 23px;height:52px;">
       <ul style="width:350px;">
          <li style="float: left; width: 80px; text-align: center;">
             <a href="/user/profile/" style="color: white;">${visitName!""}</a>
          </li>
          <li style="float: left; width: 3px; text-align: center;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <a href="/logout/" style="color: white;">退出</a>
          </li>
          <li style="float: left; width: 3px; text-align: center;">
          <span style="color: white;">|<span>
          </li>
          <li style="float: left; width: 80px; text-align: center;">
             <a href="/help/" style="color: white;">帮助</a>
          </li>
       </ul>
   </div>
   <div style="height: 49px; width: 100%; display: inline-block;">
     <ul style="margin-left:15%;width:85%;">
        <li style="text-align: center; float: left; width: 140px; padding: 15px; height: 15px;<#if pageName=='teachIndex'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/index/" style="<#if pageName!='teachIndex'>color: white</#if>;">课程列表</a>
        </li>
        <#if level==1>
        <li style="text-align: center; float: left; width: 140px; padding: 15px; height: 15px;<#if pageName=='teachCreate'>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           <a href="/teach/create/" style="<#if pageName!='teachCreate'>color: white;</#if>">创建课程</a>
        </li>
        </#if>
        <li style="text-align: center; float: left; width: 140px; padding: 15px; height: 15px;<#if pageName==''>background: url('/img/teachCreate/tab_bg.png') no-repeat scroll center top transparent;</#if>">
           
        </li>
     </ul>
   </div>
</div>