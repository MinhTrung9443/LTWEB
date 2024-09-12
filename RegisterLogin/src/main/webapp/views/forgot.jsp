<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/RegisterLogin/forgot" method="post">
		<h2>Đổi mật khẩu.</h2>
		<c:if test="${alert != null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<input type="text" placeholder="Tên đăng nhập" name="username"><br>
		<input type="text" placeholder="Mật khẩu mới" name="password"><br>
		<input type="submit" value="Đổi mật khẩu">
	</form>
</body>
</html>