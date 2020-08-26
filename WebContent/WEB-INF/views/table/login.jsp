<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
<title>Login V4</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('/res/images/abc123.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-49"> Login </span>

					<div class="wrap-input100 validate-input m-b-23"
						data-validate="UserID is reauired">
						<span class="label-input100">User ID</span> <input
							class="input100" type="text" name="ui_id" id="ui_id"
							placeholder="Type your userId"> <span
							class="focus-input100" data-symbol="&#xf206;"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Password is required">
						<span class="label-input100">Password</span> <input
							class="input100" type="password" name="ui_passowrd"
							id="ui_password" placeholder="Type your password"> <span
							class="focus-input100" data-symbol="&#xf190;"></span>
					</div>

					<div class="text-right p-t-8 p-b-31">
						<a href="#"> Forgot password? </a>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="button" class="login100-form-btn"
								onclick="doLogin()">Login</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script>
function doLogin() {
	var uiId = $('#ui_id').val();
	var uiPassword = $('#ui_password').val();
	if(uiId == null || uiId.trim().length<4 || uiId.trim().length>20){
		alert('아이디는 4글자이상 20글자 이하로 작성해주세요.');
		return;
	}else if(uiPassword == null || uiPassword.trim().length<4 || uiPassword.trim().length>20){
		alert('비밀번호는 4글자이상 20글자 이하로 작성해주세요.');
		return;
	}
	var cmd = 'login';
	var param = {
		ui_id : uiId,
		ui_password : uiPassword,
		cmd : cmd
	}
	$.ajax({
		method : 'POST',
		url : '/ajax/table',
		data : JSON.stringify(param),
		contentType:'application/json',
		success : function(res){
			if(res.result){
				alert("로그인성공");
				location.href = "/views/table/list";
			}else{
				alert("아이디랑 비밀번호를 확인해주세요");
			}
		}
	});
}
</script>
</body>
</html>