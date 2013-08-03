var _isEdit = false;
var _type = 0;
var _infoType = 0;
var _bigPhotoUrl = "";
var _smallPhotoUrl = "";
function schoolInfoTypeChange(obj, val) {

	location.href = "/teach/admin/schoolInfo/list/?type=" + val;

};

function deleteSchoolInfo(id) {

	jConfirm('确定要删除么', '删除操作', function(_flag) {

		if (_flag) {
			dwr.engine._execute(_cfg_host + "/dwr", 'TeachSysAdminBean',
					'deleteSchoolInfo', id, function(flag) {
						if (flag) {
							location.href = location.href;
						}
					});
		}

	});

};

$("#newSchoolInfo_Create").click(
		function() {

			if (_isEdit) {

				_title = $("#newSchoolInfo_title").val();
				_desc = $("#newSchoolInfo_content").val();

				if (_title == "") {
					jAlert('请添加标题', '错误!');
					return;
				}

				if (_desc == "") {
					jAlert('请添加内容', '错误!');
					return;
				}
				if (_bigPhotoUrl == "" || _smallPhotoUrl == "") {
					jAlert('请上传图片', '错误!');
					return;
				}

				dwr.engine._execute(_cfg_host + "/dwr/", 'TeachSysAdminBean',
						'addSchoolInfo', _title, _desc, _bigPhotoUrl,
						_smallPhotoUrl, _type, _infoType, function(flag) {

							if (flag) {
								_isEdit = false;
								location.href = location.href;
								return;
							} else {
								alert("添加失败");
							}

						});

			} else {

				_isEdit = true;
				$("#create_newCourseType_list").hide();
				$("#newSchoolInfo_select").hide();
				$("#create_newSchoolInfo").show();
				$("#newSchoolInfo_Create").text("保存");
			}
		});

function newschoolInfoTypeChange(obj, val) {

	_type = val;
};

function newschoolInfoInfoTypeChange(obj, val) {

	_infoType = val;
};

function saveImage2() {

	$("#uploadFrame_2").ajaxSubmit(function(message) {
		if (message != '') {
			var jsObject = eval('(' + message + ')');
			$("#image_2").attr("src", jsObject.imageUrl);
			_bigPhotoUrl = jsObject.imageUrl;
			_smallPhotoUrl = jsObject.smallImageUrl;
			$("#image_2").show();
		}
	});
	return false;
};

