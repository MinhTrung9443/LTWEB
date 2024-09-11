<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/RegisterLogin/register" method="post">
		<h2>Tạo tài khoản mới</h2>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<label>email</label><br /> 
					<input type="text" name="email"><br /> 
					<label>Tài khoản</label><br /> 
					<input type="text" name="username"> <br /> 
					<label>Họ và tên</label><br /> 
					<input type="text" name="fullname"> <br /> 
					<label>Mật khuẩu</label><br /> 
					<input type="text" name="password"> <br /> 
					<label>Số điện thoại</label><br /> 
					<input type="text" name="phone"> <br />
					<input type="submit" value="register">
				</div>
			</label>
		</section>
	</form>
</body>
</html>