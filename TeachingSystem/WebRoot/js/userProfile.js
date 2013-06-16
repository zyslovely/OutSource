$("#userProfile_save").click(function(){
	
	_pass1=$("#newProfile_password_1").val();
	_pass2=$("#newProfile_password_2").val();
	if(_pass1==''&&_pass2==''){
		return;
	}
	if(_pass1!=_pass2){
		alert("两次密码输入不同");
	}
	if(_pass1==''){
		alert('密码不能为空');
	}
	 dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'changePass',_pass1,_pass2,function(flag){
        if(flag){
        	jAlert('更新成功','小提示',function(){
        	   location.href=location.href;
        	});
        }else{
        	jAlert('更新失败','小提示');
        }
     });
});