<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
*,
*:before,
*:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
* {
    margin: 0;
    padding: 0;
    border: 0 none;
    position: relative; 
    -webkit-transition: all .4s;
    -moz-transition: all .4s;
    transition: all .4s;
}

</style>
</head>
<body>
<%-- <c:forEach var="member" items="${sessionScope.teamBean.userses}"> --%>
<%-- 	${member.userName }<br> --%>
<%-- </c:forEach> --%>
<table id="memberListTable" class = "table">
	<thead>
		<tr>
		<td COLSPAN='2'>請選擇要分享的人員</td>
		</tr>
	</thead>
	<tbody>
		<tr >
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td><input type ="button" value = "確定"></td>
			 <td><input type ="button" value = "取消"></td>
		</tr>
	</tfoot>
</table>

<script>
$(function(){
$.ajax({
	  'type':'get', 
	  'url':'<%= request.getContextPath() %>/ShareFileServlet/getMember',
	  'dataType':'json',  
	  'data':{},
	  'success':function(data){
		  console.log("here is response");
		  console.log(data);
		  $.each(data,function(i,product){
			  $("table#memberListTable>tbody").append("<tr><td COLSPAN='2'>"+product.userName+"</td></tr>")
			   $("table#memberListTable>tbody>tr").click(listBackGround);
			  console.log(product.userID);
			  console.log(product.userName);
		  })
	  }
});//end of $.ajax({
	
function changeColor(){
	if($(this).hasClass('listBackground')){
			 $(this).removeClass('listBackground');
		 }else{
			 $('span[class="listBackground"]').removeClass('listBackground');
			 $(this).addClass('listBackground');
		 }
	}//end of  function listBackGround(){
	
});
</script>


</body>

</html>