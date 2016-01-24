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
	
//	$("#toabc").click(function(){
////		$("#sketchCanvas").width($("#sketchCanvas").width()-50);\
//		var messagesHeight=$("#messagesTextArea").height();
//		$("#sketchCanvas").slideToggle(1500);
////		
//		if(messagesHeight=="170"){
//			$("#messagesTextArea").height($("#messagesTextArea").height()+200);
//		}else{
//			$("#messagesTextArea").height($("#messagesTextArea").height()-200);
//		}
//	})
	

//--------------------ChatMatch chatID----------------------------------------------------------  
	$("#chatName").on("click",".abctest",function(){
		var matchID=$(this).attr('id');
		$.ajax({
			url: '../../com/iii/twentywork/controller/main/ChatMatchServlet',
			type: "post",
            data:{"matchID":matchID},
			dataType: 'text',
			success:function(data){
	
//--------------------Websocket ---------------------------------------------------------- 
	  // Get references to elements on the page.
	  var form = document.getElementById('message-form');
	  var messageField = document.getElementById('chat-text');
	  var messagesList = document.getElementById('chat-messages');
	  var socketStatus = document.getElementById('status');
	  var closeBtn = document.getElementById('close');
	  var socket =new WebSocket("ws://localhost:8080/TwentyWork/com/iii/twentywork/model/service/websocket/ChatroomServerEndpoint/"+data+"");
//------------error------------------------------------
	  socket.onerror = function(error) {           
	    console.log('WebSocket Error: ' + error);
	  };
//------------open-------------------------------------
	  socket.onopen = function(event) {
	    socketStatus.innerHTML = 'Connected to: ws://echo.websocket.org';
	    socketStatus.className = 'open';
		  };
//------------message-------------------------------------
	  socket.onmessage = function(message) {
		 var jsonData = JSON.parse(message.data);
			if (jsonData.message != null){
				messagesTextArea.value += jsonData.message + "\n";
			}	
//	    messagesList.innerHTML += '<li class="received"><span id="chat-messages-re">Received:</span>' +
//	    jsonData + '</li>';
	  };
	  function sendMessage() {
		  socket.send(messageText.value);
			messageText.value = "";
		}
	  
	  socket.onclose = function(event) {
	    socketStatus.innerHTML = 'Disconnected from WebSocket.';
	    socketStatus.className = 'closed';
	  };
	  form.onsubmit = function(e) {
	    e.preventDefault();
	    var message = messageField.value;
	    socket.send(message);
	    messageField.value = '';
	    return false;
	  };

	  // Close the WebSocket connection when the close button is clicked.
//	  closeBtn.onclick = function(e) {
//	    e.preventDefault();

	    // Close the WebSocket.
//	    socket.close();
//	    return false;
//	  };
			 
			}
		})
	})
//---------------------------------------------------------------------------------------------
	$("#logoutid").click(function(){
		$.ajax({
			url: '../../com/iii/tewntywork/controller/login/LogoutServlet',
			type: 'post',
			success:function(data){
				
			}
		})
	})
})
