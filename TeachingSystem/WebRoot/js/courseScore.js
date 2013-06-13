$("#courseScore_save").click(function(){
	
	_opts=$(".courseScore_input");
	_courseId=$("#CourseScoreDiv").attr("data_id");
	_stage=$("#CourseScoreDiv").attr("data_stage");
	_percentType=$("#CourseScoreDiv").attr("data_percentType");
	for(i=0;i<_opts.length;i++){
		_score=$(_opts[i]).attr("value");
		if(_score>100){
			alert("分数不能大于100!");
			return;
		}
	}
	  for(i=0;i<_opts.length;i++){
	  	  _studentId=$(_opts[i]).attr("data_id");
	  	  _score=$(_opts[i]).attr("value");
	  	  if(_score<0){
	  	  	continue;
	  	  }
	  	  if(_stage<0&&_percentType!=2){
	  	  	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'insertCourseScore',_courseId,_studentId,_percentType,_score,function(flag){
	  	  		
	      });
	  	  }else if((_stage>0&&_percentType==2)){
	  	    dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'insertCourseStageScore',_courseId,_stage,_score,_studentId,function(flag){
	  	    	
	      });
	  	  }

	  }
	   jAlert("添加完成","恭喜",function(){
			                	location.href=location.href;
	                       	});
});

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
