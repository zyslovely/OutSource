
_teacherLevel=-1;
$("#newTeacher_Create").click(function(){
	$("#create_newTeacher_Profile").css("display","block");
	$("#normal_newTeacher").css("display","none");
	$("#newTeacher_Create").css("display","none");
});


$("#newTeacher_Submit").click(function(){
	
	_name=$("#newTeacher_name").val();
	_username=$("#newTeacher_username").val();
	_password=$("#newTeacher_password").val();
	if(_teacherLevel<=0){
		alert("请选择教师类型");
		return;
	}
	if(_name==""||_username==""||_password==""){
		alert("内容为空，请填写教师信息");
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addTeacherPassPort',_name,_teacherLevel,_username,_password,function(flag){
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

$(".admin_newTeacher_delete").click(function(){
jConfirm('确定要删除么','删除操作',function(_flag){
	_teacherUserId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteUser',_teacherUserId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
});
});

function teacherTypeChoice(obj,val){
	_teacherLevel=val;
}

function newfileChange(opt){
	
     $("#fileUpdate_form").ajaxSubmit(function(message) {
     	location.href=location.href;
     });
     return false;
};