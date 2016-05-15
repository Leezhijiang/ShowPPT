<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="top.jiang.utils.FileUtils"%>
<%@page import="top.jjust.common.StaticVariable"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<base href="<%=basePath%>">

		<title>Office演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body class="bodyStyle">
		<div class="divStyle">
		<h3 class="tips">作为Office播放者</h3>
			<form action="./room.jsp">
				<input class="btnstyle" type="submit" value="开启新房间">
			</form>
			<hr />
		<h3 class="tips">作为Office接收者</h3>
			<form action="./getRoomShow.jsp" method="post" onsubmit="true">
					<font class="textStyle">房间号&nbsp&nbsp&nbsp<font>
				<input class="inputStyle" id="roomID" name="roomID" type="text">
				<p />
					<font class="textStyle">房间密码<font>
					<input class="inputStyle" id="pd" name="pd" type="text">
				<p />
					<input class="btnstyle" type="submit" value="加入房间">
			</form>
			<p/><font class="textStyle">现在可以接受XLS,XLSX,DOC,DOCX,PPT,PPTX文件<font>
		</div>
	</body>
</html>
