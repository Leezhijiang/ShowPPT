<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="sun.misc.Cache"%>
<%@page import="top.jjust.cache.CacheManager"%>
<%@page import="top.jjust.changer.PPTShower2007"%>
<%@page import="top.jjust.changer.PPTShower2003older"%>
<%@page import="top.jjust.changer.DocShower"%>
<%@page import="top.jjust.changer.DocxShower"%>
<%@page import="top.jjust.changer.XlsShower"%>
<%@page import="top.jjust.bean.RoomBean"%>
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
		<base href="<%=basePath%>">

		<title>ShowOffice</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<!-- Bootstrap core CSS -->
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

		<!-- Font Awesome CSS -->
		<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

		<!-- Plugins -->
		<link href="css/animations.css" rel="stylesheet">

		<!-- Worthy core CSS file -->
		<link href="css/style.css" rel="stylesheet">
		<link href="css/style2.css" rel="stylesheet">
		<!-- Custom css -->
		<script type="text/javascript" src="js/jquery-1.6.1.js" charset="gbk"></script>
		<script type="text/javascript" src="js/Control.js" charset="gbk"></script>
		<script type="text/javascript" src="js/createRoomInAdmin.js"
			charset="gbk"></script>


		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<%
		//获取参数 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> fileItems = upload.parseRequest(request);
		String roomID = "";
		String roomPD = "";
		String mPath = "";
		RoomBean room = null;
		if (request.getParameter("room_id") != null) {
			roomID = request.getParameter("room_id");
			roomPD = request.getParameter("room_pd");
			System.out.println("request");
		}
		for (FileItem item : fileItems) {
			if (item.isFormField()) {
				if (roomID == "") {
					if ("room_id".equals(item.getFieldName())) {
						roomID = item.getString();
						System.out.println("data");
						if (roomID.contains("Room ID = ")) {
							roomID = roomID.replace("Room ID = ", "");
							roomPD = roomPD.replace("Password = ", "");
						}
					}
				}
				if (roomPD == "") {
					if ("room_pd".equals(item.getFieldName())) {
						roomPD = item.getString();
						System.out.println("data");
						if (roomPD.contains("Password = ")) {
							System.out.println("data-change");
							roomPD = roomPD.replace("Password = ", "");
						}
					}
				}
			}

			else {
				//当前是一个文件
				//防止入侵
				if (roomID == "" && roomID == null
						&& CacheManager.checkLogin(roomID, roomPD)) {

				} else {
					//处理文件以及生成相应bean
					room = (RoomBean) CacheManager.getBean(roomID);
					if (room != null) {
						String name = item.getName();
						if (name.endsWith("ppt")) {
							room.setNowPPT(0);
							room.setOfficeType(StaticVariable.SHOWPPT);
							PPTShower2003older.doPPTtoImage(item
									.getInputStream(), roomID);
						}
						if (name.endsWith("pptx")) {
							room.setNowPPT(0);
							room.setOfficeType(StaticVariable.SHOWPPT);
							PPTShower2007.doPPTtoImage2007(item
									.getInputStream(), roomID);

						}
						if (name.endsWith("doc")) {
							mPath = "/Rooms/" + roomID + "/my.html";
							room.setOfficeType(StaticVariable.SHOWWORD);
							DocShower.convert2Html(item.getInputStream(),
									roomID);
						}
						if (name.endsWith("docx")) {
							mPath = "/Rooms/" + roomID + "/my.html";
							room.setOfficeType(StaticVariable.SHOWWORD);
							DocxShower.convert2Html(item.getInputStream(),
									roomID);
						}
						if (name.endsWith("xls") || name.endsWith("xlsx")) {
							mPath = "/Rooms/" + roomID + "/my.html";
							room.setOfficeType(StaticVariable.SHOWEXCEL);
							XlsShower.convert2Html(item.getInputStream(),
									roomID);
						}
					}
				}
			}
		}
		if (room != null) {
			//显示
			switch (room.getOfficeType()) {
			case StaticVariable.SHOWPPT:
	%><img id="pptShower"
		src="Rooms/<%=roomID%>/<%=(room).getNowPPT()%>.png" alt="alttext"
		style="height: 100%" />
	<script type="text/javascript" src="js/ShowPPT.js" charset="gbk"></script>
	<%
		break;
			case StaticVariable.SHOWWORD:
	%><div id="docShower"><jsp:include page="<%=mPath%>" /></div>
	<script type="text/javascript" src="js/ShowWord.js" charset="gbk"></script>
	<%
		break;
			case StaticVariable.SHOWEXCEL:
	%><div id="xlsShower"><jsp:include page="<%=mPath%>" /></div>
	<script type="text/javascript" src="js/ShowExcel.js" charset="gbk"></script>
	<%
		break;
			}
		}
		//设置房间主人coockie标记
		Cookie c = new Cookie("roomID", roomID + "");
		response.addCookie(c);
	%>
	<body>
		<header class="header fixed clearfix navbar navbar-fixed-top">
		<div class="container">
			<div class="row">
				<div class="col-md-4">

					<!-- header-left start -->
					<!-- ================ -->
					<div class="header-left clearfix">

						<!-- logo -->
						<div class="logo smooth-scroll">
							<a href="homepage.jsp"><img id="logo" src="images/logo.png"
									alt="Worthy"> </a>
						</div>

						<!-- name-and-slogan -->
						<div class="site-name-and-slogan smooth-scroll">
							<div class="site-name">
								<a href="homepage.jsp">ShowOffice</a>
							</div>
							<div class="site-slogan">
								Powered by 江苏科技大学-
								<a href="homepage.jsp">溯源</a>
							</div>
						</div>

					</div>
					<!-- header-left end -->

				</div>
				<div style="height: 80px" class="col-md-4">
					<div class="bewrite">
						<div style="font-size: 18px;" id="desroom">
							房间号:<%=roomID%>&nbsp&nbsp&nbsp密码:<%=roomPD%></div>
						<p />
						<div id="desppt">
							第1页
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<!-- header-right start -->
					<!-- ================ -->
					<div class="header-right clearfix">

						<!-- main-navigation start -->
						<!-- ================ -->
						<div class="main-navigation animated">

							<!-- navbar start -->
							<!-- ================ -->
							<nav class="navbar navbar-default" role="navigation">
							<div class="container-fluid">



								<!-- Collect the nav links, forms, and other content for toggling -->
								<div class="collapse navbar-collapse scrollspy smooth-scroll"
									id="navbar-collapse-1">
									<ul class="nav navbar-nav navbar-right">
										<li>
											<a style="cursor: pointer" onclick="createRoomInAdmin()"
												id="new">新文件</a>
										</li>
										<li>
											<a style="cursor: pointer" id="pre">上一页</a>
										</li>
										<li>
											<a style="cursor: pointer" id="next">下一页</a>
										</li>
									</ul>
								</div>
							</div>
							</nav>
							<!-- navbar end -->

						</div>
						<!-- main-navigation end -->

					</div>
					<!-- header-right end -->

				</div>
			</div>
		</div>
		</header>
		<!-- header end -->
		<!-- ================ -->
		<div id="banner" class="banner">
			<div id="stageContain" align="center" style="top: 11%;"
				class="banner-caption">

				<!-- 内容 -->

			</div>
			<!-- 创建房间页面 -->
			<div id="createUI" class="login">
				<h2>
					Show Office
				</h2>
				<div class="login-top">
					<h1>
						CREATE FORM
					</h1>
					<div style="text-align: center">
						<form name="creatForm" id="creatForm" action="adminpage.jsp"
							method="post" enctype="multipart/form-data">
							<!--  >-->
							<input name="room_id" value="Room ID = <%=roomID%>"
								style="text-align: center" id="roomID" type="text"
								readonly="true" />
							<input name="room_pd" value="Password = <%=roomPD%>"
								style="text-align: center" id="roomPD" type="text"
								readonly="true" />
							<div class="fileUpload btn btn-primary">
								<span>Upload</span>
								<input name="file1" id="uploadBtn" type="file" class="upload" />
							</div>
							<input class="file-show" id="uploadFile"
								placeholder="Choose File" disabled="disabled" />
							<div class="forgot">
								<input type="submit" value="Create">
							</div>

						</form>
					</div>
				</div>
				<div class="login-bottom">
					<h3>
						<a id="changText" style="cursor: pointer" href="homepage.jsp">Join
							Room</a>&nbsp Click Here
					</h3>
				</div>
			</div>
		</div>
		<!-- banner end -->


	</body>
	<script type="text/javascript" src="js/getFileName.js" charset="gbk"></script>
	<script type="text/javascript" src="js/changeUI.js" charset="gbk"></script>
</html>
