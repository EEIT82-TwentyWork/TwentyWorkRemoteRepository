// 進入網頁時先讀取
$(document).ready(function() {
//window.onload = function() {
	// 檢查是否為管理者呼叫servlet
	$("#calendarh3id").click(function(){
// var matchID=$(this).attr('id');
		$.ajax({
			url: '../../com/iii/twentywork/controller/calendar/CheckTeamLeaderServlet',
			 type: "post",
			dataType: 'text',
			 success:function(data){
				 if(data=="1") {
	        			var groupcalendar;
	        			groupcalendar="<li id='TeamCalLink'><a href='../calendar/teamcal_opt.jsp'>團隊行事曆</a></li>"
	        			$("#calendarulid").append(groupcalendar);
				}
			 }
		})
	})
	// 方法結束
	
//};
 });
