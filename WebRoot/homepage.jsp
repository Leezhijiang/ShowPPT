<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

	<script type="text/javascript" src="js/changeUI.js" charset="gbk"></script>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

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
							<a ><img id="logo" src="images/logo.png"
									alt="Worthy"> </a>
						</div>

						<!-- name-and-slogan -->
						<div class="site-name-and-slogan smooth-scroll">
							<div class="site-name">
								<a href="homepage.jsp">ShowOffice</a>
							</div>
							<div class="site-slogan">
								Powered by 江苏科技大学-<a href="homepage.jsp">溯源</a>
							</div>
						</div>

					</div>
					<!-- header-left end -->

				</div>
				<div class="col-md-8">

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
											<a style="cursor:pointer" onclick="show_get()">演示/接收</a>
										</li>
										<li>
											<a style="cursor:pointer" onclick="about()">关于</a>
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
			<div class="banner-caption">
		
				<div >
					<!--ABout界面 -->
				<div id="aboutText" style="color:#FFF;width:80%;margin-left:10%;margin-top:8%"class="bewrite">OfficeShow是一款可以上传并获取同步展示资料的实用类平台。上传者在移动端或电脑进入OfficeShow页面，创建房间后即可上传文档资料；接收者只需进入房间即可获取与展示者同步的文档资料。目前，此应用支持ppt,pptx,xls,xlsx,doc,docx等格式。<p/>



OfficeShow is a utility class platform can upload and obtain the documents that are been displaying . The uploader can create room where you can upload documents by the mobile terminal or computer in the OfficeShow page.The recipients only need entered the room that is created in which can access the documents of being displaying by the uploader . Currently, this platform supports ppt, pptx, xls, xlsx, doc, docx and other formats.
				</div>
				
				<!--登陆界面 -->
				<div id="loginUI" class="login">
						<h2>
							Show Office
						</h2>
						<div class="login-top">
							<h1>
								LOGIN FORM
							</h1>
							<form name="loginForm" id="loginForm" action="visitorpage.jsp" method="post" onsubmit="checkForm()">
								<input name="ID" id="ID" type="text" value="Room Id" onfocus="this.value = '';"
									>
								<input name="PD" id="PD" type="password" value="password"
									onfocus="this.value = '';"
									>
							
							<div class="forgot">
								<input name="loginBTN" type="submit" value="Login">
							</div>
							</form>
						</div>
						<div class="login-bottom">
							<h3>
								<a id="changeLogin"  style="cursor:pointer" onclick="onchangeUI()">Create new Room</a>&nbsp Click Here
							</h3>
						</div>
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
							<div style="text-align:center">
							<form name="creatForm" id="creatForm" action="adminpage.jsp" method="post" enctype="multipart/form-data"><!--  >--> 
									<input name ="room_id"  style="text-align:center"id="roomID" type="text" readonly="true"/>
									<input  name ="room_pd" style="text-align:center"id="roomPD" type="text"  readonly="true"/>
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
								<a id="changText" style="cursor:pointer" onclick="onchangeUI()">Join Room</a>&nbsp Click Here
							</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- banner end -->


	</body>

		<script type="text/javascript" src="js/Ajax.js" charset="gbk"></script>
	<script type="text/javascript" src="js/Control.js" charset="gbk"></script>
	<script type="text/javascript" src="js/getFileName.js" charset="gbk"></script>
</html>
