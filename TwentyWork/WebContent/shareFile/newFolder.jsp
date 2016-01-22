<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	#folderDisplayNone{
		display:none;
	}

</style>

<title>Insert title here</title>
</head>
<body>
	<div class="fancy">
		<h4>請輸入資料夾名稱</h4>
		<form  method="post" enctype="multipart/form-data">
			<div>
			<input type="text" id="folderName" autofocus="autofocus"/>
			<input type="button" id="folderButton" value="submit" />
			<h4 id="folderDisplayNone">資料夾名稱重複</h4>
			</div>
		</form>
		
	</div>
</body>
<script>
// 	console.log("here is newFolder.jsp")
	
// 	var n = $('tr[id^="folder"]>td>a').length;//由main.jsp中取得
// 	var folderName = [];
// 	for (var i = 0; i < n; i++) {
// 		folderName[i] = $('tr[id^="folder"]>td>a:eq('+i+')').text();
// // 		console.log($('tr[id^="folder"]>td>a:eq('+i+')').text());
// 	}
// 	console.log(folderName);
	
	$('#folderButton').click(function(){
// 		console.log($('#navPath>a:eq(0)').attr('href'));
// 		console.log($('#navPath>a:eq(1)').attr('href'));
// 		console.log($('#navPath>a:last').attr('href'));
// 		console.log("click inside" +$('tr[id^="folder"]>td>a:last').text());
// 		console.log($('#folderName').val());
		$.ajax({
			  'type':'post', 
			  'url':'<%= request.getContextPath() %>/ShareFileServlet/newFolder',
			  'dataType':'json',  
			  'data':{newfolderName:$('#folderName').val()},//{list:JSON.stringify(session)},
			  'success':function(data){
				  $.fancybox.close();
// 				  console.log($('tr[id^="folder"]>td>a:last').text());
// 				  console.log(data);
// 				$('#buttonAdd').bind("click",function(){	
// 					  console.log(fileList.fileId);
					  var fileList = data[0];
					  $('#fileList').prepend("<tr id='folder"+fileList.fileId+"'  onclick='listBackGround()' class></tr>"); 
					  $('#folder'+fileList.fileId).click(listBackGround);
					  $('#fileList>tr#folder'+fileList.fileId).append("<td  style='display:none'>"+fileList.fileId+"</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td><a href ='"+$('#navPath>a:last').attr('href')+"/"+fileList.fileName+"' class='trtda'>"+fileList.fileName+"</a></td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td  style='display:none'>"+fileList.fileType+"</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td>-</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td  style='display:none'>"+fileList.userId+"</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td>"+fileList.userName+"</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td  style='display:none'>"+fileList.teamId+"</td>");
					  $('#fileList>tr#folder'+fileList.fileId).append("<td  style='display:none'>"+fileList.teamName+"</td>");
			  }
		  });//end of $.ajax({ 
	});
</script>
</html>