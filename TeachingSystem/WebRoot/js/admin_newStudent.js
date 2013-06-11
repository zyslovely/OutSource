$("#newStudent_download").click(function(){
	
	
});

function newStudentCreateSpecialtyChange(opt){
	
	_specialtyId=$(opt).val();
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'getList',_specialtyId,function submitCBClassList(_list){
		$("#newStudent_Class_list").empty();
		$("#newStudent_Class_list").append("<option value='0'></option>");
		for(var i=0;i<_list.length;i++){
			$("#newStudent_Class_list").append("<option value="+_list[i].id+">"+_list[i].name+"</option>");
		}
	});
};

function newStudentCreateClassChange(opt){
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
     $("#fileUpdate_form").ajaxSubmit(function(message) {
     	location.href=location.href;
     });
     return false;
};


