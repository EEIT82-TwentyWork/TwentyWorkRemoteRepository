<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShareFile</title>
<link rel="stylesheet" href="/FinalProject/css/bootstrap/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript" src="/FinalProject/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/FinalProject/css/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="/FinalProject/css/fancybox/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="/FinalProject/css/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<link rel="stylesheet" href="/FinalProject/css/fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
	
	
<style>
.padding {
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 5px;
}

.padding2 {
	padding-left: 15px;
	padding-right: 15px;
}
#fancybox-wrap {
	padding: 0px;
}

#fancybox-wrap *,
#fancybox-wrap *:before,
#fancybox-wrap *:after {
    -webkit-box-sizing: content-box !important;
    -moz-box-sizing: content-box !important;
    box-sizing: content-box !important;
}
.iconNotDisplay{
	display:none;
}
.iconShow{
}

.listBackground{
	background-color: #DFFFDF;
}
 
</style>



</head>
<body>

<div  class='padding'>
	<table ><tr>
		<td class='padding2'>Hello</td>
		<td class='padding2'>userID:100</td>
		<td class='padding2'>userName:Kirin</td>
		<td class='padding2'>groupID:200</td>
	</tr></table>
	<br>fileUploadMessage:<br>
</div>
<hr>
<!-- path -->
<div   class='padding'>

	
	
		
			
			<a href = "/FinalProject/ShareFile">ShareFile</a>
		
		
	
	
</div>
<br>
<!-- icon -->
<div   class='padding'>
	<a id="insertFile" href="/FinalProject/shareFile/uploadFile.jsp"><img alt="Upload" title ="Upload" src="/FinalProject/image/fileUploadcloud148.png" /></a>
	<a id="NewFolder"  href="/FinalProject/shareFile/newFolder.jsp"><img alt ="New Folder" title ="New Folder"  src="/FinalProject/image/newfolder15.png"  /></a>
	<img id="iconDownload" class="iconNotDisplay" alt ="Download" title ="Download"  src="/FinalProject/image/fileDownloadCloud134.png" />
	<img id="iconCopy" class="iconNotDisplay" alt ="Copy" title ="Copy"  src="/FinalProject/image/copyfile19857.png" />
	<img id="iconDelete" class="iconNotDisplay" alt ="Delete" title ="Delete"  src="/FinalProject/image/delete84453783.png" />
	<img id="iconRename" class="iconNotDisplay" alt ="Rename" title ="Rename"   src="/FinalProject/image/renameedit42.png" />
	<img id="iconMove" class="iconNotDisplay" alt ="Move" title ="Move"   src="/FinalProject/image/movesend2.png" />
	
