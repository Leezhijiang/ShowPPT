$(document).ready(function(){$("#aboutText").hide();
$("#createUI").hide();
});
var a = 1;

function onchangeUI(){
	//创建模式变登陆
    if (a == 0) {
        $("#createUI").fadeOut(500, function(){
            $("#loginUI").fadeIn(500);
        });
        a = 1;
    }
    //登陆模式变创建
    else {
        $("#loginUI").fadeOut(500, function(){
			
            $("#createUI").fadeIn(500);
        });
        a = 0;
		getRoomIDAndPD();
    }
}

//切换演示/接受
function show_get(){
    if (a == 1) {
        $("#aboutText").fadeOut(500, function(){
            $("#loginUI").fadeIn(500);
        });
    }
    else {
        $("#aboutText").fadeOut(500, function(){
            $("#createUI").fadeIn(500);
        });
    }
}

//切换关于
function about(){
    if (a == 1) {
        $("#loginUI").fadeOut(500, function(){
            $("#aboutText").fadeIn(500);
        });
    }
    else {
        $("#createUI").fadeOut(500, function(){
            $("#aboutText").fadeIn(500);
        });
    }
}
