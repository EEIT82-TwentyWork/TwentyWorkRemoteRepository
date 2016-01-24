<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=UTF-8">
<title>Calendar</title>
<jsp:include page="calinclude.jsp" />
<script type="text/javascript" src="../../js/calendar/Calendar.js"></script>
<style>
html {
	background: url('http://thecodeplayer.com/uploads/media/gs.png');
}
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	font-size: 14px;
	margin: 0 auto;
}
#calendarschedule {
	max-width: 900px;
	margin: 0 auto;
}

#callogo {
	margin: 0 auto;
}

</style>
</head>
<body>	
	<img alt="TwentyWork" id="callogo" src="../../images/index/Logo.png">
	<div id="calendarschedule" style="margin:100px auto"></div> 
</body>
</html>