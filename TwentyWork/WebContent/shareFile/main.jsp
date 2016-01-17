<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShareFile</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
	
	
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
.table{
	text-align: center;
}
 
</style>

</head>
<body>

<div  class='padding'>
	<table ><tr>
		<td class='padding2'>Hello</td>
		<td class='padding2'>userID:${teamUserBean.users.userID}</td>
		<td class='padding2'>userName:${teamUserBean.users.userName}</td>
		<td class='padding2'>groupID:${teamUserBean.team.teamId}</td>
	</tr></table>
	<br>fileUploadMessage:${message}<br>
</div>
<hr>
<!-- path -->
<div   class='padding' id = navPath>
<c:set var="path" value="<%= request.getContextPath() %>" />
	<c:forEach var="fileTree" items="${folders}">
	<c:choose>
		<c:when test="${fileTree.fileLevel ==0}">
			<c:set var="path" value="${path}/ShareFile" />
			<a href = "${path}">ShareFile</a>
		</c:when>
		<c:otherwise>
			<c:set var="path" value="${path}/${fileTree.fileName}" />
			 > <a href = "${path}">${fileTree.fileName}</a>
		</c:otherwise>
	</c:choose>
	</c:forEach>
</div>
<br>
<!-- icon -->
<div class='padding'>
	<a id="insertFile" href="<%= request.getContextPath() %>/shareFile/uploadFile.jsp"><img alt="Upload" title ="Upload" src="<%= request.getContextPath() %>/images/shareFile/fileUploadcloud148.png" /></a>
	<a id="NewFolder"  href="<%= request.getContextPath() %>/shareFile/newFolder.jsp"><img alt ="New Folder" title ="New Folder"  src="<%= request.getContextPath() %>/images/shareFile/newfolder15.png"  /></a>
	<img id="iconDownload" class="iconNotDisplay" alt ="Download" title ="Download"  src="<%= request.getContextPath() %>/images/shareFile/fileDownloadCloud134.png" />
	<img id="iconCopy" class="iconNotDisplay" alt ="Copy" title ="Copy"  src="<%= request.getContextPath() %>/images/shareFile/copyfile19857.png" />
	<img id="iconDelete" class="iconNotDisplay" alt ="Delete" title ="Delete"  src="<%= request.getContextPath() %>/images/shareFile/delete84453783.png" />
	<a id="renameFile"  class="iconNotDisplay" href="<%= request.getContextPath() %>/shareFile/renameFile.jsp"><img  alt ="Rename" title ="Rename"   src="<%= request.getContextPath() %>/images/shareFile/renameedit42.png" /></a>
	<a id="iconMove"  class="iconNotDisplay" href="<%= request.getContextPath() %>/shareFile/folderTree.jsp"><img  alt ="Move" title ="Move"   src="<%= request.getContextPath() %>/images/shareFile/movesend2.png" /></a>
</div>
<table>
<c:forEach var="folderTreeList" items="${folderTree}">
<tr>
				<td>${folderTreeList.fileId}</td>
				<td>${folderTreeList.fileName}</td>
				<td>${folderTreeList.fileLevel}</td>
			</tr>
</c:forEach></table>
<br>
<!-- file list -->
<div  id='fileListxxxxxx' class='padding'>
<table class="table">
	<thead>
		<tr>
			<td style="display:none">fileId</td>
			<td>檔案名稱</td>
			<td style="display:none">fileType</td>
			<td>上傳時間</td>
			<td style="display:none">userId</td>
			<td>上傳人員</td>
			<td style="display:none">teamId</td>
			<td style="display:none">teamName</td>
		</tr>
	</thead>
	<tbody id = "fileList">
		<c:if test="${! empty fileList}">
		<c:forEach var="fileList" items="${fileList}">
			<c:choose>
				<c:when test="${fileList.fileType == '資料夾'}">
					<tr id="folder${fileList.fileId}" >
					<td style="display:none">${fileList.fileId}</td>
					<td><a href ="${requestURI}/${fileList.fileName}"  class='fileName'>${fileList.fileName}</a></td>
					<td style="display:none">${fileList.fileType}</td>
					<td>-</td>
				</c:when>
			    <c:otherwise>
			    	<tr id="file${fileList.fileId}">
			    	<td style="display:none">${fileList.fileId}</td>
			    	<td  class='fileName'>${fileList.fileName}</td>
			    	<td style="display:none">${fileList.fileType}</td>
			    	
			    	<c:set var="string1" value='${fileList.updateTime}'/>
					<c:set var="updateTime" value="${fn:substring(string1, 0, 16)}" />
			    	<td>${updateTime}</td>
			    </c:otherwise>
			</c:choose>
				<td style="display:none">${fileList.userBean.userID}</td>
				<td>${fileList.userBean.userName}</td>
				<td style="display:none">${fileList.teamBean.teamId}</td>
				<td style="display:none">${fileList.teamBean.teamName}</td>
			</tr>
		</c:forEach>
		</c:if>
	</tbody>
