$("#newClass_Create").click(function(){
     specialtyId=$("#newClass_Create_Specialty").val();
     name=$("#newClass_Create_Name").val();
     dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addClassRoom',name,0,specialtyId,function(flag){
        if(flag){
        	jAlert('添加成功','小提示',function(){
        	   location.href=location.href;
        	});
        }else{
        	jAlert('添加失败','小提示');
        }
     });
});

function newSpecialtyCreateClassChange(obj,val){
	

	location.href="/teach/admin/class/list/?specialtyId="+val;
};

$(".admin_newClass_delete").click(function(){
	jConfirm('确定要删除么','删除操作',function(_flag){
    _classId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteClass',_classId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	});
});