</div>
<br>
<!-- file list -->
<div   class='padding'>
<table class="table">
	<thead>
		<tr>
			<td>fileId</td>
			<td>fileName</td>
			<td>fileType</td>
			<td>updateTime</td>
			<td>userId</td>
			<td>userName</td>
			<td>teamId</td>
			<td>teamName</td>
		</tr>
	</thead>
	<tbody>
		
		
			
				<tr id="folder904" >
				<td>904</td>
				<td>
					<a href ="/FinalProject/ShareFile/Group1-1"  class='trtda'>Group1-1</a>
				</td>
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder905" >
			    
			
				<td>905</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/Group1-2"  class='trtda'>Group1-2</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder921" >
			    
			
				<td>921</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/Group1-3"  class='trtda'>Group1-3</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder906" >
			    
			
				<td>906</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/Group1-3"  class='trtda'>Group1-3</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder925" >
			    
			
				<td>925</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/cvbbdfb"  class='trtda'>cvbbdfb</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder929" >
			    
			
				<td>929</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/igli"  class='trtda'>igli</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder923" >
			    
			
				<td>923</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/rgrag"  class='trtda'>rgrag</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder931" >
			    
			
				<td>931</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/vnmn"  class='trtda'>vnmn</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				<tr id="folder935" >
			    
			
				<td>935</td>
				<td>
				
					<a href ="/FinalProject/ShareFile/中文"  class='trtda'>中文</a>
					
				
				</td>
				
				<td>資料夾</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file939">
			
				<td>939</td>
				<td>
				
					
					hibernate.cfg.xml
				
				</td>
				
				<td>xml</td>
				<td>2016-01-10 11:09:20.11</td>
				<td>100</td>
				<td>Kirin</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file922">
			
				<td>922</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>202</td>
				<td>Group3</td>
				<td></td>
			</tr>
		
		
		
			<tr id="folder${fileList.fileId}" >
				<td>${fileList.fileId}</td>
				<td>
					<a href ="${requestURI}/${fileList.fileName}"  class='trtda'>${fileList.fileName}</a>
				</td>
				<td>${fileList.fileType}</td>
				<td>-</td>
				<td>${fileList.userBean.userID}</td>
				<td>${fileList.userBean.userName}</td>
				<td>${fileList.teamBean.teamId}</td>
				<td>${fileList.teamBean.teamName}</td>
				<td>${session.requestURI}</td>
			</tr>
			
				
			    <tr id="file910">
			
				<td>910</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>100</td>
				<td>Kirin</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file912">
			
				<td>912</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>102</td>
				<td>Wu</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file932">
			
				<td>932</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>202</td>
				<td>Group3</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file938">
			
				<td>938</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>202</td>
				<td>Group3</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file911">
			
				<td>911</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>200</td>
				<td>KukuCoding</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file928">
			
				<td>928</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>202</td>
				<td>Group3</td>
				<td></td>
			</tr>
		
			
				
			    <tr id="file926">
			
				<td>926</td>
				<td>
				
					
					catcat.jpg
				
				</td>
				
				<td>jpg</td>
				<td>2015-12-18 19:31:07.68</td>
				<td>101</td>
				<td>Rita</td>
				<td>202</td>
				<td>Group3</td>
				<td></td>
			</tr>
		
		
	</tbody>
</table>
</div>

<!-- folders -->
<div>
<table class="table">
	<thead>
		<tr>
			<td>FileID</td>
			<td>FileName</td>
			<td>FileLevel</td>
		</tr>
	</thead>
	<tbody>
		
			<tr>
				<td>901</td>
				<td>Group1根目錄</td>
				<td>0</td>
			</tr>
		
	</tbody>
</table>
</div>



<script >
	$(function(){
		
		$("a#insertFile").fancybox();
		$('tr[id^="folder"]').click(showIcon);
		$('tr[id^="file"]').click(showIcon);
		function showIcon(){
			if($(this).hasClass('listBackground')){
	 			 $(this).removeClass('listBackground');
	 			 $('img[id^="icon"]').addClass('iconNotDisplay');
	 		 }else{
// 	 			 if($('tr[id^="f"]').hasClass('listBackground')){
// 	 				$('tr[id^="f"]').removeClass('listBackground');
// 	 			 }
	 			 $(this).addClass('listBackground');
	 			 $('img[id^="icon"]').removeClass('iconNotDisplay');
	 		 }
		 }
		
		$('#iconDelete').click(deletefile);
		function deletefile(){
			 var session = {'list': []};
			 $('tr[class^="listBackground"][id^="f"]').each(function(i, selected){ 
				 session.list.push({ 'fileID' : $(selected).attr('id' ) });
			 });//取得選取的id	
			 console.log(session);
			console.log(JSON.stringify(session));
			$.ajax({
				  'type':'post', 
				  'url':'/FinalProject/shareFile/deletefile',
				  'dataType':'json',  
				  'data':{list:JSON.stringify(session)},
				  'success':function(data){
					  console.log("here is response");
					  console.log(data)
					  $.each(data,function(i,product){
						  console.log(product.fileID);
						  $('#'+product.fileID).remove();
					  })
				  }
			  });//end of $.ajax({ 
		}//end of function deletefile(){
			
		$("a#NewFolder").fancybox();
// 		$('#NewFolder').click(newFolder);
// 		function newFolder(){
			
				
// 		}//end of function newFolder(){
	  console.log($('tr[id^="folder"]>td').text());
// 		$.each($('tr[id^="folder"]>td').value,function(i,product){
// 			  console.log(product);
// 		})
			$('tr[id^="folder"]>td')
	});//end of $(function(){
</script>
</body>
</html>