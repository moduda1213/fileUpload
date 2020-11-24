<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="${pageContext.request.contextPath }/login">
		<div>
			ID : 
			<input type="text" name = "userId" value="${user.userId}">
		</div>
		<div>
			PW : 
			<input type="password" name="userPw">
		</div>
		<button type="submit">로그인</button>
	</form>
</body>
</html>