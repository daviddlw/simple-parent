<%@include file="/WEB-INF/common_include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<!-- 必须放在head里生效作为基链接 -->
<script type="text/javascript" src="js/vue.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vue HelloWorld</title>
</head>
<body>
	<h2>Vue HelloWorld</h2>
	<div>
		vue el value: <span id="vueVal">{{message}},{{alert}}</span>
	</div>
	<div>
		vue html: <span id="vueHtml"><span v-html="html_c"></span></span>
	</div>
</body>
<script type="text/javascript">
	new Vue({
		el : '#vueVal',
		data : {
			message : 'david, welcome to vue world!',
			alert : '你好vue'
		}
	});

	new Vue({
		el : '#vueHtml',
		data : {
			html_c : '<strong><b>html content</b></strong>'
		}
	});
</script>
</html>