<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TwentyWork|registerPersonal</title>
<link rel="stylesheet" href="../css/login/register.css">
<link rel="stylesheet" href="../css/logo/logo.css">

<title>Insert title here</title>
</head>
<body>
	<div class="logocontainer">
		<div class="logo">
			<span class="left"><</span>TwentyWork<span class="right">/></span>
			<div class="text">
				<span class="word">Web</span><span class="web">Developer</span>
			</div>
		</div>
	</div>
	<form id="msform" action="<c:url value="/login/Personal" />"method="post">
		<fieldset>
			<h1 class="fs-title"><%=request.getParameter("teamName")%></h1>
			<h2 class="fs-title">Personal Details</h2>
			<h3 class="fs-subtitle">welcome !!</h3>
			<input type="text" name="teamName" value="<%=request.getParameter("teamName") %>" readonly="readonly" />
			<input type="text" name="email" value="<%=request.getParameter("email") %>" readonly="readonly" />
			<input type="text" name="password" placeholder="Password"/>
			<input type="text" name="fullName" placeholder="Full Name" />
			<input type="text" name="cellPhone" placeholder="CellPhone" />
			<input type="text" name="birth" placeholder="Birth" />
			<input type="submit" name="registersubmit" class="submit action-button" value="Submit" />
		</fieldset>

	</form>

</body>
</html>