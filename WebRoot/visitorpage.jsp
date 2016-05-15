<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="top.jjust.cache.CacheManager"%>
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
	
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
		String roomID = request.getParameter("ID");
		String roomPD = request.getParameter("PD");
		String mPath = "/Rooms/"+roomID+"/my.html";
		String type ="";
		RoomBean room =null;
		if(roomID!=null&&roomID!=""){
			//验证账户密码
			
			room= (RoomBean)CacheManager.getBean(roomID);
			if(room!=null){
				if(room.checkPD(roomPD)){
				//设置cookie
				//设置房间访客coockie标记
				Cookie c = new Cookie("visitorID",roomID+"");
				response.addCookie(c);
				switch(room.getOfficeType()){
	    			case StaticVariable.SHOWPPT: 
	    				type="PPT演示";
	    				%><img id="pptShower" src="Rooms/<%=roomID%>/<%=(room).getNowPPT()%>.png" alt="alttext" style="height:100%"/><script type="text/javascript" src="js/getPPT.js" charset="gbk"></script><%
	    			break;
	    			case StaticVariable.SHOWWORD: 
	    				type="Word演示";
	    				%><div id="docShower"><jsp:include page="<%=mPath %>"/></div><script type="text/javascript" src="js/getWord.js" charset="gbk"></script><%
	    			break;
	    			case StaticVariable.SHOWEXCEL: 
	    				type="Excel演示";
	    				%><div id="xlsShower"><jsp:include page="<%=mPath %>"/></div><script type="text/javascript" src="js/getExcel.js" charset="gbk"></script><%
	    			break;
				}
			}
		}
		}
		
	 %>
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
							<a><img id="logo" src="images/logo.png"
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
				<div class="col-md-4">
					<div class="bewrite">
						房间:<%=roomID%>
					</div>
					<div style="text-align: center;    font-size: 22px;" id="desppt"><%=type%></div>
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
										<li class="active">
											<a href="homepage.jsp">加入新房间</a>
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
			<div id="stageContain" style="top: 11%;" align="center" class="banner-caption">
			<!-- 内容页 -->
				
			</div>
		</div>
		<!-- banner end -->


	</body>
	<script type="text/javascript" src="js/Control.js" charset="gbk"></script>
	<script type="text/javascript" src="js/Ajax.js" charset="gbk"></script>
	<script type="text/javascript" src="js/changeUI.js" charset="gbk"></script>
</html>
