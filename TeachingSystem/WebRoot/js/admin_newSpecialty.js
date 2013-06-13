$("#newSpecialty_Create").click(function(){
     name=$("#newSpecialty_Name").val();
     shortName=$("#newSpecialty_ShortName").val();
     dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'addSpecialty',name,shortName,4,submitCB);
});

function submitCB(flag){
	
	jAlert('添加专业成功','恭喜',function(flag){
		location.href=location.href;
	});
};