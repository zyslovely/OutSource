
$("#newStudent_download").click(function(){
	
	
});

function newStudentCreateSpecialtyChange(obj,val){
	
	location.href="/teach/admin/student/list/?specialtyId="+val;
};

function newStudentCreateClassChange(obj,val){
	_classId=$("#newStudent_Class_list").val();
	_specialtyId=$("#newStudent_Specialty_list").val();
	location.href="/teach/admin/student/list/?classId="+_classId+"&specialtyId="+_specialtyId;
};


$("#newStudent_download").click(function(){
	_specialtyId=$("#newStudent_Specialty_list").val();
    _classId=$("#newStudent_Class_list").val();
    if(_classId==undefined||_classId<0){
    	alert("请选择添加班级");
    	return;
    }
    location.href="/teach/teacher/download/addStudent/?classId="+_classId;
});

function newfileChange(opt){
	
     _val=$(opt).val();
     if(_val==''){
     	return;
     }
     _classId=$("#newStudent_Class_list").val();
	 _specialtyId=$("#newStudent_Specialty_list").val();
	 if(_specialtyId<=0||_classId<=0){
	 	alert("请选择专业和班级");
	 	return;
	 }
     $("#fileUpdate_form").ajaxSubmit(function(message) {
     	location.href=location.href;
     });
     return false;
};

$(".admin_newStudent_delete").click(function(){
	jConfirm('确定要删除么','删除操作',function(_flag){
	_studentId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteUser',_studentId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	});
});

$(".admin_newStudent_end").click(function(){
	
	_studentId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'endAllSemester',_studentId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
});


