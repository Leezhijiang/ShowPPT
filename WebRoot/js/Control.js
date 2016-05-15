//验证用户上传数据是否符合规范
function checkFile(){
	$("#createForm").submit();
	alert("1");
}
//计算图片显示大小
function resizeImage(obj){
    if (screen.availWidth < screen.availHeight) {
        var bili = obj.height / obj.width;
        obj.width = screen.availWidth * 0.9;
        obj.height = obj.width * bili;
    }
    if (screen.availWidth > screen.availHeight) {
        var bili = obj.height / obj.width;
        obj.height = screen.availHeight * 0.9;
        obj.width = obj.height / bili;
    }
}

//office控制上一页
function getPre(){
    	 var nowpage = 0;
		$.ajax({
			type: "GET",
			timeout: 80000,
			url: "servlet/ChangePPTServlet?mdo=pre&randID=" + Math.random(),
			success: function(msg){
				if (msg != "") {
					//接收数据
					nowpage = msg;
					//改变图片的src
					var src = $("#pptShower").attr("src");
					var a = src.split(/\d*.png/);
					var nowPage = Number(nowpage);
					$("#desppt").html("第" + (nowPage + 1) + "页");
					$("#pptShower").attr("src",a[0] + nowPage + ".png");
				}
				
			}
		});    //	var xmlhttp;
    //	if (window.XMLHttpRequest)
    //	 {// code for IE7+, Firefox, Chrome, Opera, Safari
    //	 	xmlhttp=new XMLHttpRequest();
    //	}
    //		else
    //		  {// code for IE6, IE5
    //		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    //		  }
    //		xmlhttp.onreadystatechange=function()
    //		  {
    //		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    //		    {
    //		    //改变图片的src
    //		    var src = document.getElementById("stage").src;
    //		    var a = src.split(/\d*.png/);
    //		    var nowPage = Number(xmlhttp.responseText);
    //		    document.getElementById("page").innerHTML="当前第"+(nowPage+1)+"页";
    //		    document.getElementById("stage").src=a[0]+nowPage+".png";
    //		    }
    //		  }
    //		xmlhttp.open("GET","servlet/ChangePPTServlet?mdo=pre&randID="+Math.random(),true);
    //		xmlhttp.send();1
}

//office控制下一页
function getNext(){
	    var nowpage = 0;
		$.ajax({
			type: "GET",
			timeout: 80000,
			url: "servlet/ChangePPTServlet?mdo=next&randID=" + Math.random(),
			success: function(msg){
				if (msg != "") {
					//接收数据
					nowpage = msg;
					//改变图片的src
					var src = $("#pptShower").attr("src");
					var a = src.split(/\d*.png/);
					var nowPage = Number(nowpage);
						$("#desppt").html("第" + (nowPage + 1) + "页");
					$("#pptShower").attr("src",a[0] + nowPage + ".png");
				}
				
			}
		});

    //	var xmlhttp;
    //	if (window.XMLHttpRequest)
    //  	{// code for IE7+, Firefox, Chrome, Opera, Safari
    // 	 xmlhttp=new XMLHttpRequest();
    //	  }
    //	else
    //	  {// code for IE6, IE5
    //	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    //	  }
    //	xmlhttp.onreadystatechange=function()
    //	  {
    //	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    //	    {
    //	     //改变图片的src
    //		    var src = document.getElementById("stage").src;
    //		    var a = src.split(/\d*.png/);
    //		     var nowPage = Number(xmlhttp.responseText);
    //		    document.getElementById("page").innerHTML="当前第"+(nowPage+1)+"页";
    //		    document.getElementById("stage").src=a[0]+nowPage+".png";
    //	    }
    //	  }
    //	xmlhttp.open("GET","servlet/ChangePPTServlet?mdo=next&randID="+Math.random(),true);
    //	xmlhttp.send();
}
//得到当前页,长轮询技术
function getNow(){
	var src = $("#pptShower").attr("src");
	var b = src.split(/\d*\//);
			 var c = b[2].split(".png")
			 var nowpage = c[0];
				$.ajax({
				type: "GET",
				timeout: 80000,
				url: "servlet/ChangePPTServlet?mdo=now&nowpage="+nowpage+"&randID="+Math.random(),
				
				success: function(msg){
					if (msg != "") {
						//接收数据
						nowpage = msg;
						//改变图片的src
						var a = src.split(/\d*.png/);
						var nowPage = Number(nowpage);
						$("#pptShower").attr("src",a[0] + nowPage + ".png");
						getNow();
					}				
				},
	   			error:function(XMLHttpRequest,textStatus,errorThrown){  
	      			if(textStatus=="timeout"){
	      				//超时重启 
	      					getNow();
	      				}  
	    			}  
	
		});
}
//		var xmlhttp;
//		if (window.XMLHttpRequest)
//	  	{// code for IE7+, Firefox, Chrome, Opera, Safari
//	 	 xmlhttp=new XMLHttpRequest();
//		  }
//		else
//		  {// code for IE6, IE5
//		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//		  }
//		xmlhttp.onreadystatechange=function()
//		  {
//		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
//		    {
//		    //改变图片的src
//		    var src = document.getElementById("stage").src;
//		    var a = src.split(/\d*.png/);
//		    var nowPage = xmlhttp.responseText;
//		    document.getElementById("stage").src=a[0]+nowPage+".png";
//		    }
//		  }
//		xmlhttp.open("GET","servlet/ChangePPTServlet?mdo=now&randID="+Math.random(),true);
//		xmlhttp.send();
//控制div下移，即上一页
function movePre(){
	var a = $("#stageContain").css("top");
		var c = $(window).height();
	a =parseFloat(a.replace("px",""));
	if(a<0){
		$("#stageContain").animate({top:'+='+c/2+'px'});
		var send = a+c/2;
		$.ajax({
			type: "GET",
			timeout: 80000,
			url: "servlet/ChangePPTServlet?mdo=setNowPX&nowPX="+send+"&randID=" + Math.random()
		});
	}
}
//控制div上移，即下一页
function moveNext(){
	var a = $("#stageContain").css("height");
	var b = $("#stageContain").css("top");
	var c = $(window).height();
	a = a.replace("px","");
	b = b.replace("px","");
	b = -b;
	if(b<(a-c)){
		$("#stageContain").animate({top:'-='+c/2+'px'});
		var send = b+c/2;
		$.ajax({
			type: "GET",
			timeout: 80000,
			url: "servlet/ChangePPTServlet?mdo=setNowPX&nowPX="+(-send)+"&randID=" + Math.random()
		});
	}
	
}
function moveNow(){
				var nowPX = $("#stageContain").css("top");
				$.ajax({
				type: "GET",
				timeout: 80000,
				url: "servlet/ChangePPTServlet?mdo=nowPX&nowPX="+nowPX+"&randID="+Math.random(),
				success: function(msg){
					if (msg != "") {
						//接收数据
						nowPX = msg;
						//翻页
						$("#stageContain").animate({top:nowPX+"px"});
						setTimeout("moveNow()", 500);
						
					}				
				},
	   			error:function(XMLHttpRequest,textStatus,errorThrown){  
	      			if(textStatus=="timeout"){
	      				//超时重启 
	      					moveNow();
	      				}  
	    			}  
	
		});
}
