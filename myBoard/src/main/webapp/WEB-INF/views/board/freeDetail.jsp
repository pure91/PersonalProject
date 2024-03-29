<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<style>
	/* 사진 한장마다 한칸씩 내리기*/
	.image-grid {
		display: flex;
		flex-direction: column;
	}
	.image-grid img {
		max-width: 300px;
		max-height: 300px;
	}
	
	#likeAndHate {
		float : right;
	}
</style>
<head>
<!-- 로그인정보 가져오기 -->
<c:set var="loggedInUser" value="${sessionScope.login}" />

<title>게시판 상세</title>
</head>
<body>
	<!-- 좋아요, 싫어요 -->
	<div id="likeAndHate">
		<button type="button" class="btn btn-warning" id="like_btn" onclick="updateLike(); return false;">좋아요</button>
		<button type="button" class="btn btn-danger" id="hate_btn">싫어요</button>
 	</div>
 	
 	<!-- 상세 내용 -->
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
	
	<script>
		var freeSeq = ${boardVO[0].freeSeq};
		var usersId = "${loggedInUser.usersId}"; // jsp는 상관없어도 javaScript에서는 $달러까지 ""문자열로 감싸줘야함 why?안그러면 그냥 변수명으로 착각함
		console.log("usersId : ", usersId);
		var usersName = "${boardVO[0].usersName}";
		
		function updateLike(){
			$.ajax({
				url : "/like/board/updateLike",
				type : "POST",
				dataType : "json",
				data : {
					"freeSeq" : freeSeq,
					"usersId" : usersId,
					"usersName" : usersName,
				},
				error : function(){
					alert("통신 에러");
				},
				success : function(likeCheck) {
					if(likeCheck == 0){
						alert("좋아요 완료");
						location.reload();
					}
					else if (likeCheck == 1){
						alert("싫어요 완료");
						location.reload();
					}
				}
			});
		}
	</script>
</html>