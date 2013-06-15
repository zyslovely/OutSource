$("#interactive_send").click(function(){
	
	_text=$("#interactive_send_text").val();
	_courseId=$("#interactive_send_select").val();
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysBean', 'addInteractive',_text,_courseId,0,'',0,function(flag){
		if(flag){
			location.href=location.href;
		}
	});

});