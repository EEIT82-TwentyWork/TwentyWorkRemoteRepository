<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
*,
*:before,
*:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
* {
    margin: 0;
    padding: 0;
    border: 0 none;
    position: relative; 
    -webkit-transition: all .4s;
    -moz-transition: all .4s;
    transition: all .4s;
}
html,
body {
    width: 100%;
    height: 100%;
    font-family: 'Fjalla One', sans-serif;
/*     font-size: 18px; */
}
.nav {
    margin: 20px auto;
    left: 60px;
    width: 455px;
    min-height: auto;
/*     float:left; */
}
ul.list,
ul.list ul {
    list-style-type: none;
    margin:0;
    padding:0;
}
ul.list ul {
    margin-left:10px; 
    position:relative;
}
ul.list ul:before {
    content:"";
    display:block;
    width:0;
    position:absolute;
    top:0;
    bottom:0;
    left:0;
    border-left:1px solid #ccc;
}
ul.list li  {
    margin:0;
    padding:3px 12px;
    text-decoration: none;
    text-transform: uppercase;
    font-size:13px;
    line-height:20px;
/*     color:#ccc; */
    font-weight:normal;
    position:relative;
}
ul.list li a { 
    text-decoration: none;
    text-transform: uppercase;
    font-size:14px;
    line-height:20px;
/*     color:#ccc; */
    font-weight:bold;
    position:relative;
}
ul.list li a:hover,
ul.list li a:hover+ul li a {
/*      color: RGBA(213, 235, 227, 1);  */
}
ul.list ul li:before {
    content:"";
    display:block;
    width:8px;
    height:0;
    border-top:1px solid #ccc;
    position:absolute;
    top:10px;
    left: 0;	
}
ul.list ul li:last-child:before {
    background: white;  
    height: auto;
    top: 10px; 
    bottom: 0;
}
#folderTreeTitle {
    text-align: center;
}
span{
	padding:2px;
}
input{
    margin-left: 190px;
    padding: 3px;
    border: 3px;
}
#inputpadding{
 	padding-bottom: 8px;
}

</style>
</head>
<body>
<h4 id="folderTreeTitle">請選擇要移動到的資料夾</h4>
	<nav class="nav">
	  	<ul class=list >
    		<li class="level0"><span class="listBackground">Home</span></li>
     	</ul>
   </nav>
   <div id="inputpadding">
   		<input type = "button"  value = "確定"></a>
   </div>
   <script>
   $(function(){
	   $.ajax({
		   'type':'get', 
			  'url':'<%= request.getContextPath() %>/ShareFileServlet/getFolderTree',
			  'dataType':'json',  
			  'data':{},
			  'success':function(data){
// 				  console.log("here is response");
// 				  console.log(data)
				  var folderLevel=0;
				  $.each(data,function(i,folder){
					  console.log(folder.fileLevel+","+folder.fileId+","+folder.fileName+","+folder.upperFolderId);
					  var nowLevel=folder.fileLevel;
					
					  if(nowLevel>=1){
						  $('ul#'+folder.upperFolderId).append("<li class='level"+nowLevel+"'><span>"+folder.fileName+"</span><ul id='"+folder.fileId+"'></ul></li>")
					  }else{
						  $('li[class="level'+folderLevel+'"]').append("<ul id='"+folder.fileId+"'></ul>")
					  }
				  
				  })  // end of $.each(data,function(i,folder){
				  $("span").click(changeColor);
// 				  $.fancybox.close();
			  }
		   
	   });//end of $.ajax({
	   function changeColor(){
			if($(this).hasClass('listBackground')){
	 			 $(this).removeClass('listBackground');
	 		 }else{
	 			 $('span[class="listBackground"]').removeClass('listBackground');
	 			 $(this).addClass('listBackground');
	 		 }
	 	}//end of  function listBackGround(){
	 		
	 		
	 	$('#inputpadding>input').click(function(){
	 		var nowFolderId = $('#folders>tbody>tr>td[class="fileId"]:last').text();
	 		var newFolderId = $('span[class="listBackground"]').next().attr('id');
	 		if(newFolderId==nowFolderId){
	 			$.fancybox.close();
	 		}else{
		 		var session = {'list':[],'newFolderId':newFolderId};
		 		$('tr[class^="listBackground"][id^="f"]').each(function(i,selected){
		 			var selectedId = $(selected).attr('id')
		 			if(selectedId!=newFolderId){
		 				session.list.push({'fileID':selectedId})
		 			}
		 		});
		 		console.log(JSON.stringify(session))
				$.ajax({
					'type':'get',
					'url':'<%= request.getContextPath() %>/ShareFileServlet/updateFolder',
					'dataType':'json',
					'data':{moveObj:JSON.stringify(session)},
					'success':function(data){
						console.log("here is move file response");
						console.log(data)
						$.each(data,function(i,removeObj){
							$('#'+removeObj.fileId).remove();
						});
						$.fancybox.close();
					}
				})//end of $.ajax({
			}
	 	});//end of $('#inputpadding>input').click(function(){	
	 	
   });//end of $(function(){
   </script>
</body>
</html>