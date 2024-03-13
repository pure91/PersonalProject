<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- sessionScope는 내가 컨트롤러에서 sesssion.setAttribute에 키값으로 login을 준걸 토대로
그 login이라는 이름으로 저장된 객체의 정보를 가져오는 EL표현식임  -->
<c:set var="loggedInUser" value="${sessionScope.login}" />

<nav class="navbar navbar-expand bg-body-tertiary ">
	<div class="container-fluid">
		<a class="navbar-brand" href="/main/main">Main</a>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="/board/freeList">자유게시판</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="/notice/noticeList">공지사항(아직 없음)</a></li>
			</ul>
			<!-- 로그인 완료 시 버튼 변경하기-->
			<c:choose>
				<c:when test="${empty loggedInUser}">
					<a href="/users/login"><button class="btn btn-outline-dark"
							type="button" style="margin-right: 10px;">로그인</button></a>
					<a href="/users/join"><button class="btn btn-outline-primary"
							type="button">회원가입</button></a>
				</c:when>
				<c:otherwise>
					<span style="margin-right :10px;">${loggedInUser.usersName} 님</span>
					<a href="/users/logout"><button class="btn btn-outline-danger"
							type="button">로그아웃</button></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>
