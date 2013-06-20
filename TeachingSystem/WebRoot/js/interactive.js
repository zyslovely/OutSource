$("#interactive_send").click(function(){
	
	_text=$("#interactive_send_text").val();
	_courseId=$("#interactive_send_select").val();
	if(_courseId==undefined){
		_courseId=0;
	}
	_checked=$("#interactive_checkbox").attr("checked");
	status=0;
	if(_checked){
		status=1;
	}
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysBean', 'addInteractive',_text,_courseId,status,'',0,function(flag){
		if(flag){
			location.href=location.href;
		}
	});

});

function forward(_id){

	jHtml("<div style='width:400px;height:228px;'><textarea id='textarea_"+_id+"' style='width:400px;height:200px;' rows='3' cols='100' value=''></textarea><input type='button' style='float: right;' value='确定' onClick='forwardClick("+_id+")'/></div>","转发内容","600","228");
	
};
	
	
function forwardClick(_id){
	
	_contentOpt= $("#textarea_"+_id);
	if(_contentOpt.val()==''){
		return;
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'addForward',_contentOpt.val(),_id,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	
	
};

function back(_id){

jHtml("<div style='width:400px;height:228px;'><textarea id='textarea_"+_id+"' style='width:400px;height:200px;' rows='3' cols='100' value=''></textarea><input type='button' style='float: right;' value='确定' onClick='backClick("+_id+")'/></div>","回复内容","600","228");
	
}

function backClick(_id){
	
	_contentOpt= $("#textarea_"+_id);
	if(_contentOpt.val()==''){
		return;
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'addInteractiveBack',_id,_contentOpt.val(),function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	
	
};

function comment(_id){
	
	if($("#interactive_"+_id).css("display")=='none'){
		$("#interactive_"+_id).css("display","block");
	}else{
		$("#interactive_"+_id).css("display","none");
	}
};

function deleteInteractive(_id){
	
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'deleteInteractive',_id,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
};

function deleteInteractiveBack(_id){
	
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysBean', 'deleteInteractiveBack',_id,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
};
