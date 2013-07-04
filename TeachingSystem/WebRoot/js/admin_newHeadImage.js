
function saveImage1(){
$("#uploadFrame_1").ajaxSubmit(function(message) {
if(message!=''){
	
	alert("操作成功，请刷新下页面");
}
});
return false;
};


function saveImage2(){
$("#uploadFrame_2").ajaxSubmit(function(message) {
if(message!=''){
	alert("操作成功，请刷新下页面");
}
});
return false;
};
