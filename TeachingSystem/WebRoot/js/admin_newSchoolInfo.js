var _isEdit=false;
var _type=0;
var _infoType=0;
var _bigPhotoUrl="";
var _smallPhotoUrl = "";
function schoolInfoTypeChange(obj,val){
	
	location.href="/teach/admin/schoolInfo/list/?type="+val;
	
};


$("#newSchoolInfo_Create").click(function(){
	
	if(_isEdit){
	
		_isEdit=false;
		_name=$("#newCourseType_name").val();
		_percentOpt=$(".newCourseType_scoreType_percent");
		_typeIds=new Array();
		_percentIds=new Array();
		_total=0;
		for(var i=0;i<objs.length;i++){
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
		$("#create_newCourseType_list").hide();
		$("#newSchoolInfo_select").hide();
		$("#create_newSchoolInfo").show();
		$("#newSchoolInfo_Create").text("保存");
	}
});

function newschoolInfoTypeChange(obj,val){
	

	_type=val;
};


function newschoolInfoInfoTypeChange(obj,val){
	
	
	_infoType=val;
};

function saveImage2(){
	
$("#uploadFrame_2").ajaxSubmit(function(message) {
if(message!=''){
	var jsObject = eval('('+message+')'); 
	$("#image_2").attr("src",jsObject.imageUrl);
	_bigPhotoUrl = jsObject.imageUrl;
	_smallPhotoUrl = jsObject.smallImageUrl;
	$("#image_2").show();
}
});
return false;
};

