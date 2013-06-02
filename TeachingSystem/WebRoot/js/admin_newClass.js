$("#newClass_Create").click(function(){
     specialtyId=$("#newClass_Create_Specialty").val();
     name=$("#newClass_Create_Name").val();
     dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addClassRoom',name,0,specialtyId,submitCBBrand);
});

function submitCBBrand(flag){
	
	jAlert('消息内容','123');
};