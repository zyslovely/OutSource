var _isEdit=false;
var objs=new Array();
$("#newCourseType_newPercent").click(function(){
	
	
});

$("#newCourseType_Create").click(function(){
	
	if(_isEdit){
		
		_name=$("#newCourseType_name").val();
		_percentOpt=$(".newCourseType_scoreType_percent");
		_typeIds=new Array();
		_percentIds=new Array();
		_total=0;
		for(i=0;i<objs.length;i++){
			_typeIds.push(objs[i].val);
			_percentIds.push(parseInt($(_percentOpt[i]).val()));
			_total+=parseInt($(_percentOpt[i]).val());
			if($(_percentOpt[i]).val()==''||$(_percentOpt[i]).val()==0){
				jAlert('评分数不能为0','错误!');
				return;
			}
		}
		if(_total!=100){
			
			jAlert('评分总和需要满足100%','错误!');
			return;
		}
		dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysAdminBean', 'addCoursePercentTypeDemo',_name,_typeIds,_percentIds,function(flag){
			
			if(flag){
				location.href=location.href;
				return;
			}else{
				alert("添加失败");
			}
			
		});
	}else{
		
		$("#create_newCourseType").css("display","block");
		$("#create_newCourseType_list").css("display","none");
		_isEdit=true;
		$("#newCourseType_Create").text("保存");
	}
	
});

function deleteCourseType(_id){
	jConfirm('确定要删除么','删除操作',function(_flag){
	dwr.engine._execute(_cfg_host+"/dwr", 'TeachSysAdminBean', 'deleteCourseType',_id,function(flag){
		if(flag){
			location.href=location.href;
		}
	});
	});
};

function scoreType_select(obj,val){
	_in=0;
	for(i=0;i<objs.length;i++){
		if(objs[i].obj==obj){
			objs[i].val=val;
			_in=1;
			break;
		}
	}
	if(_in==0){
	  objs.push({
		"obj":obj,
		"val":val
	  });
	}

};


