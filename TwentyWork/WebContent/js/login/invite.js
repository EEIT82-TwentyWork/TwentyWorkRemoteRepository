/*jQuery time*/

$(document).ready(function(){   
	var i =1;
	$("#JoinList").click(function(){
		console.log(i);	
		$("#EmaiList").append("<li>"+"<input type ='text' class='emails' name='inviteEmail" + i + "' value='" + $("#EmailAdd").val() + "'>"+"</li>");
		$("#EmailAdd").val('');
		$("#count").val(i);
		i++;
	})
})