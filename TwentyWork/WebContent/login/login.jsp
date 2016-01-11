<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
groupID:200,userID:100~105<br>
groupID:201,userID:106~108<br>
groupID:202,userID:100~108<br>
<br><br>
	<form action="<c:url value="/login/login.controller" />" method="post">
<table>
	<tr>
		<td>groupID : </td>
		<td><input type="text" name="groupID" value="${groupID}"></td>
		<td>${errors.groupID}</td>
	</tr>
	<tr>
		<td>userID : </td>
		<td><input type="text" name="userID" value="${userID}"></td>
		<td>${errors.userID}</td>
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text" name="password" value="${password}"></td>
		<td>${errors.password}</td>
	</tr>
	<tr>
        <td></td>
        <td><input type="checkbox" name="rememberMe" 
               <c:if test='${sessionScope.rememberMe==true}'>
                  checked='checked'
               </c:if> 
             value="true"> 記住密碼
        </td>
             
    </tr>     
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" value="Login"></td>
	</tr>
</table>
</form>
</body>
</html>