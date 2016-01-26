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
.padding,.commentArea {
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 5px;
}
.UserInformation{
	float:left;
}
.userimage{
/* 	width:'40px'; */
/* 	height:'40px'; */
	padding:14px
}
.eachPost{
	border:1px black;
	padding:5px;
}
.subTable{
	 width:400px;
}
.subTableTr{
	 border-bottom: 1px solid gray;
}
#mainHr{
	margin-top:0px;
	margin-bottom:0px;
}
#boardList{
	padding-bottom:5px;
}
</style>
</head>
<body>
<jsp:include page="../main/workHome/head.jsp" />
<!---------------- main page code ---------------------------------->

<div id="boardList" class="padding">
<h4>${boardBean.boardTitle}</h4>
<hr id="mainHr">
<c:set var="index" value='1' />
<table class='subTable'>
<c:forEach var="subList" items="${subList }">
<tr class='subTableTr'><td>
<div class="eachPost">
	<div class="postUser">
		<div class = "UserInformation">
			<img class = 'userimage' src = "<%= request.getContextPath() %>/images/board/silhouette78.png" />
		</div>
 		<div >
  			<span>#${index}</span><br>
  			<c:set var="index" value='${index+1}' />
  			<span>${subList.users.userName }</span><br>
  			<span>${fn:substring(subList.subTime ,0,16) }</span>
  		</div>
  	</div>
  	<hr  id="mainHr">
  	<div class = "subTextContext">
  		${subList.subText }
  	</div>
<!--   	------------------------------------------------------------------------------------------- -->
  </div>
  </td></tr>
 </c:forEach>	
 </table>	
</div>
<div class='commentArea'>
<form action="<%= request.getContextPath() %>/BoardServlet/subInsert">
	<textarea rows="8" cols="50" name="boardText" placeholder="輸入回覆內容"></textarea>
	<input type = "submit" value="送出" />
	<input type ="hidden" name="boardID" value="${boardBean.boardId }"  display='none'/>
</form> 
</div>

<!---------------- main page code end---------------------------------->
<jsp:include page="../main/workHome/foot.jsp" />
<script >
$(function(){
$('.subTableTr:last').removeClass('subTableTr')
})// end of $(function(){
</script>
</body>
</html>