</table>
</div>
<!-- folders -->
<div>
<table class="table" style="display:none" id="folders">
	<thead>
		<tr>
			<td>FileID</td>
			<td>FileName</td>
			<td>FileLevel</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="fileTree" items="${folders}">
			<tr>
				<td class='fileId'>${fileTree.fileId}</td>
				<td>${fileTree.fileName}</td>
				<td>${fileTree.fileLevel}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<iframe id="downloadFrame" style="display:none"></iframe>


<script >
	$(function(){
		$("#insertFile").fancybox();
		$("#NewFolder").fancybox();
		$("#renameFile").fancybox();
		$("#iconMove").fancybox();
		$('tr[id^="f"]').click(listBackGround);
			
	
	
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
				  'url':'<%= request.getContextPath() %>/ShareFileServlet/deletefile',
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
			
		
		
		$('#iconDownload').click(function(){
			$('tr[class^="listBackground"][id^="f"]').each(function(i, selected){ 
				 console.log( $(selected).attr('id' ) );
				 var iframe = document.getElementById("downloadFrame");
				 iframe .src = "<%= request.getContextPath() %>/ShareFileServlet/downloadfile?fileID="+$(selected).attr('id' );
			 });//取得選取的id
		});//end of $('#iconDownload').click(function(){
		
		$('#iconRename').click(function(){
			var fildID = $('tr[class^="listBackground"]>td:eq(0)').text(); 
// 			console.log('${fileList}')
			$.ajax({
				  'type':'get', 
				  'url':'<%= request.getContextPath() %>/ShareFileServlet/renamefile',
				  'dataType':'json',  
				  'data':{fileId:fildID},
				  'success':function(data){
					  console.log("here is response of #iconRename");
					  console.log(data)
					  $.each(data,function(i,product){
						  console.log(product.fileID);
					  })
				  }
			  });//end of $.ajax({ 
		}); //end of $('#iconRename').click(function(){ 
		
		});//end of $(function(){
			
			
			
			
	function listBackGround(){
// 		console.log("listBackGround")
// 		console.log($(this).hasClass('listBackground'))
// 		console.log($(this));
		if($(this).hasClass('listBackground')){
 			 $(this).removeClass('listBackground');
 			icondisplay();
 		 }else{
// 			 if($('tr[id^="f"]').hasClass('listBackground')){
// 				$('tr[id^="f"]').removeClass('listBackground');}//單選
 			 $(this).addClass('listBackground');
 			icondisplay();
 		 }
 	}//end of  function listBackGround(){
 		
//		#iconDelete | #iconCopy | #iconMove | #iconRename | #iconDownload
//	class="iconNotDisplay"
	function icondisplay(){
		if($('tr[class="listBackground"]').length==0){
//				console.log('length=0')
			$('img[id^=icon]').addClass('iconNotDisplay');
			$('#renameFile').addClass('iconNotDisplay');
		}else if($('tr[class="listBackground"]').length==1){
//				console.log('length=1')
			$('#iconDelete').removeClass('iconNotDisplay');
			$('#iconCopy').removeClass('iconNotDisplay');
			$('#iconMove').removeClass('iconNotDisplay');
			$('#renameFile').removeClass('iconNotDisplay');
			if($('tr[id^=folder][class="listBackground"]').length>=1){
				$('#iconDownload').addClass('iconNotDisplay');
			}else{
				$('#iconDownload').removeClass('iconNotDisplay');
			}
		}else{
//				console.log('length>1')
			$('#iconDelete').removeClass('iconNotDisplay');
			$('#iconCopy').removeClass('iconNotDisplay');
			$('#iconMove').removeClass('iconNotDisplay');
			$('#renameFile').addClass('iconNotDisplay');
			$('#iconDownload').addClass('iconNotDisplay');
		}
	}; //end of function icondisplay(){
</script>
</body>
</html>