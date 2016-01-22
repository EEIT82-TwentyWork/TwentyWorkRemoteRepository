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
		if(event.color=="red"){
			alert("您無法修改團體行事曆");
			revertFunc();
		} else {
		    var eventTitle = event.title;
		    var startdate = moment(new Date(event.start)).format('YYYY-MM-DD');
		    var starttimehour = moment(new Date(event.start)).format('HH');
		    var starttimemin = moment(new Date(event.start)).format('mm');
		    var enddate = moment(new Date(event.end)).format('YYYY-MM-DD');
		    var endtimehour = moment(new Date(event.end)).format('HH');
		    var endtimemin = moment(new Date(event.end)).format('mm');
		    var eventDesp = event.eventDesp;
		    var location = event.location;
		    var rminderdate = moment(new Date(event.rminder)).format('YYYY-MM-DD');
		    var rmindertimehour = moment(new Date(event.rminder)).format('HH');
		    var rmindertimemin = moment(new Date(event.rminder)).format('mm');
		    var id = event.id;
		    var color = event.color;
		    var allday = event.allday;
	
	    	if(event.eventDesp.match("Invalid")){
		    	var eventDesp = null;
	    	};
		    if(location.match("Invalid")){
		    	location = null;
	    	};
		    if(rminderdate.match("Invalid")){
		    	rminderdate = null;
	    	};
	    	if(rmindertimehour.match("Invalid")){
	    		rmindertimehour = null;
	    	};
	    	if(rmindertimemin.match("Invalid")){
	    		rmindertimemin = null;
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
			   				eventDesp: eventDesp,
			   				location: location,
			   				rminderdate: rminderdate,
			   				rmindertimehour: rmindertimehour,
			   				rmindertimemin: rmindertimemin,
			   				allday: allday,
			   				color: color,
			   				id: id
			   			}
			   	});
		}
    		
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
	    	var eventDesp = calEvent.eventDesp;
	    	var location = calEvent.location;
	    	var rminderdate = moment(new Date(calEvent.rminder)).format('YYYY-MM-DD');
	    	var rmindertime = moment(new Date(calEvent.rminder)).format('HH:mm');
	    	var eventAllday = calEvent.allday;
	    	var eventcolor = calEvent.color;
	    	var id = calEvent.id;
	    	
	    	if(rminderdate.match("Invalid")){
	    		rminderdate = null;
	    	}
	    	if(rmindertime.match("Invalid")){
				rmindertime = null;
	    	}
	    	
	        $.fancybox({//燈箱彈出
		         'type':'ajax', 
		         'href':'event.jsp?title='+eventTitle+'&startdate='+startdate+'&starttime='+starttime+'&enddate='+enddate+'&endtime='+endtime+'&allday='+eventAllday+'&id='+id+'&eventDesp='+eventDesp+'&location='+location+'&rminderdate='+rminderdate+'&rmindertime='+rmindertime+'&color='+eventcolor
		     	}); 
	    }
	    
	}); 
	
	
	
	
	
}); 
