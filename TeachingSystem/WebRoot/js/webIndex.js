var _nowIndex=0;
$(document).ready(function(){ 
     setInterval(changeImageInterval, 5000);
});

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
});

$("#dot_1").hover(function(){
	_nowIndex=0;
	changeImage();
});

$("#dot_2").hover(function(){
	_nowIndex=1;
	changeImage();
});
function changeImageInterval(){
	if(_nowIndex==0){
		_nowIndex=1;
	}else{
		_nowIndex=0;
	}
	changeImage();
}

function changeImage(){
	
	if(_nowIndex==0){
		$("#img_2").fadeOut(500,function(){
		      $("#img_1").fadeIn(500);
		      $("#dot_1").css("background-image","url('/img/webIndex/pic_mask_dot_select.png')");
		      $("#dot_2").css("background-image","url('/img/webIndex/pic_mask_dot.png')");
		});
	}else if(_nowIndex==1){
		$("#img_1").fadeOut(500,function(){
		      $("#img_2").fadeIn(500);
		      $("#dot_1").css("background-image","url('/img/webIndex/pic_mask_dot.png')");
		      $("#dot_2").css("background-image","url('/img/webIndex/pic_mask_dot_select.png') ");
		      
		});
	}else{
		
	}
}

