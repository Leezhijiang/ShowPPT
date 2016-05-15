$(document).ready(function(){
	//把ppt插入到stageContain中
	$("#pptShower").appendTo("#stageContain");
	//修改上一页下一页click事件
	$("#pre").click(getPre);
	$("#next").click(getNext);
	
});