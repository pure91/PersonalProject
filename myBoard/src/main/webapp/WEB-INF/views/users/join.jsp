<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.subject {
	font-weight: bold;
}
</style>
<title>회원가입 폼</title>
</head>
<body>
	<div class="container">
		<h2>회원가입 폼</h2>
		<br>
		<form action="/users/join" method="post" id="join-frm">
			<!-- 아이디 입력 -->
			<div class="form-group">
				<label for="usersId" class="subject">아이디</label>
				<!-- input-group덕에 버튼이 옆에 붙는듯 -->
				<div class="input-group">
					<input name="usersId" id="usersId" class="form-control"
						required="required" placeholder="아이디 입력">
					<button type="button" class="btn btn-secondary" id="checkMemId">중복확인</button>
				</div>
			</div>
			<div id="checkId-result">알파벳과 숫자를 포함하여 4~20글자를 입력해주세요</div>
			<br>

			<!-- 비밀번호 / 비밀번호 확인(얘네는 autocomplete="off" 꼭해야함 비밀번호라 보안상 필요함(콘솔에 떴음)-->
			<!-- col 먹이려면 row로 먼저 감싸야하는듯 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersPw" class="subject">비밀번호</label> <input
						type="password" name="usersPw" id="usersPw" class="form-control"
						required="required" placeholder="비밀번호 입력" autocomplete="off">
				</div>
				<br>
				<div class="form-group col-6">
					<label for="usersPw2" class="subject">비밀번호 확인</label> <input
						type="password" name="usersPw2" id="usersPw2" class="form-control"
						required="required" placeholder="비밀번호 확인" autocomplete="off">
				</div>
			</div>
			<div id="password-result">영문, 숫자, 특수문자 중 2가지 이상을 조합하고, 8자리 이상을 입력해주세요</div>
			<br>

			<!-- 이름/성별 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersName" class="subject">이름</label> <input
						name="usersName" id="usersName" class="form-control"
						required="required" placeholder="이름 입력">
					<div id="name-result">이름은 한글 2~6글자로 입력해주세요.</div>
				</div>

				<div class="form-group col-6">
					<label class="subject">성별</label>
					<div>
						<label for="male" class="radio-inline"> <input type="radio"
							name="usersGender" id="male" checked value="남자">남자
						</label> 
						<label for="female"class="radio-inline"> <input type="radio"
							name="usersGender" id="female" value="여자">여자
						</label>
					</div>
				</div>
			</div>
			<br>

			<!-- 이메일/연락처 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersEmail" class="subject">이메일</label> <input
						name="usersEmail" id="usersEmail" class="form-control"
						placeholder="이메일 입력">
				</div>
				<div class="form-group col-6">
					<label for="usersTel" class="subject">연락처</label> <input type="tel"
						name="usersTel" id="usersTel" class="form-control"
						placeholder="연락처 입력(- 제외)">
				</div>
			</div>
			<br>

			<!-- 우편번호 -->
			<div class="form-group">
				<label for="usersZip" class="subject">우편번호</label>
				<div class="input-group">
					<input name="usersZip" id="usersZip" class="form-control"
						placeholder="우편번호 입력">
					<button type="button" class="btn btn-secondary" id="findZipCodeBtn"
						onclick="openDaumPostcode()">우편번호찾기</button>
				</div>
			</div>
			<br>

			<!-- 기본주소 / 상세주소 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersAddress" class="subject">기본주소</label> <input
						name="usersAddress" id="usersAddress" class="form-control"
						placeholder="기본주소 입력">
				</div>
				<div class="form-group col-6">
					<label for="usersAddress2" class="subject">상세주소</label> <input
						name="usersAddress2" id="usersAddress2" class="form-control"
						placeholder="상세주소 입력">
				</div>
			</div>
			<br>

			<!-- 파일 업로드 -->
			<div class="form-group">
				<label for="filesSeq " class="subject">프로필 사진</label> <input
					name="filesSeq " id="filesSeq " class="form-control" type="file">
			</div>

			<br>
			<hr>
			<!-- 가입하기 버튼 생성 -->
			<div class="text-end">
				<button type="submit" class="btn btn-outline-primary" id="join-btn">가입하기</button>
				<button type="button" class="btn btn-outline-danger" id="joinCancle"
					onclick="history.back()">취소</button>
			</div>
		</form>
	</div>

	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->
	<!-- ============================================================== 구분선 ============================================================== -->

	<!-- Daum 우편번호 서비스 스크립트 추가 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script>
		// 우편번호 찾기 팝업 호출 함수
		function openDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 선택한 주소를 폼에 반영
							document.getElementById('usersZip').value = data.zonecode;
							document.getElementById('usersAddress').value = data.address;
							document.getElementById('usersAddress2').focus(); // 상세주소로 포커스 이동
						}
					}).open();
		}
	</script>

	<!-- 유효성 처리 -->
	<script>
		// 아이디 유효성 + 중복체크
		$(function() {
			// 사용자가 화면에서 인풋값에 입력하는 아이디가 usersIdValue에 담김
			let usersIdValue = $("#usersId");
			console.log(usersIdValue.val());

			// 중복확인을 누르면 담긴 아이디의 값이 usersId 키 값에 들어감
			$("#checkMemId").on("click", function() {
				let usersId = {
					"usersId" : usersIdValue.val()
				};

				let checkId_result = $("#checkId-result");
				console.log(usersId);

				$.ajax({
					url : "/users/idCheck",
					type : "post",
					data : JSON.stringify(usersId),
					dataType : "json",
					contentType : "application/json;charset=UTF-8",
					xhrFields : { //서버간 인증 쿠키를 주고 받으려면 true로 써야한다고함
						withCredentials : true
					},
				}).done(function(rst) {
					console.log(rst);
					if (rst.rst === "ok") {
						checkId_result.css("color", "green").html(rst.msg);
						submitFlag = true;
					} else {
						checkId_result.css("color", "red").html(rst.msg);
						submitFlag = false; // 서브밋플래그 false로 하면 폼 제출을 거부하는거임.
					}
				}).fail(function(xhr) {
					console.log(xhr);
				});
			});
		})
		
		// 이름 유효성 검증 (DB에서 값 가져오는건 아님)
		$("#usersName").on("change", function(){
			const regExp = /^[가-힣]{2,6}$/;
			let usersNameValue = $(this).val();
			let usersNameResult = $("#name-result");
			
			if(usersNameValue.match(regExp) === null){ // match() 메서드는 문자열이 정규식과 매치되는 부분을 검색함
				usersNameResult.css("color", "red").html("자음/모음이 아닌 한글로 이루어진 2~6글자의 이름만 가능합니다.");
				submitFlag = false;
			} else {
		        usersNameResult.css("color", "green").html("올바른 이름 형식입니다.");
				submitFlag = true;
			}
		})
		
		// 비밀번호 유효성 검증(역시 DB에서 값 가져올건 아님)
		$("#usersPw").on("input", function(){ // 사용자가 패스워드 인풋에 값을 입력하면
			const regExp = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,20}$/;
			if ($("#usersPw").val().match(regExp) === null){
				$("#password-result").css("color", "red").html("영문, 숫자, 특수문자 중 2가지 이상 조합하여 8자리 이상");
				submitFlag = false;
			} else {
				$("#password-result").css("color", "red").html("");
			}
		});
		$("#usersPw2").on("input", function(){
			if($(this).val() !== $("#usersPw").val()){
				$("#password-result").css("color", "red").html("비밀번호가 일치하지 않습니다.");
				submitFlag = false;
			} else {
				$("#password-result").css("color", "green").html("비밀번호가 일치합니다.");
				submitFlag = true;
			}
		});
	</script>
</body>
</html>