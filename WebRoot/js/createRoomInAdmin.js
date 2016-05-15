
$(document).ready(function(){
    //隐藏创建界面
    $("#createUI").hide();
});
function createRoomInAdmin(){
    if ($("#new").html() == "新文件") {
		//修改value
		$("#new").html("返回");
        //隐藏上下页按钮,contain
        $("#stageContain").hide("500");
        $("#pre").hide("500");
        $("#next").hide("500",function(){
			$("#createUI").show("500");
		});
        //显示创建页面
        
    }else{
		$("#new").html("新文件");
			//隐藏界面
		  $("#createUI").hide("500",function(){
		  	   $("#stageContain").show("500");
        	$("#pre").show("500");
			 $("#next").show("500");
		  });
	}
}
