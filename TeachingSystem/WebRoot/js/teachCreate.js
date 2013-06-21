_semesterId=-1;
_classId=-1;
function coursePercentTypeChange(obj,val){
	  
	 $(".coursePercentTypeDemos_ul").css("display","none");
	 $(".coursePercentTypeDemos_property_ul").css("display","none");
	 $("#coursePercentTypeDemos_"+val).css("display","block");
	 $("#coursePercentTypeDemos_property_"+val).css("display","block");
	 
	
};

function semesterChange(obj,val){
	_semesterId=val;
};

function classChange(obj,val){
	_classId=val;
};

function specialtyChange(obj,val){
	

	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'getList',val,function(list){
		$('#class_selector').html("");
		$('#jgd_dd_class_selector').remove();
		if(list){
			for(i=0;i<list.length;i++){
				$('#class_selector').append("<option value="+list[i].id+">"+list[i].name+"</option>");
			}
		}
		$('#class_selector').jgdDropdown({callback: function(obj, val) { classChange(obj,val) }});
	});
	
};

$("#teachCreate_save").click(function(){
	
	if(_classId<0){
		alert("请选择班级");
		return;
	}
	if(_semesterId<0){
		alert("请选择专业");
		return;
	}
	course_name=$("#course_name").val();
	desc=$("#desc_textarea").val();
	_demoId=$("#demoselector").val();
	
	 _opts=$(".coursePercentTypes_biaozhun_li_"+_demoId);
	 _percents=$(".coursePercentTypes_biaozhun_input_"+_demoId);
	 _teachers=$(".coursePercentTypes_biaozhun_selectteacher_"+_demoId);
	 _propertiesOpts=$(".coursePercentTypes_shuxing_li_"+_demoId);
	 _objectCounts=$(".coursePercentTypes_biaozhun_input_objectCount_"+_demoId);
	 var _courseScorePercents=new Array();
	 var _properties=new Array();
	 for(i=0;i<_opts.length;i++){
	 	_tempOpt=_opts[i];
	 	_percentType=$(_tempOpt).attr("data_type_id");
	 	_objectCount=$(_objectCounts[i]).val();
	 	//分组
	 	if(_percentType==4&&_objectCount==0){
	 		alert("分组数量不能为0");
	 		return;
	 	}
	 	if(_percentType==2&&_objectCount==0){
	 		alert("多次课程不能为0");
	 		return;
	 	}
	 	 var _courseScorePercent={
	 		                  "percentType": _percentType,
	 		                  "percent": $(_percents[i]).val(),
	 		                  "teacherId": $(_teachers[i]).val()==undefined?0:$(_teachers[i]).val(),
	 		                  "objectCount":_objectCount
	 	                    };
	 	      _courseScorePercents.push(_courseScorePercent);             
	 }
	 for(i=0;i<_propertiesOpts.length;i++){
	 	_tempOpt=_propertiesOpts[i];
	 	_typeId=$(_tempOpt).attr("data_type_id");
	 	_propertiesTypeOps=$(".coursePercentTypes_shuxing_checkbox_"+_demoId+"_"+_typeId);
	 	for(j=0;j<_propertiesTypeOps.length;j++){
	 	_checked=$(_propertiesTypeOps[j]).attr("checked");
	    	if(_checked){
	    		var _property={
	    		         "percentType" : _typeId,
	    		         "propertyId" : $(_propertiesTypeOps[j]).attr("data_property_id"),
	    		         "percent" : $(_percents[i]).val()
	    	          };
	    	          _properties.push(_property);
	    	}

	 	}
	 }
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'addNewCourse',course_name,_courseScorePercents,_semesterId,_classId,_properties,desc,submitCBBrand);
	
});

function submitCBBrand(semesterId){
	if(semesterId>0){
		jAlert("课程创建成功","恭喜",function(){
			location.href="/teach/index/?semesterId="+semesterId;
		});
	}else{
		jAlert("课程创建失败","悲剧",function(){
		});
	}
};

