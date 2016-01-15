/*jQuery time*/
//--------------------撈ChatList----------------------------------------------------------
$(document).ready(function(){
	$.ajax({
        url: '../../com/iii/twentywork/controller/main/MainAjaxServlet',
        dataType: 'json',
        success:function(data){
        		for(var i =0;i<data.length;i++){
        			var into;
        			into="<li class='abctest'"+" id="+data[i].userID+"><a href='#'>"+data[i].userName+"</a></li>"
        				
        			$("#chatName").append(into);
        		}
        }
//--------------------E1----------------------------------------------------------     
        });
	$("#accordian h3").click(function(){
		//slide up all the link lists
		$("#accordian ul ul").slideUp();
		//slide down the link list below the h3 clicked - only if its closed
		if(!$(this).next().is(":visible"))
		{
			$(this).next().slideDown();
		}
	})
	$("#chatName").on("click",".abctest",function(){
		
		var matchID=$(this).attr('id');
		$.ajax({
			url: '../../com/iii/twentywork/controller/main/ChatMatchServlet',
			 type: "post",
             data:{"matchID":matchID},
			dataType: 'text',
			 success:function(data){
			 }
		})
	})

})
//
//	$("#40289ff9524396ce0152439948330000").on("click",function(){
//		alert("hahda");
//	})