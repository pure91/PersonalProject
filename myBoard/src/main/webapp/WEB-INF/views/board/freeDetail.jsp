<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<style>
	.image-grid {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.image-grid img {
		max-width: 300px;
		max-height: 300px;
	}
</style>
<head>
<!-- 로그인정보 가져오기 -->
<c:set var="loggedInUser" value="${sessionScope.login}" />

<title>게시판 상세</title>
</head>
<body>
	<table>
		<tr>
			<th>회원ID</th>
			<td>${boardVO[0].usersId}
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardVO[0].usersName}
		</tr>
		<tr>
			<th>제목</th>
			<td>${boardVO[0].freeTitle}
		</tr>
		<tr>
			<th>내용</th>
			<td>${boardVO[0].freeContents}
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${boardVO[0].freeWrtDate}" pattern="yyyy-MM-dd"/>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${boardVO[0].freeCnt}
		</tr>
		<tr>
			<th>사진</th>
			<c:forEach var="board" items="${boardVO}">
				 <td class="image-grid">
			        <c:if test="${not empty board.filesStrgName}">
			            <img src="/resources/upload/freeBoard/${board.filesStrgName}"/>
			        </c:if>
			        <c:if test="${empty board.filesStrgName}">
			            사진 없음
			        </c:if>
	    		</td>
    		</c:forEach>
		</tr>
	</table>
	<!-- 내가 작성한 글만 수정, 삭제버튼 보이게하기 -->
	<c:choose>
		<c:when test="${loggedInUser.usersId eq boardVO[0].usersId}">
			<button class="btn btn-outline-dark" onclick="listFn()">목록</button>
			<button class="btn btn-outline-success" onclick="updateFn()">수정</button>
			<button class="btn btn-outline-warning" onclick="deleteFn()">삭제</button>
		</c:when>
		<c:otherwise>
			<button class="btn btn-outline-dark" onclick="listFn()">목록</button>
		</c:otherwise>
	</c:choose>

</body>

	<script>
		// 목록 함수
		function listFn(){
			location.href = "/board/freeList";
		}
		// 수정 함수
		function updateFn(){
			const no = ${boardVO[0].freeSeq};
			location.href = "/board/freeUpdate?freeSeq=" + no;
		}
		// 삭제 함수
		function deleteFn(){
			const no = ${boardVO[0].freeSeq};
			location.href = "/board/freeDelete?freeSeq=" + no;
		}
	</script>
</html>