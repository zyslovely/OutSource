
$("#remember_me").click(function(){

_remember=$(this).attr("data-remember");
if(_remember==1){
	$(this).attr("data-remember",0);
	$(this).attr("value",0);
	$(this).attr("src",'/img/webIndex/radio_button.png');
}else{
	$(this).attr("data-remember",1);
	$(this).attr("value",1);
	$(this).attr("src",'/img/webIndex/radio_button_select.png');
}

});

$("#submit_login").click(function(){
	
});

function doLogin(){
	_username=$("#username").val();
	_password=$("#password").val();
	_remember=$("#remember_me").attr("data-remember");
	if(_username==''||_password==''){
		alert('请输入用户名或者密码');
	}
	location.href="/login/?username="+_username+"&password="+_password+"&remember="+_remember;
};

$(document).keypress(function(e) {
	if ( e.which == 13){
		doLogin();
	}
})