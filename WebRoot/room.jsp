<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="top.jjust.container.Room"%>
<%@page import="top.jiang.utils.*"%>
<%@page import="top.jjust.bean.RoomBean"%>
<%@page import="top.jjust.cache.CacheManager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>Office演示房间</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">  
    var ischeck = false;  
    function checkForm(){  
        if(!ischeck){  
        //检查文件是否符合规范
       		var path = document.getElementById("file1").value;
       		if(path.length<=3){
       			alert("请选择文件");
       		}else{
       			
       			var position = (path.length-3);
       			var houzui  = path.substring(position,path.length);
        		if(houzui=="ppt"){
           			ischeck=true;  
           			
       			 }else{
       			 if(houzui=="ptx"){
           			ischeck=true;  
       			 }
       			 else if(houzui=="doc"){
       			 ischeck=true;
       			 }
       			 else if(houzui=="ocx"){
       			 ischeck=true;
       			 }
       			 else if(houzui=="xls"){
       			 ischeck=true;
       			 }
       			 else if(houzui=="lsx"){
       			 ischeck=true;
       			 }
       			 else{
       					 alert("请选择文件或检查是否提交ppt(pptx)doc(docx)xls(xlsx)文件！");  
            			ischeck=false;  
       			 }	
       			 }
       		}
          }
        else{  
            alert("无需重复提交！");  
            ischeck=false;  
        }  
     return ischeck;  
 	}  
</script>
	</head>

	<body class="bodyStyle">
		<div class="divStyle">
			<%
				//防盗链

				if (null == request.getHeader("Referer")
						|| request.getHeader("Referer").indexOf("ShowPPT") < 0) {
			%>
			非法访问
			<%
				} else {
					//获取时间作为随机值,得到房间号
					Date date = new Date();
					int roomID = (int) date.getTime();
					Room room = new Room(roomID + "");

					//随机生成密码
					String pd = NumberUtils.getFixLenthString(6);
			%>
			<h3 class="tipsNoraml">新建房间成功</h3><p/>
			<font class="textStyle">房间号为
			<%=roomID%></font>
				<font class="textStyle">房间对应密码为
				<%=pd%></font>
				<%
					//把房间号和对应密码存入缓存
						RoomBean ur = new RoomBean(roomID + "", pd);
						CacheManager.insert(ur);
						//设置房间主人coockie标记
						Cookie c = new Cookie("roomID", roomID + "");
						response.addCookie(c);
					}
				%>
			
			<form action="showStage.jsp" method="post"
				enctype="multipart/form-data" onsubmit="return checkForm()">
				<font class="textStyle">选择需要上传的文件</font>
				<input class="uploadStyle" type="file" id="file1" name="file1">
				<input class="btnstyle" type="submit" value="上传文件">
			</form>
		</div>
	</body>
</html>


