<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=UTF-8">

<title>事件</title>
</head>
<body>
	<div class="fancy">
		<h3>事件</h3>
		<form id="add_form" action="../../com/iii/tewntywork/controller/calendar/CalServlet.controller"  method="post">

		<table>
			<tr>
				<td>事件標題 : </td>
				<td><input type="text" name="title" value=<%=request.getParameter("title")==null ? "" : request.getParameter("title")%>></td>
				<td>${errors.title}</td>
			</tr>
			<tr>
				<td>開始時間 : </td>
				<td><input type="text" name="start" value="<%=request.getParameter("startdate")+" "+request.getParameter("starttime")%>" id="start" class="input datepicker"></td>
				<td>${errors.starttime}</td>
			</tr>
			<tr>
				<td>結束時間 : </td>
				<td><input type="text" name="end" value="<%=request.getParameter("enddate")+" "+request.getParameter("endtime")%>" id="end" class="input datepicker"></td>
				<td>${errors.endtime}</td>
			</tr>
			<tr>
				<td>全天 : </td>
				<td><input type="checkbox" name="allday" value=<%=request.getParameter("allday")==null ? "1" : request.getParameter("allday")%> id="allday" <%=request.getParameter("allday")==null ? "" : "checked"%>></td>
<!-- 				checked -->
				<td>${errors.allday}</td>
			</tr>
			<tr>
				<td>事件內文 : </td>
				<td><input type="text" name="myEventDesp" value=<%=request.getParameter("myEventDesp")==null ? "" : request.getParameter("myEventDesp")%>></td>
				<td>${errors.myEventDesp}</td>
			</tr>
			<tr>
				<td>地點 : </td>
				<td><input type="text" name="myLocation" value=<%=request.getParameter("myLocation")==null ? "" : request.getParameter("myLocation")%>></td>
				<td>${errors.myLocation}</td>
			</tr>
			<tr>
				<td>提醒時間 : </td>
				<td><input type="checkbox" name="myRmindercheckbox" value=<%=request.getParameter("myRmindercheckbox")==null ? "1" : request.getParameter("myRmindercheckbox")%> id="myRmindercheckbox" <%=request.getParameter("myRmindercheckbox")==null ? "" : "checked"%>>
				<input type="text" name="myRminder" value="<%=request.getParameter("myRminder")+" "+request.getParameter("myRminder")%>" id="myRminder" class="input datepicker" style="display:<%=request.getParameter("myRmindercheckbox")=="1" ? "inline" : "inline"%>"></td>
				<td>${errors.endtime}</td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" name="prodaction" value="Insert" >
					<input type="submit" name="prodaction" value="Update" style="display:<%=request.getParameter("id")==null ? "none" : "inline"%>" >
				</td>
				<td>
					<input type="submit" name="prodaction" value="Delete" style="display:<%=request.getParameter("id")==null ? "none" : "inline"%>">
					<input type="submit" name="prodaction" value="Select">
				</td>
				<td>
					<input type="text" name="id" id="id" value=<%=request.getParameter("id")%> readonly="readonly" >
				</td>
			</tr>
		</table>

		</form>
	</div>
	<script>
var starttimeBox = $('#start');
var endtimeBox = $('#end');
var myRminderBox = $('#myRminder');

$.timepicker.datetimeRange(
		starttimeBox,
		endtimeBox,
	{
		minInterval: (1000*60*60), // 1hr
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm',
		addSliderAccess:true,
        showTime: false,
        sliderAccessArgs:{touchonly:false}
	}
);
myRminderBox.datetimepicker({
		dateFormat: 'yy-mm-dd',
		timeFormat: 'HH:mm',
		addSliderAccess:true,
	    showTime: false,
	    sliderAccessArgs:{touchonly:false}
})
	</script>
	
	
</body>
</html>