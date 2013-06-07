function coursePercentTypeChange(opt){
	  
	 _demoId=$(opt).val();
	 $(".coursePercentTypeDemos_ul").css("display","none");
	 $(".coursePercentTypeDemos_property_ul").css("display","none");
	 $("#coursePercentTypeDemos_"+_demoId).css("display","block");
	 $("#coursePercentTypeDemos_property_"+_demoId).css("display","block");
	 
	
};


$("#teachCreate_save").click(function(){
	
	course_name=$("#course_name").val();
	class_id=$("#class_selector").val();
	semeter_id=$("#semester_selector").val();
	desc=$("#desc_textarea").val();
	_demoId=$("#demoselector").val();
	
	 _opts=$(".coursePercentTypes_biaozhun_li_"+_demoId);
	 _percents=$(".coursePercentTypes_biaozhun_input_"+_demoId);
	 _teachers=$(".coursePercentTypes_biaozhun_selectteacher_"+_demoId);
	 _propertiesOpts=$(".coursePercentTypes_shuxing_li_"+_demoId);
	 var _courseScorePercents=new Array();
	 var _properties=new Array();
	 for(i=0;i<_opts.length;i++){
	 	_tempOpt=_opts[i];
	 	 var _courseScorePercent={
	 		                  "percentType": $(_tempOpt).attr("data_type_id"),
	 		                  "percent": $(_percents[i]).val(),
	 		                  "teacherId": $(_teachers[i]).val()
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
	dwr.engine._execute(_cfg_host+"/dwr/", 'TeachSysTeacherBean', 'addNewCourse',course_name,_courseScorePercents,semeter_id,class_id,_properties,desc,submitCBBrand);
	
});

function submitCBBrand(flag){
	
};

