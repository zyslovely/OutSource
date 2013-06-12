$("#courseInfo_endCourse").click(function(){
	
	_courseId=$("#courseInfo_endCourse").attr("data_id");
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'finishCourse',_courseId,function(tag){
		if(tag==1){
			jAlert("结束课程成功","恭喜",function(){
				location.href=location.href;
			});
		}else if(tag==2){
			jAlert("还有学生的成绩没有录入","失败",function(){
		      });
		}else{
			jAlert("操作失败","失败",function(){
			
			});
		}
	});
});