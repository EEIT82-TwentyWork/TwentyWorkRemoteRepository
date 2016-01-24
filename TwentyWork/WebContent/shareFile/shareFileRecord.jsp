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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main/Main.css">	

	
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
.mainPageContext{
	float:left;
 	background-color:white; 
 	width: 1000px;
	height:100%;
	margin-top:20px;
	box-shadow: 0 5px 15px 1px rgba(0, 0, 0, 0.6), 0 0 200px 1px
 rgba(255, 255, 255, 0.5);
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
#logOut{
	float:right;
	padding-right:20px;
	
}
.leftBarA {
    color: white;
    padding-left:12px;
}

.folderImg{
	padding-left:12px;
}
</style>

</head>
	<body onload="initializeCanvas()">
		<div class="container-fluid">
			<section>
			<div id="page-top">
<!-- 				<a href="/TwentyWork/main/workHome/main.jsp"><img id="logoMainID" src="../../images/index/Logo-main.png" border="0" title="TwentyWork HomePage"></a> -->
				<div class="logocontainer">
					<div class="logo">
						<a href="/TwentyWork/main/workHome/main.jsp">
							<span class="left">< </span>TwentyWork<span class="right"> /></span>
						</a>
						<span id="top-userinfo">
								<span>Hi！</span>
								<span id="top-name" ><%=session.getAttribute("userName") %></span>
								 | 
								<a href="#">Setting	</a>
								 | 
								<a id="logoutid" href="<%=request.getContextPath()%>">Logout</a>
						</span>
					</div>
					
				</div>
				
<%-- 				<h3>Welcome <%=session.getAttribute("userName") %></h3> --%>
				
				
			</div>
<!-- layout----E1 begin---------------------------------------------------->				
				<div id="page-left">
					<div id="accordian">
						<ul>

							<li>
								<h3><a href = "<%=request.getContextPath() %>/main/workHome/main.jsp">首頁</a></h3>
							</li>

							<li>
								<h3><span class="icon-dashboard"></span>Dashboard</h3>
								<ul>
									<li><a href="#">Reports</a></li>
									<li><a href="#">Reports</a></li>
									<li><a href="#">Search</a></li>
									<li><a href="#">Graphs</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<!-- we will keep this LI open by default -->
							<li class="active">
								<h3><span class="icon-tasks"></span>Tasks</h3>
								<ul></ul>
							</li>
							<li> 
						       	 <h3 id="calendarh3id" ><span class="icon-calendar"></span>Calendar</h3> 
						       	 <ul id="calendarulid"> 
						         <li><a href="../calendar/cal_opt.jsp">個人行事曆</a></li> 
					        </ul> 
					       </li>
							<li>
								<h3><span class="icon-heart"></span>Favourites</h3>
								<ul>
									<li><a href="#">Global favs</a></li>
									<li><a href="#">My favs</a></li>
									<li><a href="#">Team favs</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
							<li>
								<h3><span class="icon-chat"></span>Chat List</h3>
								<ul id="chatName">
								</ul>
							</li>
							<li>
								<h3><a href = "<%=request.getContextPath() %>/ShareFile">檔案分享</a></h3>
							</li>
							<li>
								<h3><span class="icon-close"></span>close</h3>
							</li>
						</ul>
					</div>
				</div>
<!-- layout----E1 end---------------------------------------------------->	
<!---------------- main page code ---------------------------------->
<div id="page-left">
<div class = 'mainPageContext'>
<br>
<!-- userInfo -->
<table  class="iconNotDisplay"><tr>
		<td class='padding2'>Hello</td>
		<td>userID:</td>
		<td class='padding2' id="userID">${LoginOK.userID}</td>
		<td>userName:</td>
		<td class='padding2'>${LoginOK.userName}</td>
		<td>teamID:</td>
		<td class='padding2' id="teamID">${teamBean.teamId}</td>
		<td>teamName:</td>
		<td class='padding2' id="teamID">${teamBean.teamName}</td>
</tr></table>


<!-- path -->
<div   class='padding' id = navPath>
<c:set var="path" value="<%= request.getContextPath() %>" />
			<c:set var="path" value="${path}/ShareFile" />
			<a href = "${path}">ShareFile</a>
</div>
<br>
<!-- icon -->
<div class='padding'>
	<img id="iconDownload" class="iconNotDisplay" alt ="Download" title ="Download"  src="<%= request.getContextPath() %>/images/shareFile/fileDownloadCloud134.png" />
	<div style='float:right'>
		<a id="shareRecord"  href="<%= request.getContextPath() %>/ShareFileServlet/shareRecord"><img alt ="ShareFileRecord" title ="ShareFileRecord"  src="<%= request.getContextPath() %>/images/shareFile/mail3.png"  /></a>
	</div>
</div>

