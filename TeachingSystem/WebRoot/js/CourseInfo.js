$("#courseInfo_endCourse").click(function(){
	
	_courseId=$("#courseInfo_endCourse").attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'finishCourse',_courseId,function(flag){
		if(flag){
			jAlert("添加成功","恭喜",function(){
				location.href=location.href;
			});
		}else{
			jAlert("添加失败","恭喜",function(){
			});
		}
	});
});