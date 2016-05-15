<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="top.jjust.cache.*"%>
<%@page import="top.jjust.common.StaticVariable"%>
<%@page import="top.jjust.container.Room"%>
<%@page import="top.jjust.bean.RoomBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Office</title>
    <link href="css/index.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.6.1.js"></script>
	<script type="text/javascript" src="js/Control.js"  charset="utf-8" ></script>
  </head>
  
  <body class="bodyStyle">
  <div class="divStyle2">
    <%
    	String roomID = request.getParameter("roomID"); 
    	RoomBean room = (RoomBean)CacheManager.getBean(roomID);
    	String pd = request.getParameter("pd"); 
    	if(CacheManager.checkLogin(roomID,pd)){
    	//设置房间访客coockie标记
		Cookie c = new Cookie("visitorID",roomID+"");
		response.addCookie(c);
		String mPath = "/Rooms/"+roomID+"/my.html";
		switch(room.getOfficeType()){
		case StaticVariable.SHOWNONE : %>
    	<font class="textStyle">用户尚未进行演示<%=roomID%></font><p/>
    	<%
    	break;
    	case StaticVariable.SHOWPPT :
    	//开启ppt控制
    	%>
    	<font class="textStyle">房间号为<%=roomID %></font><p/>
    	
    	<img id="stage" src="Rooms/<%=roomID%>/<%=(room).getNowPPT()%>.png" alt="alttext" onload="resizeImage(this)"/>
    	<script type="text/javascript">
    	window.onload = getNow();
  	
    	 </script>
    	<%
    	break;
    	case StaticVariable.SHOWWORD :
    	case StaticVariable.SHOWEXCEL :
    	//开启翻页控制
    	%>
    	<div id="stageContain" style="top:0px;position:absolute;"><jsp:include page="<%=mPath%>"  /></div>
    	<script type="text/javascript">
    	window.onload = moveNow();
  	
    	 </script>
    	<%
    	}
    	}else{
    	%>
    	密码不匹配或不存在房间	
    	<%
    	}
    %>
    </div>
  </body>
</html>