<div class='padding'>
<table class='table' id='shareNotifyList'>
<thead>
	<tr>
		<td  style="display:none">收件者</td>
		<td>寄件者</td>
		<td>檔名</td>
		<td>發送時間</td>
	</tr>
</thead>
<tbody>
<c:forEach var="list" items="${shareFileRecordList}">
	<c:choose>
		<c:when test="${list.file.fileType == '資料夾'}">
			<tr id="folder${list.file.fileId}">
				<td  style="display:none">${list.users.userName}</td>
				<td>${list.sendUser.userName}</td>
				<td><a>${list.file.fileName}</a></td>

				<c:set var="string1" value='${list.shareTime}'/>
				<c:set var="updateTime" value="${fn:substring(string1, 0, 16)}" />
				<td>${updateTime}</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr id="file${list.file.fileId}">
				<td  style="display:none">${list.users.userName}</td>
				<td>${list.sendUser.userName}</td>
				<td>${list.file.fileName}<a><img src="<%= request.getContextPath() %>/images/shareFile/open131.png"></a></td>

				<c:set var="string1" value='${list.shareTime}'/>
				<c:set var="updateTime" value="${fn:substring(string1, 0, 16)}" />
				<td>${updateTime}</td>
			</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tbody>
</table>
<br>
</div>
<iframe id="downloadFrame" style="display:none"></iframe>
<!---------------- main page code end---------------------------------->
			</section>
		</div>

<script >
$(function(){
	$('table#shareNotifyList>tbody>tr>td>a>img').addClass('folderImg');
	
	
	$('table#shareNotifyList>tbody>tr').click(listBackGround);	
	function listBackGround(){
		if($(this).hasClass('listBackground')){
			$(this).removeClass('listBackground');
			icondisplay();
		}else{
			if($('table#shareNotifyList>tbody>tr').hasClass('listBackground')){
				$('table#shareNotifyList>tbody>tr').removeClass('listBackground');
			}//單選
			$(this).addClass('listBackground');
			icondisplay();
			
		}
	}//end of  function listBackGround(){
		
		
	function icondisplay(){
		if($('tr[class="listBackground"]').length==1){
			if($('tr[id^=folder][class="listBackground"]').length>=1){
				$('#iconDownload').addClass('iconNotDisplay');
			}else{
				$('#iconDownload').removeClass('iconNotDisplay');
			}
		}
	}; //end of function icondisplay(){

	$('#iconDownload').click(function(){
		$('tr[class^="listBackground"][id^="f"]').each(function(i, selected){ 
			 console.log( $(selected).attr('id' ) );
			 var iframe = document.getElementById("downloadFrame");
			 iframe .src = "<%= request.getContextPath() %>/ShareFileServlet/downloadfile?fileID="+$(selected).attr('id' );
		 });//取得選取的id
		$('table#shareNotifyList>tbody>tr').removeClass('listBackground'); 
		icondisplay();
	});//end of $('#iconDownload').click(function(){

	 
	var fileIdList = {'fileID': []};
	 $('table#shareNotifyList>tbody>tr[id^="f"]').each(function(i, selected){ 
		 fileIdList.fileID.push( $(selected).attr('id' ) );
	 });//取得選取的id	
	console.log(fileIdList);
	console.log(JSON.stringify(fileIdList));
	var jsonData = JSON.stringify(fileIdList);
	$.ajax({
		  'type':'get', 
		  'url':'<%= request.getContextPath() %>/ShareFileServlet/getHref',
		  'dataType':'json',  
		  'data':{data:JSON.stringify(fileIdList)},
		  'success':function(data){
			  console.log("here is response");
			  console.log(data)
			  $('table#shareNotifyList>tbody>tr[id^="folder"]').each(function(i, selected){ 
		 			 var ttt=$(selected).attr('id' );
					 var href = '<%= request.getContextPath() %>/ShareFile'+data[ttt];
// 					 console.log(href)
					 $('table#shareNotifyList>tbody>tr[id^="'+$(selected).attr('id' )+'"]>td>a').attr('href',href);
	 		  });	//end of $('table#shareNotifyList>tbody>tr[id^="folder"]').each(function(i, selected){ 
	 		 $('table#shareNotifyList>tbody>tr[id^="file"]').each(function(i, selected){ 
	 			 var ttt=$(selected).attr('id' );
	 			 var href = '<%= request.getContextPath() %>/ShareFile'+data[ttt];
// 	 			 console.log(ttt);
// 	 			 console.log(href);
	 			$('table#shareNotifyList>tbody>tr[id^="'+$(selected).attr('id' )+'"]>td>a').attr('href',href)
	 		 })
		  }//end of 'success':function(data){
	  });//end of $.ajax({ 
	
});//end of $(function(){
</script>

</body>
</html>