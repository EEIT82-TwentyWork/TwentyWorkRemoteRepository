<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zn-Hantn-TW">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main/Main.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board/favList.css">

<style>
.mainPageContext{
	float:left;
 	background-color:white; 
 	width: 1000px;
	height:620px;
	margin-top:20px;
	box-shadow: 0 5px 15px 1px rgba(0, 0, 0, 0.6), 0 0 200px 1px
 rgba(255, 255, 255, 0.5);
 	overflow-y:auto;
}


</style>
<title>main</title>
</head>
	<body onload="initializeCanvas()">
		<div class="container-fluid">
			<section>
			<div id="page-top">
<!-- 				<a href="/TwentyWork/main/workHome/main.jsp"><img id="logoMainID" src="../../images/index/Logo-main.png" border="0" title="TwentyWork HomePage"></a> -->
				<div class="logocontainer">
					<div class="logo">
						<a href="/TwentyWork/main/workHome/main.jsp">
							<span class="left">< </span>TwentyWork<span class="right"> /></span>
						</a>
						<span id="top-userinfo">
								<span>Hi！</span>
								<span id="top-name" ><%=session.getAttribute("userName") %></span>
								 | 
								<a href="#">Setting	</a>
								 | 
								<a id="logoutid" href="<%=request.getContextPath()%>">Logout</a>
						</span>
					</div>
					
				</div>
				
<%-- 				<h3>Welcome <%=session.getAttribute("userName") %></h3> --%>
				
				
			</div>
<!-- layout----E1 begin---------------------------------------------------->				
				<div id="page-left">
					<div id="accordian">
						<ul>

							<li>
								<h3><a href = "<%=request.getContextPath() %>/main/workHome/main.jsp">首頁</a></h3>
							</li>

							<li>
								<h3><span class="icon-dashboard"></span>Dashboard</h3>
								<ul>
									<li><a href="#">Reports</a></li>
									<li><a href="#">Reports</a></li>
									<li><a href="#">Search</a></li>
									<li><a href="#">Graphs</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<!-- we will keep this LI open by default -->
							<li class="active">
								<h3><span class="icon-tasks"></span>Tasks</h3>
								<ul></ul>
							</li>
							<li> 
						       	 <h3 id="calendarh3id" ><span class="icon-calendar"></span>Calendar</h3> 
						       	 <ul id="calendarulid"> 
						         <li><a href="../calendar/cal_opt.jsp">個人行事曆</a></li> 
					        </ul> 
					       </li>
							<li>
								<h3><span class="icon-heart"></span>Favourites</h3>
								<ul>
									<li><a href="#">Global favs</a></li>
									<li><a href="#">My favs</a></li>
									<li><a href="#">Team favs</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<li>
								<h3><span class="icon-chat"></span>Chat List</h3>
								<ul id="chatName">
								</ul>
							</li>
							<li id="myFavLeftList">
								<h3><a href = "<%=request.getContextPath() %>/Board">討論版</a></h3>
								<ul>
								</ul>
							</li>
							<li>
								<h3><a href = "<%=request.getContextPath() %>/ShareFile">檔案分享</a></h3>
							</li>
							<li>
								<h3><span class="icon-close"></span>close</h3>
							</li>
						</ul>
					</div>
				</div>
<!-- layout----E1 end---------------------------------------------------->
<div id="page-left">
<div class = 'mainPageContext'>	