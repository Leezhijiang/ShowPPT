<%@ page language="java" import="java.util.*" pageEncoding="utf-8"  %>
<%@page import="top.jjust.changer.*"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="top.jjust.common.StaticVariable"%>
<%@page import="top.jjust.bean.RoomBean"%>
<%@page import="top.jjust.cache.CacheManager"%>
<%@page import="top.jiang.utils.CookieUtils"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Office控制</title>
    <link href="css/index.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.6.1.js"></script>
	<script type="text/javascript" src="js/Control.js"  charset="utf-8" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  
  </head>
  
  <body class="bodyStyle">
  <div class="divStyle2">
  <script type="text/javascript">
  
</script>
    <%if(null == request.getHeader("Referer") || request.getHeader("Referer").indexOf("ShowPPT/room.jsp") < 0){
    }else{
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	List<FileItem> fileItems = upload.parseRequest(request);
    	Cookie[] cookies = request.getCookies();
    	String roomID = CookieUtils.getRoomID(cookies);
    	for(FileItem item:fileItems){
    		if(item.isFormField()){}
    		else{
    		//当前是一个文件
    		if(roomID.equals("")&&roomID==null){
    			%>
    				非法访问
    			<%
    		}else{
    			RoomBean room = (RoomBean)CacheManager.getBean(roomID);
    			String name = item.getName();
    			String mPath = StaticVariable.PPTPicRootPath+roomID+"/"+"my.html";
    			//ppt系列
    			if(name.endsWith("ppt")){
    			room.setNowPPT(0);
    			room.setOfficeType(StaticVariable.SHOWPPT);
    			PPTShower2003older.doPPTtoImage(item.getInputStream(),roomID);
    			
    			%>
    			<font class="textStyle">房间号:<%=room.getRoomID() %>,&nbsp&nbsp密码：<%=room.getPD() %>&nbsp&nbsp&nbsp&nbsp</font><font class="textStyle" id="page">当前第1页</font>&nbsp&nbsp<input class="btnstyle" type="button" onclick=getPre() value="上一页"/>&nbsp&nbsp&nbsp&nbsp&nbsp<input class="btnstyle" type="button" onclick=getNext() value="下一页"/><p/>
    				<img id="stage" src="Rooms/<%=roomID%>/<%=(room).getNowPPT()%>.png" alt="alttext"  onload="resizeImage(this)"/>
    			<%
    			}
    			else if(name.endsWith("pptx")){
    			room.setNowPPT(0);
    			room.setOfficeType(StaticVariable.SHOWPPT);
    			PPTShower2007.doPPTtoImage2007(item.getInputStream(),roomID);
    			%>
    			<font class="textStyle">房间号:<%=room.getRoomID() %>,&nbsp&nbsp密码：<%=room.getPD() %>&nbsp&nbsp&nbsp&nbsp</font><font class="textStyle" id="page">当前第1页</font>&nbsp&nbsp<input class="btnstyle" type="button" onclick=getPre() value="上一页"/>&nbsp&nbsp&nbsp&nbsp&nbsp<input class="btnstyle" type="button" onclick=getNext() value="下一页"/><p/>
    				<img id="stage" src="Rooms/<%=roomID%>/<%=(room).getNowPPT()%>.png" alt="alttext"  onload="resizeImage(this)"/>
    			<%
    			}
    			//word系列
    			else if(name.endsWith("doc")){
    			room.setOfficeType(StaticVariable.SHOWWORD);
    				%>
    				<font class="textStyle">房间号:<%=room.getRoomID() %>,&nbsp&nbsp密码：<%=room.getPD() %>
    				<input style="position:fixed;margin-top: 10%;" class="btnstyle" type="button" onclick=movePre() value="上一页"/>&nbsp&nbsp&nbsp&nbsp&nbsp<input style="position:fixed;margin-top: 10%;" class="btnstyle" type="button" onclick=moveNext() value="下一页"/>
    				<%=DocShower.convert2Html(item.getInputStream(),roomID) %>
    				<%
    				
    			}
    			else if(name.endsWith("docx")){
    			room.setOfficeType(StaticVariable.SHOWWORD);
    				
    				%>
    				<font class="textStyle">房间号:<%=room.getRoomID() %>,&nbsp&nbsp密码：<%=room.getPD() %>
    				<input style="position:fixed;margin-top: 10%;"  class="btnstyle" type="button" onclick=movePre() value="上一页"/>&nbsp&nbsp&nbsp&nbsp&nbsp<input style="position:fixed;margin-top: 10%;"  class="btnstyle" type="button" onclick=moveNext() value="下一页"/>
    				<div id="stageContain" style="top:0px;position:absolute;"><%=DocxShower.convert2Html(item.getInputStream(),roomID) %></div>
    				<%
    			}
    			//excel系列
    			else if(name.endsWith("xls")||name.endsWith("xlsx")){
    			room.setOfficeType(StaticVariable.SHOWEXCEL);
    				%>
    				<font class="textStyle">房间号:<%=room.getRoomID() %>,&nbsp&nbsp密码：<%=room.getPD() %>
    				<%=XlsShower.convert2Html(item.getInputStream(),roomID) %>
    				<%
    			}
    		}
    		}
    	}   
    } %>
    </div>
    	<input  class="bottombtnstyle" type="button" onclick=movePre() value="上一页"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
    	<input   class="bottombtnstyle" type="button" onclick=moveNext() value="下一页"/>
  </body>

</html>
