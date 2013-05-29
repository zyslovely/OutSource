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
	
	
});