$("#courseInfo_endCourse").click(function(){
jConfirm('确定要结束课程么','结束课程操作',function(_flag){
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
});

function feedbackClick(courseId){

jHtml("<div style='width:400px;height:228px;'><textarea id='textarea_"+courseId+"' style='width:400px;height:200px;' rows='3' cols='100' value='反馈内容'></textarea><input type='button' style='float: right;' value='确定' onClick='backClick("+courseId+")'/></div>","反馈内容","600","228");
};

function backClick(_courseId){

	_contentOpt= $("#textarea_"+_courseId);
	if(_contentOpt.val()==''){
		return;
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'addFeedBack',0,0,_contentOpt.val(),_courseId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});


};