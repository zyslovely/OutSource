$("#newTeacher_Create").click(function(){
	$("#create_newTeacher_Profile").css("display","inline-block");
	$("#normal_newTeacher").css("display","none");
});


$("#newTeacher_Submit").click(function(){
	
	_name=$("#newTeacher_name").val();
	_username=$("#newTeacher_username").val();
	_password=$("#newTeacher_password").val();
	if(_name==""||_username==""||_password==""){
		alert("内容为空，请填写老师信息");
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addTeacherPassPort',_name,1,_username,_password,function(flag){
		if(flag){
			jAlert("添加成功","恭喜",function(){
				location.href=location.href;
			});
		}else{
			jAlert("添加失败","恭喜",function(){
			});
		}
	});
});