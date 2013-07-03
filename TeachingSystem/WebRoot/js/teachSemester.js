$("#newSemester_Create").click(function(){
	
	_name=$("#newSemester_Name").val();
	if(_name==''){
		alert("请输入名字");
		return;
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addNewSemester',_name,function(flag){
		if(flag){
			jAlert("添加成功","恭喜",function(){
				location.href=location.href;
		      });
		}else{
			jAlert("操作失败","悲剧",function(){
			
			});
		}
	});
});

$(".new_teachSemester_delete").click(function(){
	_teachSemester=$(this).attr("data_id");
	jConfirm('确定要删除么','删除操作',function(_flag){
		if(_flag){
			dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'deleteSemester',_teachSemester,function(flag){
				if(flag){
					jAlert("删除成功","恭喜",function(){
						location.href=location.href;
				      });
				}else{
					jAlert("删除失败","悲剧",function(){
					
					});
				}
			});
		}
	
	});
});