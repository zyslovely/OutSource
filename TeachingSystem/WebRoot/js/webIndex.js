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
	doLogin();
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

$("#password").focus(function(){
	$("#username_div").css("background-image","url('/img/webIndex/input_bg.png')");
	$("#password_div").css("background-image","url('/img/webIndex/input_bg_focus.png')");
});

$("#username").focus(function(){
	$("#username").val("");
	$("#password_div").css("background-image","url('/img/webIndex/input_bg.png')");
	$("#username_div").css("background-image","url('/img/webIndex/input_bg_focus.png')");
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

$("#forget_pass").click(function(){
	
	alert("1、带好学生证、身份证或者学生证或由班主任代办。\n" +
			"2、请到办公楼5楼计算机教学部找专业老师办理。\n" +
			"3. 密码查询和修改时间：每周二15:00~16:00");
});

