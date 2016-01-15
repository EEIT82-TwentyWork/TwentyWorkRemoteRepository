<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=UTF-8">
<title>Calendar</title>

<!-- jQuery -->
<link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src='https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>

<!-- Fullcalendar -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.5.0/fullcalendar.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.5.0/fullcalendar.print.css' media='print'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.5.0/fullcalendar.min.js"></script>

<!-- Timepicker plugin -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-timepicker-addon.min.css'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-timepicker-addon.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-sliderAccess.min.js"></script>

<!-- i18n -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/i18n/jquery-ui-i18n.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/i18n/jquery-ui-timepicker-zh-TW.js"></script>

<!-- BootStrap -->
<script src="../../js/bootstarp/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../css/bootstrap/bootstrap.css" type="text/css" />

<!-- FancyBox -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" type="text/css" media="screen" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.pack.js"></script>

<script type="text/javascript">
// 進入網頁時先讀取
$(document).ready(function() {
// 	呼叫fullCalendar
	$('#calendarschedule').fullCalendar({ 
		theme: true,
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		defaultDate: new Date(),
		editable: true,
		eventLimit: true, 
		timezone: 'local',
// 		接收json資料給fullCalendar讀取
	    events: function(start, end, timezone, callback) {
// 	       	 呼叫CalServlet的Call抓出相對應資料
	    	$.ajax({
	            url: '../../com/iii/tewntywork/controller/calendar/CalServlet.controller',
	            dataType: 'json',
	            data: { 
	            	prodaction: 'Call'
	            },
	            success: function(doc) {
	            	callback(doc);
	            }
	        });
	    },
// 	    行事曆事件移動時做的方法
	  eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
	    var eventTitle = event.title;
	    var startdate = moment(new Date(event.start)).format('YYYY-MM-DD');
	    var starttimehour = moment(new Date(event.start)).format('HH');
	    var starttimemin = moment(new Date(event.start)).format('mm');
	    var enddate = moment(new Date(event.end)).format('YYYY-MM-DD');
	    var endtimehour = moment(new Date(event.end)).format('HH');
	    var endtimemin = moment(new Date(event.end)).format('mm');
	    var myEventDesp = event.myEventDesp;
	    var myLocation = event.myLocation;
	    var myRminderdate = moment(new Date(event.myRminder)).format('YYYY-MM-DD');
	    var myRmindertimehour = moment(new Date(event.myRminder)).format('HH');
	    var myRmindertimemin = moment(new Date(event.myRminder)).format('mm');
	    var id = event.id;
	    
	    var allday = event.allday;
// 	    alert(allday);
//     	if(!event.allday.match("undefined")){
//     		var allday = event.allday;
//     	} else {
//     		var allday = null;
//     	};
    	alert(allday);
    	if(event.myEventDesp.match("Invalid")){
	    	var myEventDesp = null;
    	};
	    if(myLocation.match("Invalid")){
	    	myLocation = null;
    	};
	    if(myRminderdate.match("Invalid")){
	    	myRminderdate = null;
    	};
    	if(myRmindertimehour.match("Invalid")){
    		myRmindertimehour = null;
    	};
    	if(myRmindertimemin.match("Invalid")){
    		myRmindertimemin = null;
    	};
	    
//     	if (confirm("Are you sure about this change?")) {
    		$.ajax({
		   		url: '../../com/iii/tewntywork/controller/calendar/CalServlet.controller',
		   		data: { prodaction: 'Update',
		   				title: eventTitle,
		   				startdate: startdate,
		   				starttimehour: starttimehour,
		   				starttimemin: starttimemin,
		   				enddate: enddate,
		   				endtimehour: endtimehour,
		   				endtimemin: endtimemin,
		   				myEventDesp: myEventDesp,
		   				myLocation: myLocation,
		   				myRminderdate: myRminderdate,
		   				myRmindertimehour: myRmindertimehour,
		   				myRmindertimemin: myRmindertimemin,
		   				allday: allday,
		   				id: id
		   			}
		   	});
//         } else {
//         	revertFunc();  	
// 		}
		},
		
	    dayClick: function(datetime, allDay, jsEvent, view) {
	        var newdate = datetime.format();
	        $.fancybox({//燈箱彈出
	         'type':'ajax', 
	         'href':'event.jsp?startdate='+newdate
	     	}); 
	    },
	    
	    eventClick: function(calEvent, jsEvent, view) {
	    	var eventTitle = calEvent.title;
	    	var startdate = moment(new Date(calEvent.start)).format('YYYY-MM-DD');
	    	var starttime = moment(new Date(calEvent.start)).format('HH:mm');
	    	var enddate = moment(new Date(calEvent.end)).format('YYYY-MM-DD');
	    	var endtime = moment(new Date(calEvent.end)).format('HH:mm');
	    	var myEventDesp = calEvent.myEventDesp;
	    	var myLocation = calEvent.myLocation;
	    	var myRminderdate = moment(new Date(calEvent.myRminder)).format('YYYY-MM-DD');
	    	var myRmindertime = moment(new Date(calEvent.myRminder)).format('HH:mm');
	    	var eventAllday = calEvent.allday;
	    	var id = calEvent.id;
	    	
	    	if(myRminderdate.match("Invalid")){
	    		myRminderdate = null;
	    	}
	    	if(myRmindertime.match("Invalid")){
				myRmindertime = null;
	    	}
	    	
	        $.fancybox({//燈箱彈出
		         'type':'ajax', 
		         'href':'event.jsp?title='+eventTitle+'&startdate='+startdate+'&starttime='+starttime+'&enddate='+enddate+'&endtime='+endtime+'&allday='+eventAllday+'&id='+id+'&myEventDesp='+myEventDesp+'&myLocation='+myLocation+'&myRminderdate='+myRminderdate+'&myRmindertime='+myRmindertime
		     	}); 
	    }
	    
	}); 

}); 

</script>
<style>
	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}
	#calendarschedule {
		max-width: 900px;
		margin: 0 auto;
	}
</style>

</head>
<body>
	<div id="calendarschedule"></div> 
	
	<button id="somebutton">press here</button>
</body>
</html>