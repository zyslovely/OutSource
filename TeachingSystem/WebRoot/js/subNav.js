function openBrowse(){ 
var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
if(ie){ 
$("#file").click(); 
$("#filename").value=$("#file").value;
}else{
var a=document.createEvent("MouseEvents");//FF的处理 
a.initEvent("click", true, true);  
$("#file").dispatchEvent(a); 
} 
} 