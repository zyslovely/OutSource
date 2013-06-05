$("#newStudent_download").click(function(){
	
	
});

function newStudentCreateSpecialtyChange(opt){
	
	_specialtyId=$(opt).val();
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'getList',function submitCBClassList(_list){
		for(i=0;i<_list.length;i++){
			
		}
	});
};

function submitCBClassList(_list){

	
};