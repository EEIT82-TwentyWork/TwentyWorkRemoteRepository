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
				<td><input type="text" class="checkreadonly" id="title" name="title" value=<%=request.getParameter("title")==null ? "" : request.getParameter("title")%>></td>
				<td>${errors.title}</td>
			</tr>
			<tr>
				<td>開始時間 : </td>
				<td><input type="text" class="checkreadonly" id="start" name="start" value="<%=request.getParameter("startdate")+" "+request.getParameter("starttime")%>" ></td>
				<td>${errors.starttime}</td>
			</tr>
			<tr>
				<td>結束時間 : </td>
				<td><input type="text" class="checkreadonly" id="end" name="end" value="<%=request.getParameter("enddate")+" "+request.getParameter("endtime")%>" ></td>
				<td>${errors.endtime}</td>
			</tr>
			<tr>
				<td>全天 : </td>
				<td><input type="checkbox" class="checkreadonly" id="allday" name="allday" value="1"></td>
				<td>${errors.allday}</td>
			</tr>
			<tr>
				<td>事件內文 : </td>
				<td><input type="textarea" class="checkreadonly" id="eventDesp" name="eventDesp" value="<%=request.getParameter("eventDesp")==null ? "" : request.getParameter("eventDesp")%>"></td>
				<td>${errors.eventDesp}</td>
			</tr>
			<tr>
				<td>地點 : </td>
				<td><input type="text" class="checkreadonly" id="location" name="location" value=<%=request.getParameter("location")==null ? "" : request.getParameter("location")%>></td>
				<td>${errors.location}</td>
			</tr>
			<tr style='display:none'>
				<td >提醒時間 : </td>
				<td><input type="checkbox" class="checkreadonly" id="rmindercheckbox" name="rmindercheckbox" value=<%=request.getParameter("rmindercheckbox")==null ? "1" : request.getParameter("rmindercheckbox")%> <%=request.getParameter("rmindercheckbox")==null ? "" : "checked"%>>
				<input type="text" class="checkreadonly" id="rminder" name="rminder" value="<%=request.getParameter("rminder")+" "+request.getParameter("rminder")%>" style="display:<%=request.getParameter("rmindercheckbox")=="1" ? "inline" : "inline"%>"></td>
				<td>${errors.rminder}</td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" class="checkreadonly" name="prodaction" value="Insert" style="display:<%=request.getParameter("id")==null ? "inline" : "none"%>">
					<input type="submit" class="checkreadonly" name="prodaction" value="Update" style="display:<%=request.getParameter("id")==null ? "none" : "inline"%>" >
					<input type="submit" class="checkreadonly" name="prodaction" value="Delete" style="display:<%=request.getParameter("id")==null ? "none" : "inline"%>">
				</td>
<!-- 				檢查id號碼 -->
				<td>
					<input type="text" class="checkreadonly" id="id" name="id" value="<%=request.getParameter("id")%>" style="display:none" >
					<input type="text" class="checkreadonly" id="color" name="color" value="<%=request.getParameter("color")%>" style="display:none" >
					<input type="text" class="checkreadonly" id="lookallday" name="lookallday" value="<%=request.getParameter("allday")%>" style="display:none">
				</td>
			</tr>
		</table>

		</form>
	</div>
<script>

$(document).ready(function(){
	var col1 =$("#color").val();
	var checkallday = $("#lookallday").val();
	
	if(checkallday == "1") {
		$("#allday").attr("checked","checked");
	}
	
	if(col1=="red"){
// 		利用ID設定唯讀方法
// 		document.getElementById("color").readOnly=true
// 		利用class設定唯讀方法
		$(".checkreadonly").attr("readonly","readonly");
	} else {
		var starttimeBox = $('#start');
		var endtimeBox = $('#end');
		var myRminderBox = $('#rminder');

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
		});
	}
	
	
})

</script>
	
	
</body>
</html>