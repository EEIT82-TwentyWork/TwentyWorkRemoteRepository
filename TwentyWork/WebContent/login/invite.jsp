<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel ="stylesheet" href="../css/login/invite.css">
<title>invite member</title>
</head>
<body>
<!-- 		------------------------------------------------------------ -->
				<div class="logocontainer">
					<div class="logo">
						<span class="left"><</span>TwentyWork<span class="right">/></span>
						<div class="text">
						<span class="word">Web</span><span class="web">Developer</span>
						</div>
					</div>
				</div>
<!-- 		------------------------------------------------------------ -->	
	<div id="inviteForm">
		<form action="<c:url value="/login/invite.controller"/>" method="post">
			<input type="text" placeholder="Email" id="EmailAdd" ><br>
			<input type="button" id="JoinList" value="加入邀情名單"  class="action-button">
			
			<!-- 		<button type="button" id="JoinList">加入邀情名單</button> -->
			<button type="submit" class="action-button" >發送邀請信</button>
			<div>${emtpy}</div>
			<div id="InviterList">
				<ol id="EmaiList">
				
				</ol>
				<input type="text" id="count" name="count" style="display:none">
				<!-- 	none			block -->
	<!-- 			<div id="count" style="display:block">111111</div> -->
			</div>
	
		</form>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="../js/login/invite.js"></script>
</body>
</html>