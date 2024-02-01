<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/main/main">Main</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
           <a class="nav-link" href="/board/freeList">자유게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/notice/noticeList">공지사항(아직 없음)</a>
        </li>
      </ul>
      
      <form class="d-flex" role="search">
        <a href="/users/login"><button class="btn btn-outline-dark" type="button" style="margin-right:10px;">로그인</button></a>
        <a href="/users/join"><button class="btn btn-outline-primary" type="button">회원가입</button></a>
      </form>
    </div>
  </div>
</nav>
