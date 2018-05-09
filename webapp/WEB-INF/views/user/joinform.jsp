<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="user">

					<form id="join-form" name="joinForm" method="get"
						action="${pageContext.request.contextPath }/user/join">

						<label class="block-label" for="name">이름</label> <input id="name"
							name="name" type="text" value=""> <label
							class="block-label" for="email">이메일</label> <input id="email"
							name="email" type="text" value=""> <input
							id="btnEmailCheck" type="button" value="id 중복체크">
						<div id="msg"></div>
						<label class="block-label">패스워드</label> <input name="password"
							type="password" value="">

						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female"
								checked="checked"> <label>남</label> <input type="radio"
								name="gender" value="male">
						</fieldset>

						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>

						<input type="submit" value="가입하기">

					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
	<!-- /container -->

</body>

<script type="text/javascript">
	$("#btnEmailCheck").on("click", function() {
		
		console.log("이메일체크 도착!");

		var email = $("#email").val();
		console.log(email);


		$.ajax({
			//보낼 때
			url : "${pageContext.request.contextPath}/user/emailcheck",
			type : "post",
			data : {email: email},
					//뒤의 email은 var email
			//받을 때
			dataType : "json", //넘어올 때의 data type(json)
// 			success : function(result) { //통신이 성공했을 때, //result로 해도 되고 isExists로 해도 됨
			success : function(isExists) { //통신이 성공했을 때, 
				/*성공시 처리해야될 코드 작성*/
				
				console.log(isExists);
				if(isExists == true){ //true이면 사용중!
					$("#msg").html("사용중인 아이디 입니다.")
				}else{
					$("#msg").html("사용가능한 아이디 입니다.")
				}
			
			},
			//실패했을 때
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>
</html>