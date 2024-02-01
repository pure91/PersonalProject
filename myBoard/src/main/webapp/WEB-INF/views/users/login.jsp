<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<title>로그인 폼</title>
</head>

<body>
	<div class="container">
		<h2>로그인 폼</h2>
		<form action="/users/login" method="post">
			<div class="form-group">
				<!-- 라벨의 for랑 인풋의 id 맞춰줘야함 자꾸 까먹네 -->
				<label for="usersId">아이디</label> <input type="text" name="usersId"
					id="usersId" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="usersPw">비밀번호</label> <input type="password"
					name="usersPw" id="usersPw" class="form-control" required>
			</div>
			<button type="submit">로그인</button>
			<button type="button" onclick="history.back()">취소</button>
		</form>
	</div>
</body>
</html>