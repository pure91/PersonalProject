<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<style>
	/* 	본문 테이블 크기, 콜랩스로 경계 병합  */
	#freeBoardTable {
		width: 100%;
		border-collapse: collapse;
		magin-top: 20px;
	}
	
	/* 본문 헤더 테두리, 글자 중앙 배치 */
	.freeBoardTr .freeBoardTh{
		border: 1px solid #ddd;
		padding: 8px;
		text-align: center;
	}
	/* 본문 셀 배경, 글자색 지정 */
	.freeBoardTh{
		background-color: gray;
		color: white;
	}
	
	/* 글쓰기 버튼 */
	#addBoard {
		float: right;
		margin-bottom: 10px;
	}
</style>

<title>자유게시판</title>
</head>
<body>
	<h2>자유게시판 목록</h2>
<!-- 	<form action="/board/freeInsert" method="post"> -->
	<a href="/board/freeInsert">
		<button type="button" class="btn btn-outline-secondary" id="addBoard">글쓰기</button>
	</a>
<!-- 	</form> -->
	<table id="freeBoardTable">
		<!-- 열의 제목(table) 일반적으로 thead는 th셀을 사용하고 -->
		<thead>
			<tr class="freeBoardTr">
				<th class="freeBoardTh">No.</th>
				<th class="freeBoardTh">제목</th>
				<th class="freeBoardTh">작성자</th>
				<th class="freeBoardTh">작성일자</th>
				<th class="freeBoardTh">조회수</th>
			</tr>
		</thead>
		<!-- 행의 데이터(tbody) tbody는 td셀을 사용한다.-->
		<tbody>
			<!-- 이곳에는 이제 DB에 있는 값을 가져와서 동적으로 넣어줄것임 -->
			<c:forEach var="list" items="${freeBoardList}">
				<tr>
					<td>${list.freeSeq}</td>
					<td>${list.freeTitle}</td>
					<td>${list.usersName}</td>
					<td>${list.freeWrtDate}</td>
					<td>${list.freeCnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>