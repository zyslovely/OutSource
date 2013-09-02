_teachType = -1;
$("#newTeach_Create").click(function() {

	$("#create_newTeach_Profile").css("display", "block");
	$("#normal_newTeach").css("display", "none");
	$("#newTeach_Create").css("display", "none");
});

$("#newTeach_Submit").click(
		function() {

			_name = $("#newTeach_name").val();
			if (_teachType <= 0) {
				alert("请选择课程类型");
				return;
			}
			if (_name == "") {
				alert("课程名称不能为空");
			}
			dwr.engine._execute(_cfg_host + "/dwr", 'TeachSysAdminBean',
					'addNewTeach', _name, _teachType, function(flag) {
						if (flag) {
							jAlert("添加成功", "恭喜", function() {
								location.href = location.href;
							});
						} else {
							jAlert("添加失败", "恭喜", function() {
							});
						}
					});
		});

$(".admin_newTeach_delete").click(
		function() {
			_teachId = $(this).attr("data_id");
			jConfirm('确定要删除么', '删除操作', function(_flag) {

				if(_flag){
					dwr.engine._execute(_cfg_host + "/dwr", 'TeachSysAdminBean',
							'deleteNewTeach', _teachId, function(flag) {
								if (flag) {
									location.href = location.href;
								}
							});
				}
				
			});
		});

function teachTypeChoice(obj, val) {
	_teachType = val;
}


