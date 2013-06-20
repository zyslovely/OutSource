$("#newSpecialty_Create").click(function(){
     name=$("#newSpecialty_Name").val();
     shortName=$("#newSpecialty_ShortName").val();
     dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'addSpecialty',name,shortName,6,submitCB);
});

function submitCB(flag){
	
	jAlert('添加专业成功','恭喜',function(flag){
		location.href=location.href;
	});
};

$(".admin_newSpecialty_delete").click(function(){
	jConfirm('确定要删除么','删除操作',function(_flag){
	_specialtyId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteSpecialty',_specialtyId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	});
});