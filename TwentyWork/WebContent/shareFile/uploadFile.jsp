<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.uploadFileSubmit {
	padding-top: 10px;
}
</style>
</head>
<body>
	<div class="fancy">
		<h4>Choose File to Upload in Server</h4>
		<form action="<%= request.getContextPath() %>/ShareFileServlet/fileUpload" method="post" enctype="multipart/form-data">
			
			<input type="file" name="file" multiple/>
			<div class ="uploadFileSubmit">
			<input type="submit" value="upload" />
			</div>
		</form>
		
	</div>
</body>
</html>