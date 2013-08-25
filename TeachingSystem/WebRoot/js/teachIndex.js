function semestersChange(obj,val){
	

	if(parseInt(val)<0){
		location.href="/teach/index/";
	}else if(_isVisitor!=1){
		location.href="/teach/index/?semesterId="+val+"&userId="+_hostUserId;
	}else{
		location.href="/teach/index/?semesterId="+val
	}
	
};

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


$(".teachIndex_delete").click(function(){
	 _courseId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteCourse',_courseId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
});