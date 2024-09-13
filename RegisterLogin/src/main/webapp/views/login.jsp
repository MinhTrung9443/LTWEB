<%@page import="vn.iotstar.model.User"%>
<%@page import="vn.iotstar.ultis.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
HttpSession session2 = request.getSession(false);
User us = (User)session2.getAttribute("account");
String username = "";
String pass = "";
if (us != null)
{
	username = us.getUserName();
	pass = us.getPassWord();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/RegisterLogin/login" method="post">
		<h2>Mời đăng nhập</h2>
		<c:if test="${alert != null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<section>
			<label class="input login-input">
				<div>
					<input type="text" placeholder="Tài khoản" name="username" value="<%=username%>"> <br /> 
						<input type="text" placeholder="Mật khẩu" name="password" value="<%=pass%>"><br /> 
						<input type="checkbox" name="remember"> <label>Nhớ mật khẩu</label><br /> 
						<a href="/RegisterLogin/views/forgot.jsp">Quên mật khẩu</a> <input
						type="submit" value="login">
				</div>
			</label>
		</section>
	</form>
</body>
</html>