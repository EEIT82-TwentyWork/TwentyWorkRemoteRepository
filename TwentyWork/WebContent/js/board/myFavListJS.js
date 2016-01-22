$(function(){
	 console.log("here is js/board/myFavListJS.js");
	$.ajax({
		  'type':'get', 
		  'url':'BoardServlet/getMyFav',
		  'dataType':'json',  
		  'data':{},
		  'success':function(data){
			  console.log("here is response");
			  console.log(data)
			  $.each(data,function(i,product){
				  console.log(product.boardId);
				  console.log(product.favTitle)
				  $("#myFavLeftList>ul").append("<li><div><img  src = 'images/board/star129_yellow.png'></div><a href='/TwentyWork/Board/"+product.boardId+"'>"+product.favTitle+"</a></li>")
			  	  $("#myFavLeftList>ul>li>div").addClass('floatLeftOnly');
			  })
		  }
	  });//end of $.ajax({ 
})
