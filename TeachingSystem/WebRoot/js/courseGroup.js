function deleteGroup(_id){

	jConfirm('确定要删除么','删除操作',function(_flag){
	if(_flag){
		dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'deleteCoursePercentTypeGroup',_id,function(flag){
	  if(flag){
		location.href=location.href;
	   }
		});
	}
	});
	
};


$("#courseGroup_New").click(function(){
	
	_students_leaderOpt=$(".courseGroup_checkbox_leader");
	_students_teamerOpt=$(".courseGroup_checkbox_teamer");
	_courseId=$("#courseGroup_DIV").attr("data_id");
	_coursePercentTypeGroupStudents=new Array();
	index=0;
	for(i=0;i<_students_leaderOpt.length;i++){
		if($(_students_leaderOpt[i]).attr("checked")){
			index++;
		}
	}
	if(index==0){
		alert("不可以没有组长");
		return;
	}else if(index>1){
		alert("不可以多于1个组长");
		return;
	}
	for(i=0;i<_students_leaderOpt.length;i++){
		if(!$(_students_leaderOpt[i]).attr("checked")&&!$(_students_teamerOpt[i]).attr("checked")){
			continue;
		}
		if($(_students_teamerOpt[i]).attr("checked")&&$(_students_leaderOpt[i]).attr("checked")){
			alert("不能同时选择学生两种属性");
			location.href=location.href;
			return;
		}
	    _leader=false;
		if($(_students_leaderOpt[i]).attr("checked")){
			_leader=true;
		}
		
		var _coursePercentTypeGroupStudent={
	 		                  "studentId": _leader?$(_students_leaderOpt[i]).attr("data_id"):$(_students_teamerOpt[i]).attr("data_id"),
	 		                  "level": _leader?1:0
	 	                    };
	 	_coursePercentTypeGroupStudents.push(_coursePercentTypeGroupStudent);
	}
	
	
    dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'addCourseGroup',_courseId,_coursePercentTypeGroupStudents,function(flag){
	if(flag){
		location.href=location.href;
	}else{
		alert("添加出错，请检查分组数量是否超过预设定的数量");
	}
});
});