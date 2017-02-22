<%@include file="/WEB-INF/common_include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloWord Dynamic</title>
</head>
<body>
	<h1>User</h1>
	<p style="font-size: 14px; font-family: Microsoft YaHei;">
		Id: ${user.id} <br /> Name: ${user.name}
	</p>
</body>
</html>