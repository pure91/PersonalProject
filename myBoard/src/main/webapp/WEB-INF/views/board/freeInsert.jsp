<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="loggedInUser" value="${sessionScope.login}" />

<title>자유게시판</title>
</head>
<body>
	<h2>자유게시판 게시글 등록</h2>
	<br>
	<form action="/board/freeInsert" method="post" enctype="multipart/form-data">
		<!-- 제목 -->
		<label for="title">제목 : </label>
		<input type="text" id="freeTitle" name="freeTitle" placeholder="제목을 입력하세요" required>
		<br>
		
		<!-- 내용 -->
		<label for="content">내용 : </label>
		<textarea id="freeContents" name="freeContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
		<br>
		
		<!-- 작성자 -->
		<label for="name">작성자 : ${loggedInUser.usersName}</label>
		<input type="hidden" id="name" name="name" value="${loggedInUser.usersName}" readonly>
		<br>
		
		<input type="hidden" id="usersId" value="${usersId}"/>
		
		<!-- 파일 첨부 -->
		<!-- 이 인풋태그의 name속성이 서버로 파일을 전송할때 식별자로 쓰이는데
		컨트롤러서 내가 매개변수를 	
		public List<FilesVO> uploadFile(MultipartFile[] uploadFile, String folder){
		이렇게 줬으니까 name값 맞춰주면 좋음 -->
		<!-- label for는 id값이랑만 맞춰주면 연결됨 -->
		<!-- multiple써줘야 한번에 1장말고 여러장 넣을수있음 -->
		<label for="file">파일 첨부 : </label>
		<input type="file" id="file" name="uploadFile" multiple>
		<br>
		
		<!-- 등록 버튼 -->
		<div>
			<button type="submit" class="btn btn-outline-dark">등록</button>
		</div>
	</form>
</body>
</html>