<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
.freeBoardTr .freeBoardTh {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}
/* 본문 셀 배경, 글자색 지정 */
.freeBoardTh {
	background-color: gray;
	color: white;
}

/* 리스트 글자 중앙 배치 */
.freeBoardTd {
	text-align: center;
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
	<fieldset>
		<div class="search-board-wrap">
			<form>
				<span class="search-board-select select"> <select
					name="searchType" id="searchType" title="검색 범위">
						<option value="title" selected="">제목</option>
						<option value="content">내용</option>
				</select>
				</span> <span class="search-board-input input"> <input type="text"
					name="keyword" id="keyword" placeholder="검색어 입력" title="검색어 입력" />
					<button type="submit" class="button-search">검색</button>
				</span>
			</form>
		</div>
	</fieldset>
	
	<a href="/board/freeInsert">
		<button type="button" class="btn btn-outline-secondary" id="addBoard">
			<box-icon name='edit'></box-icon>
			글쓰기
		</button>
	</a>
	
	<table id="freeBoardTable">
		<!-- 열의 제목(table) 일반적으로 thead는 th셀을 사용하고 -->
		<thead>
			<tr class="freeBoardTr">
				<th class="freeBoardTh">No.</th>
				<th class="freeBoardTh">제목</th>
				<th class="freeBoardTh">작성자</th>
				<th class="freeBoardTh">작성일자</th>
				<th class="freeBoardTh">조회수</th>
				<th class="freeBoardTh">파일첨부</th>
			</tr>
		</thead>
		<!-- 행의 데이터(tbody) tbody는 td셀을 사용한다.-->
		<tbody>
			<!-- 이곳에는 이제 DB에 있는 값을 가져와서 동적으로 넣어줄것임 -->
			<c:forEach var="list" items="${freeBoardList}">
				<tr>
					<td class="freeBoardTd">${list.freeSeq}</td>
					<td class="freeBoardTd"><a
						href="/board/freeDetail?freeSeq=${list.freeSeq}">
							${list.freeTitle} </a></td>
					<td class="freeBoardTd">${list.usersName}</td>
					<td class="freeBoardTd"><fmt:formatDate
							value="${list.freeWrtDate}" pattern="yyyy-MM-dd" /></td>
					<td class="freeBoardTd">${list.freeCnt}</td>
					<!-- 파일 첨부 여부에 따라 아이콘으로 표시해보자 -->
					<td class="freeBoardTd">
						<c:choose>
							<c:when test="${list.filesSeq != null && list.filesSeq > 0}">
								<box-icon name="file"></box-icon>
							</c:when>
							<c:otherwise>
								Not file
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	${articlePage.getPagingArea()}
</body>
</html>