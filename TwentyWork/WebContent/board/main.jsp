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
.starNotShow{
	display: none;
}

</style>
</head>
<body>
<jsp:include page="../main/workHome/head.jsp" />
<!---------------- main page code ---------------------------------->


<div id = "iconNav" class = "padding">
	<div><a href="<%= request.getContextPath() %>/board/addNewBoard.jsp"><img src = "<%= request.getContextPath() %>/images/board/add182.png" />Add Channle</a></div>
</div>
<br><br>
<div id="boardList" class="padding">
	<table class = "table">
		<thead>
			<tr>
				<td>收藏</td>
				<td>標題</td>
				<td>更新時間</td>
				<td>發言人</td>
				<td>留言數</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty boardList}">
			<c:forEach var="boardList" items="${boardList}">
				<tr>
					<td class="starIcon" id="${boardList.boardId }">
						<img  src = "<%= request.getContextPath() %>/images/board/star129_gray.png"></img>
					</td>
					<td><a href="Board/${boardList.boardId }">${boardList.boardTitle }</a></td>
					<td>${fn:substring(boardList.boardTime ,0,16) }</td>
					<td>${boardList.users.userName }</td>
					<td>${fn:length(boardList.subs)-1} </td>
				</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>


</div>

<!---------------- main page code end---------------------------------->
<jsp:include page="../main/workHome/foot.jsp" />
<script>
$(function(){
	$.ajax({
		  'type':'get', 
		  'url':'BoardServlet/getMyFav',
		  'dataType':'json',  
		  'data':{},
		  'success':function(data){
			  console.log("here is response");
			  console.log(data)
			  $.each(data,function(i,product){
				  console.log(product.boardId);
				  console.log(product.favTitle)
				  $("#"+product.boardId+">img").attr('src','/TwentyWork/images/board/star129_yellow.png')
			  })
		  }
	  });//end of $.ajax({ 
			
	$("td[class='starIcon']>img").click(function(){
		console.log($(this).attr('src'));
		if($(this).attr('src')=="/TwentyWork/images/board/star129_gray.png"){
			console.log("gray")
			$(this).attr('src','/TwentyWork/images/board/star129_yellow.png')
		}else{
			console.log("yellow")
			$(this).attr('src','/TwentyWork/images/board/star129_gray.png')
			$.ajax({
				'type':'get', 
				'url':'BoardServlet/deleteMyFav',
				'dataType':'json',  
				'data':{boardId:},
				'success':function(data){
					
				}
			});//end of $.ajax({ 
		}

	})//end of $("td[class='starIcon']>img").click(function(){   .find('img')
		
	
		
})// end of $(function(){
</script>
</body>
</html>