<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#folderDisplayNone{
		display:none;
	}

</style>

</head>
<body>
	<div class="fancy">
		<h4>請輸入欲更改的檔名或資料夾名稱</h4>
		<form  method="post" enctype="multipart/form-data">
			<div>
			<input type="text" id="rename" autofocus="autofocus"/>
			<input type="button" id="renameSubmitButton" value="確定更改" />
			<h4 id="folderDisplayNone">資料夾名稱重複</h4>
			</div>
		</form>
		
	</div>
</body>
<script>
$('#renameSubmitButton').click(function(){
	var rename = $('#rename').val();
	var fileId = $('tr[class="listBackground"]>td:eq(0)').text();
	var fileType =  $('tr[class="listBackground"]>td:eq(2)').text();
	
	if(fileType!=='資料夾'){
		rename=rename+"."+fileType;
	}
// 	console.log(rename)
// 	console.log(fileType)
	
	$.fancybox.close();
	$.ajax({
		  'type':'get', 
		  'url':'<%= request.getContextPath() %>/ShareFileServlet/renamefile',
		  'dataType':'json',  
		  'data':{fileId:fileId,fileName:rename},
		  'success':function(data){
			  if($('tr[class="listBackground"]>td').hasClass('fileName')){
				  
				  $('tr[class="listBackground"]>td[class="fileName"]').text(rename);
				}else{
					$('tr[class="listBackground"]>td>a').text(rename);
					var href = $('#navPath>a:last').attr('href')+"/"+rename;
					$('tr[class="listBackground"]>td>a').attr('href',href);
				}
		  }
	
	})//end of  $.ajax({
	
})//end of $('#renameSubmitButton').click(function(){ 
	
	
	
</script>
</html>