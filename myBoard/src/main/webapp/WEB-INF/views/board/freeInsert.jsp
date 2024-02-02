<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>자유게시판</title>
</head>
<body>
	<h2>자유게시판 게시글 등록</h2>
	<br>
	<form action="/board/freeInsert" method="post" enctype="multipart/form-data">
		<!-- 제목 -->
		<label for="title">제목 : </label>
		<input type="text" id="freeTitle" name="freeTitle" required>
		<br>
		
		<!-- 내용 -->
		<label for="content">내용 : </label>
		<textarea id="freeContents" name="freeContents" rows="5" required></textarea>
		<br>
		
		<!-- 파일 첨부 -->
		<label for="file">파일 첨부 : </label>
		<input type="file" id="file" name="file">
		<br>
		
		<!-- 등록 버튼 -->
		<div>
			<button type="submit" class="btn btn-outline-dark">등록</button>
		</div>
	</form>
</body>
</html>