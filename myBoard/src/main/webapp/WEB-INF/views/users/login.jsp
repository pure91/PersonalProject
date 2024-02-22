<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
.subject {
	font-weight: bold;
}
</style>
<title>로그인 폼</title>
</head>

<body>
	<div class="container">
		<h2>로그인 폼</h2>
		<br>
		<form action="/users/login" method="post" id="login-frm">
			<div class="form-group">
				<!-- 라벨for랑 인풋id 맞춰줘야함.. 자꾸 까먹네 -->
				<label for="usersId" class="subject">아이디</label> <input type="text"
					name="usersId" id="usersId" class="form-control" required>
			</div>
			<br>

			<div class="form-group">
				<label for="usersPw" class="subject">비밀번호</label> <input
					type="password" name="usersPw" id="usersPw" class="form-control"
					required autocomplete="off">
			</div>

			<!-- 로그인 아이디/비밀번호 체크 -->
			<div id="check-result" style="color: red;">${loginError}</div>
			<br>
			<button type="submit" class="btn btn-outline-dark"
				style="width: -webkit-fill-available;">로그인</button>
			<button type="button" id="auto-login">테스트용 자동 로그인</button>
		</form>
	</div>

	<!-- 자동 로그인 버튼 만들어놓기 매번 로그인 귀찮아서-->
	<script>
		$("#auto-login").on("click", function() {
			$("input[name=usersId]").val("test1");
			$("input[name=usersPw]").val("test123#");
			$("#login-frm").submit();
		});
	</script>
</body>
</html>