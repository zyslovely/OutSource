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

function newSpecialtyCreateClassChange(opt){
	
	_specialtyId=$(opt).val();
	location.href="/teach/admin/class/list/?specialtyId="+_specialtyId;
};