$(document).ready(function(){
	//把ppt插入到stageContain中
	$("#docShower").appendTo("#stageContain");
	$("#textCation").css("background-color","#E1E1E1");
	$("#textCation div:first").removeAttr("style");
	//修改上一页下一页click事件
	$("#pre").hide();
	$("#next").hide();
});