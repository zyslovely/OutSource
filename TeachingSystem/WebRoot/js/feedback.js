

function feedbackBackClick(_feedbackId,_courseId,_toUserId){

jHtml("<div style='width:400px;height:228px;'><textarea id='textarea_"+_feedbackId+"' style='width:400px;height:200px;' rows='3' cols='100' value='回复内容'></textarea><input type='button' style='float: right;' value='确定' onClick='backClick("+_feedbackId+","+_courseId+","+_toUserId+")'/></div>","回复内容","600","228");
};

function backClick(_feedbackId,_courseId,_toUserId){
	
	_contentOpt= $("#textarea_"+_feedbackId);
	if(_contentOpt.val()==''){
		return;
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'addFeedBack',_toUserId,_feedbackId,_contentOpt.val(),_courseId,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	
	
};