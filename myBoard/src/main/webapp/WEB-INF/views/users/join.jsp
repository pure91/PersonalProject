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
						required="required" pattern="[A-Za-z0-9]{4,20}"
						placeholder="아이디 입력">
					<button type="button" class="btn btn-secondary" id="checkMemId">중복확인</button>
				</div>
			</div>
			<div id="checkId-result">알파벳과 숫자를 포함하여 4~20글자를 입력해주세요</div>
			<br>

			<!-- 비밀번호 / 비밀번호 확인 -->
			<!-- col 먹이려면 row로 먼저 감싸야하는듯 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersPw" class="subject">비밀번호</label> <input
						type="password" name="usersPw" id="usersPw" class="form-control"
						required="required" pattern=".{4,20}" placeholder="비밀번호 입력">
				</div>
				<br>
				<div class="form-group col-6">
					<label for="usersPw2" class="subject">비밀번호 확인</label> <input
						type="password" name="usersPw2" id="usersPw2" class="form-control"
						required="required" pattern=".{4,20}" placeholder="비밀번호 확인">
				</div>
			</div>
			<div id="password-result">비밀번호는 숫자 4~20글자로 입력해주세요.</div>
			<br>

			<!-- 이름/성별 -->
			<div class="row">
				<div class="form-group col-6">
					<label for="usersName" class="subject">이름</label> <input
						name="usersName" id="usersName" class="form-control"
						required="required" pattern="[가-힣]{2,10}" placeholder="이름 입력">
					<div id="name-result">이름은 한글 2~10글자로 입력해주세요.</div>
				</div>

				<div class="form-group col-6">
					<label for="usersGender" class="subject">성별</label>
					<div>
						<label class="radio-inline"> <input type="radio"
							name="usersGender" id="usersGender" checked value="남자">남자
						</label> <label class="radio-inline"> <input type="radio"
							name="usersGender" id="usersGender" value="여자">여자
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
					<label for="usersTel" class="subject">연락처</label> <input
						name="usersTel" id="usersTel" class="form-control"
						placeholder="연락처 입력">
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
				<button type="button" class="btn btn-outline-primary" id="join-btn">가입하기</button>
				<button type="button" class="btn btn-outline-danger" id="joinCancle"
					onclick="history.back()">취소</button>
			</div>
		</form>
	</div>

	<!-- Daum 우편번호 서비스 스크립트 추가 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

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

	<!-- 아이디 중복검사, 유효성 처리 -->
	<script>
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
			        xhrFields: { //서버간 인증 쿠키를 주고 받으려면 true로 써야한다고함
			            withCredentials: true
			        },
				}).done(function(rst) {
				    console.log(rst);
				    if(rst.rst === "ok"){
				        checkId_result.css("color", "green").html(rst.msg);
				        submitFlag = true;
				    } else {
				        checkId_result.css("color", "red").html(rst.msg);
				        submitFlag = false;
				    }
				})
				.fail(function(xhr) {
				    console.log(xhr);
				});
			});
		})
	</script>
</body>
</html>