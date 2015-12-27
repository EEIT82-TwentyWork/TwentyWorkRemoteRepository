<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zn-Hantn-TW">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="../../css/main/Main.css">

<title>main</title>
</head>
	<body>	
		<div class="container-fluid">
			<section>
<!-- layout----E1 begin---------------------------------------------------->				
				<div id="page-top">
					<h3>Welcome ${users.userName}</h3>
				</div>
				<div id="page-left">
					<div id="accordian">
						<ul>
							<li>
								<h3><span class="icon-dashboard"></span>Dashboard</h3>
								<ul>
									<li><a href="#">Reports</a></li>
									<li><a href="#">Search</a></li>
									<li><a href="#">Graphs</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<!-- we will keep this LI open by default -->
							<li class="active">
								<h3><span class="icon-tasks"></span>Tasks</h3>
								<ul>
									<li><a href="#">Today's tasks</a></li>
									<li><a href="#">Urgent</a></li>
									<li><a href="#">Overdues</a></li>
									<li><a href="#">Recurring</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<li>
								<h3><span class="icon-calendar"></span>Calendar</h3>
								<ul>
									<li><a href="#">Current Month</a></li>
									<li><a href="#">Current Week</a></li>
									<li><a href="#">Previous Month</a></li>
									<li><a href="#">Previous Week</a></li>
									<li><a href="#">Next Month</a></li>
									<li><a href="#">Next Week</a></li>
									<li><a href="#">Team Calendar</a></li>
									<li><a href="#">Private Calendar</a></li>
									<li><a href="#">Settings</a></li>
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
								<ul>
									<li><a href="#">吳承恩</a></li>
									<li><a href="#">黃莉婷</a></li>
									<li><a href="#">陳凱尉</a></li>
									<li><a href="#">Amy</a></li>
									<li><a href="#">cat</a></li>
									<li><a href="#">Azumi</a></li>
									<li><a href="#">BOSS</a></li>
									<li><a href="#">123456789012345678901234567890</a></li>
									<li><a href="#">一二三四五六七八九十一二三四五六七八</a></li>
								</ul>
							</li>
							<li>
								<h3><span class="icon-close"></span>close</h3>
							</li>
						</ul>
					</div>
				</div>
<!-- layout----E1 end---------------------------------------------------->								
				<div id="page-center">
					<div id="chat-top"></div>
					<div id="chat-context">
						<div id="status"></div>
						<ul id ="chat-messages"></ul>
					</div>
					<div id="chat-box">
						<form id="message-form" action ="#" method="post">
							<input type="textarea" id="chat-text" placeholder="strat chat" required/>
							<button type="submit" >Send Message</button>
							<input type ="button" id ="close" value="close">
						</form>
					</div>
				</div>
				<div id="page-right">right</div>
				<div id="page-bottom">bottom</div>
			</section>
		</div>	
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="../../js/login/Main.js"></script>
<script src="../../js/main/Websocket.js"></script>
	</body>

</html>
