function semestersChange(opt){
	
	_semesterId=$(opt).val();
	if(parseInt(_semesterId)<0){
		location.href="/teach/index/";
	}else{
		location.href="/teach/index/?semesterId="+_semesterId;
	}
	
};