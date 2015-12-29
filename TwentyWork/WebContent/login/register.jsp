<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TwentyWork|register</title>
<link rel="stylesheet" href="../css/login/register.css">


</head>
<body>
	<!-- multistep form -->
	<form id="msform" action="<c:url value="/login/login.c" />" method="post">
	<!-- progressbar -->
	<ul id="progressbar">
		<li class="active">Account Setup</li>
		<li>Personal Profiles</li>
		<li>Team Details</li>
	</ul>
	<fieldset>
		<h2 class="fs-title">Create your account</h2>
		<h3 class="fs-subtitle">This is step 1</h3>
		<input type="text" name="email" placeholder="Email" blur="email1" />
		<input type="password" name="pass" placeholder="Password" /> <input
			type="password" name="cpass" placeholder="Confirm Password" /> <input
			type="button" name="next" class="next action-button" value="Next" />
		<h3>
			<span class="error">${errors.action}</span>
		</h3>
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Personal Details</h2>
		<h3 class="fs-subtitle">We will never sell it</h3>
		<input type="text" name="fname" placeholder="Full Name" /> <input
			type="text" name="cellPhone" placeholder="CellPhone" /> <input
			type="text" name="birth" placeholder="birth" /> <input type="button"
			name="previous" class="previous action-button" value="Previous" /> <input
			type="button" name="next" class="next action-button" value="Next" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Team Details</h2>
		<h3 class="fs-subtitle">welcome !!</h3>
		<input type="text" name="teamName" placeholder="Team Name" />
		<textarea name="about" placeholder="about"></textarea>
		<input type="button" name="previous" class="previous action-button"
			value="Previous" /> <input type="submit" name="registersubmit"
			class="submit action-button" value="Submit" />
	</fieldset>
	</form>
	<!-- jQuery -->
	<script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js"
		type="text/javascript"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- jQuery easing plugin -->
	<script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js"
		type="text/javascript"></script>
	<script src="../js/login/register.js"></script>
</body>
</html>