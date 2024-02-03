<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>업데이트 폼</title>
</head>
<body>
	<form action="/board/freeUpdate" method="post" name="updateForm">
		<input type="hidden" name="freeSeq" value="${board.freeSeq}" readonly>
		<label for="name">작성자 : </label>
		<input type="text" name="usersName" value="${board.usersName}" readonly>
		<br>
		<label for="title">제목 : </label>
		<input type="text" name="freeTitle" value="${board.freeTitle}">
		<br>
		<label for="content">내용 : </label>
		<textarea id="freeContents" name="freeContents" cols="30" rows="10">${board.freeContents}</textarea>
		<br>
		<button class="btn btn-outline-dark" type="submit">수정</button>
	</form>
</body>
</html>