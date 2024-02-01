<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>

<title>회원가입 폼</title>
</head>
<body>
	<div class="container">
		<h2>회원가입 폼</h2>
		<form action="/users/join" method="post" id="join-frm">
			<!-- 아이디 입력 -->
			<div class="form-group">
				<label for="usersId">아이디</label> 
				<input name="usersId" id="usersId" class="form-control" required="required"
					pattern="[A-Za-z0-9]{4,20}" placeholder="아이디 입력">
				<button type="button" class="btn btn-secondary" id="checkMemId">중복확인</button>
			</div>
			<div id="checkId-result">알파벳과 숫자를 포함하여 4~20글자</div>
			
			<!-- 비밀번호 / 비밀번호 확인 -->
			<div class="form-group">
				<label for="usersPw">비밀번호</label>
				<input type="password" name="usersPw" id="usersPw" class="form-control" required="required"
				pattern=".{4,20}" placeholder="비밀번호 입력">
			</div>
			<div class="form-group">
				<label for="usersPw2">비밀번호 확인</label>
				<input type="password" name="usersPw2" id="usersPw2" class="form-control" required="required"
				pattern=".{4,20}" placeholder="비밀번호 확인">
			</div>
			<div id="password-result">4~20글자</div>
			
			<!-- 이름 -->
			<div class="form-group">
				<label for="usersName">이름</label> <input name="usersName" id="usersName" class="form-control" required="required"
					pattern="[가-힣]{2,10}" placeholder="이름 입력">
			</div>
			<div id="name-result">2~10글자</div>
			
			<!-- 성별 -->
			<div class="form-group">
				<label for="usersGender">성별</label>
				<div>
					<label class="radio-inline"> 
						<input type="radio" name="usersGender" id="usersGender" checked value="남자">남자
					</label>
					<label class="radio-inline"> 
						<input type="radio" name="usersGender" id="usersGender" value="여자">여자
					</label>
				</div>
			</div>
			
			<!-- 이메일 -->
			<div class="form-group">
				<label for="usersEmail">이메일</label>
				<input name="usersEmail" id="usersEmail" class="form-control" placeholder="이메일 입력">
			</div>
			
			<!-- 우편번호 -->
			<div class="form-group">
				<label for="usersZip">우편번호</label>
				<div class="input-group">
					<input name="usersZip" id="usersZip" class="form-control" placeholder="우편번호 입력">
					<button type="button" class="btn btn-secondary" id="findZipCodeBtn"
						onclick="openDaumPostcode()">우편번호찾기</button>
				</div>
			</div>
			<!-- 기본주소 / 상세주소 -->
			<div class="form-group">
				<label for="usersAddress">기본주소</label>
				<input name="usersAddress" id="usersAddress" class="form-control" placeholder="기본주소 입력">
			</div>
			<div class="form-group">
				<label for="usersAddress2">상세주소</label>
				<input name="usersAddress2" id="usersAddress2" class="form-control" placeholder="상세주소 입력">
			</div>
			<br>
			<hr>
			<!-- 가입하기 버튼 생성 -->
			<div class="text-end">
				<button type="button" class="btn btn-dark" id="join-btn">가입하기</button>
				<button type="button" class="btn btn-outline-dark" id="joinCancle">취소</button>
			</div>
		</form>
	</div>
	
<!-- Daum 우편번호 서비스 스크립트 추가 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
    // 우편번호 찾기 팝업 호출 함수
    function openDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 선택한 주소를 폼에 반영
                document.getElementById('usersZip').value = data.zonecode;
                document.getElementById('usersAddress').value = data.address;
                document.getElementById('usersAddress2').focus(); // 상세주소로 포커스 이동
            }
        }).open();
    }
</script>
</body>
</html>