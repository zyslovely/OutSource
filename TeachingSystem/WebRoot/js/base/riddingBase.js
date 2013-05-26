/**
 * 点击退出
 */
$("#quit").click(function(){
	WB2.anyWhere(function(W){
        W.parseCMD("/account/end_session.json?access_token="+userAccessToken, function(sResult, bStatus){
         	location.href=cfg_host+"/logout/";
        },{},{method: 'get'});
    });

});
function quitCB(){
	
};