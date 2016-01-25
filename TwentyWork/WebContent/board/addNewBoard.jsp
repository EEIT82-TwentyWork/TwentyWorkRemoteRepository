<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.padding {
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 30px;
}
</style>
</head>
<body>
<jsp:include page="../main/workHome/head.jsp" />
<!---------------- main page code ---------------------------------->
<div id="boardList" class="padding">

<form action="<%= request.getContextPath() %>/BoardServlet/insert">
	標題:<input type="text" name ="boardTitle" placeholder="請輸入標題"/>${errors.boardTitleEmpty }<br><br>
	內文:<textarea rows="4" cols="50" name="boardText" ></textarea>
	<input type = "submit" value="送出" />
	<a href = "../Board"><input type = "button" value="取消" id="cancleNewBoard"/></a>
	
</form>

</div>

<!---------------- main page code end---------------------------------->
<jsp:include page="../main/workHome/foot.jsp" />
<script type="text/javascript">
// $(function(){
// 	$("#cancleNew")
	
	
	
}
</script>
</body>
</html>