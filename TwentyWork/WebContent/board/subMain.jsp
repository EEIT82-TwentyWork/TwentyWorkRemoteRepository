<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap/bootstrap.min.css">
<title>Insert title here</title>
<style>
.padding {
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 5px;
}
</style>
</head>
<body>
<div id = "iconNav" class = "padding">
	<div><a href="#"><img src = "<%= request.getContextPath() %>/images/board/add182.png" />Add Comment</a></div>
</div>
<br><br>
<div id="boardList" class="padding">
<h4>${boardBean.boardTitle}</h4><br>
	<table class = "table">
		<thead>
			<tr>
				<td>內文</td>
				<td>留言時間</td>
				<td>發言人</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${boardBean.boardText}</td>
				<td>${fn:substring(boardBean.boardTime ,0,16) }</td>
				<td>${boardBean.users.userName }</td>
			</tr>
			<c:if test="${! empty subList}">
				<c:forEach var="subList" items="${subList}">
					<tr>
						<td>${subList.subText }</td>
						<td>${fn:substring(subList.subTime ,0,16) }</td>
						<td>${subList.users.userName }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>


</div>
</body>
</html>