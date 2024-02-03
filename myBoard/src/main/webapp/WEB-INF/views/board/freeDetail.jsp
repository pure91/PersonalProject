<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>게시판 상세</title>
</head>
<body>
	<table>
		<tr>
			<th>회원ID</th>
			<td>${board.usersId}
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.usersName}
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.freeTitle}
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.freeContents}
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${board.freeWrtDate}" pattern="yyyy-MM-dd"/>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.freeCnt}
		</tr>
	</table>
	<button onclick="listFn()">목록</button>
	<button onclick="updateFn()">수정</button>
	<button onclick="deleteFn()">삭제</button>
</body>

	<script>
		// 목록 함수
		function listFn(){
			location.href = "/board/freeList";
		}
		// 수정 함수
		function updateFn(){
			const no = ${board.freeSeq};
			location.href = "/board/freeUpdate?freeSeq=" + no;
		}
		// 삭제 함수
		function deleteFn(){
			const no = ${board.freeSeq};
			location.href = "/board/freeDelete?freeSeq=" + no;
		}
	</script>
</html>