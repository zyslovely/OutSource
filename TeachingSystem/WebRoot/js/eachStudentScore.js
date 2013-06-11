$("#EachStudentScore_save").click(function(){
	
	_opts=$(".EachStudentScore_input");
	
	_courseId=$("#EachStudentScoreDiv").attr("data_courseId");
	for(i=0;i<_opts.length;i++){
		_score=$(_opts[i]).attr("value");
		if(_score>100){
			alert("分数不能大于100!");
			return;
		}
		if(_score<0){
			alert("分数不能小于0!");
			return;
		}
	}
	  for(i=0;i<_opts.length;i++){
	  	  _studentId=$(_opts[i]).attr("data_userId");
	  	  _groupId=$(_opts[i]).attr("data_groupId");
	  	  _score=$(_opts[i]).attr("value");
	  	  
	  	  dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysStudentBean', 'addGroupScore',_studentId,_courseId,_groupId,_score,function(flag){
	  	  		
	      });
	  }
	   jAlert("添加完成","恭喜",function(){
			                	location.href=location.href;
	                       	});
});