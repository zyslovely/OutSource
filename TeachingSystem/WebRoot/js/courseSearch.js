
function courseSearchSepcialtyChange(obj,val){
	location.href="/teach/search/?specialtyId="+val;
};

function courseSearchClassChange(obj,val){
	
	location.href="/teach/search/?specialtyId="+_specialtyId+"&classId="+val+"&semesterId="+_semesterId;
};

function courseSearchSemesterChange(obj,val){
	
	location.href="/teach/search/?specialtyId="+_specialtyId+"&classId="+_classId+"&semesterId="+val;
};

$("#courseSearch_search").click(function(){
	
	_opts=$(".courseSearch_property_input");
	var _tag="";
	for(i=0;i<_opts.length;i++){
		
		_tag+=$(_opts[i]).attr("data_id");
		_tag+=",";
		_tag+=$(_opts[i]).val();
		_tag+=";";
		
	}
	location.href="/teach/search/?specialtyId="+_specialtyId+"&classId="+_classId+"&semesterId="+_semesterId+"&properties="+_tag;
	
});