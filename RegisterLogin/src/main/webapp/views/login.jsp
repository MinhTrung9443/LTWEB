<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/RegisterLogin/login" method="post">
		<h2>Mời đăng nhập</h2>

		<section>
			<label class="input login-input">
				<div>
					<span class="input-group-addon"><i class="fa fauser"></i></span> <input
						type="text" placeholder="Tài khoản" name="username"
						class="form-control"> <br /> <input type="text"
						placeholder="Tài khoản" name="password" class="form-control"><br />
					<input type="submit" value="login">
				</div>
			</label>
		</section>
	</form>
</body>
</html>