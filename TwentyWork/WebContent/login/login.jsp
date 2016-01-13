<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/login/loginFage.css">
<link rel="stylesheet" href="http://daneden.github.io/animate.css/animate.min.css">
<link rel="stylesheet" href="../css/logo/logo.css">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,400italic,700italic,700">

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
<div id="loginPageSetting">
	<div class="form animated flipInX">
		<h2>Login To Your Account</h2>
		<form action="<c:url value="/login/login.controller" />" method="post">
			<input type="text" placeholder="Team Name" name="TeanName"/>
			<input placeholder="Username" type="text" name="email"></input> 
			<input placeholder="Password" type="password" name="password"></input>
			
	  			 <div class="login-checkbox">
	  				  <input type="checkbox" id="cb1" name="rememberMe" <c:if test='${sessionScope.rememberMe==true}'>checked='checked'</c:if> value="true">
					      <label for="cb1"></label>
					      <div class="toggle"></div>
	   			 </div>
           <input type="submit" value="Login" id ="loginsubmit" >
		</form>
	</div>
</div>

	<!--  


	<form action="<c:url value="/login/login.controller" />" method="post">
<table>
	<tr>
		<td>TeanName : </td>
		<td><input type="text" name="TeanName" value="${groupID}"></td>
		<td>${errors.groupID}</td>
	</tr>
	<tr>
		<td>email : </td>
		<td><input type="text" name="email" value="${userID}"></td>
		<td>${errors.userID}</td>
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text" name="password" value="${password}"></td>
		<td>${errors.password}</td>
	</tr>
	<tr>
        <td></td>
        <td><input type="checkbox" name="rememberMe" <c:if test='${sessionScope.rememberMe==true}'>checked='checked'</c:if> value="true"> 記住密碼
        </td>
             
    </tr>     
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" value="Login"></td>
	</tr>
</table>
</form>
-->

</body>
</html>