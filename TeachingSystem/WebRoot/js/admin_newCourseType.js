var _isEdit=false;
$("#newCourseType_newPercent").click(function(){
	
	$("#newCourseType_scoreType").append("<div style='height: 35px;'><select  class='newCourseType_scoreType_select'><option value=\"0\">平时成绩</option>" +
			"<option value=\"1\">考试成绩</option><option value=\"2\">多次平时考试成绩</option>" +
			"<option value=\"3\">创意成绩</option><option value=\"4\">团队互评成绩</option>" +
			"</select><input class='newCourseType_scoreType_percent' type=\"text\" value='' style=\"width:50px;margin-left:10px\"/><span style=\"margin-left:10px;\">%</span></div>");
			
});

$("#newCourseType_Create").click(function(){
	
	if(_isEdit){
		
		_name=$("#newCourseType_name").val();
		_typeIdOpt=$(".newCourseType_scoreType_select");
		_percentOpt=$(".newCourseType_scoreType_percent");
		_typeIds=new Array();
		_percentIds=new Array();
		_total=0;
		for(i=0;i<_typeIdOpt.length;i++){
			_typeIds.push($(_typeIdOpt[i]).val());
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