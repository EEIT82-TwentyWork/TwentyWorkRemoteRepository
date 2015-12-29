<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){   
	var i =1;
	$("#JoinList").click(function(){
		console.log(i);	
		$("#EmaiList").append("<li>"+"<input type ='text' name='inviteEmail" + i + "' value='" + $("#EmailAdd").val() + "'>"+"</li>");
		$("#EmailAdd").val('');
		$("#count").val(i);
		i++;
	})
})
</script>
</head>
<body>
	<h1>invite team member</h1>

	<form action="<c:url value="/login/invite.controller"/>" method="get">
		<input type="text" placeholder="Email" id="EmailAdd" value=""> 
		<input type="button" id="JoinList" value="加入邀情名單">

		<!-- 		<button type="button" id="JoinList">加入邀情名單</button> -->


		<button type="submit">發送邀請信</button>
		<div>${emtpy}</div>
		<div id="InviterList">
			<ul id="EmaiList">
			
			</ul>
			<input type="text" id="count" name="count" style="display:block">
<!-- 			<div id="count" style="display:block">111111</div> -->
		</div>

	</form>


</body>
</html>