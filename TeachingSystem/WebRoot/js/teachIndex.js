function semestersChange(obj,val){
	

	if(parseInt(val)<0){
		location.href="/teach/index/";
	}else{
		location.href="/teach/index/?semesterId="+val;
	}
	
};


$(".teachIndex_delete").click(function(){
	 _courseId=$(this).attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteCourse',_courseId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
});