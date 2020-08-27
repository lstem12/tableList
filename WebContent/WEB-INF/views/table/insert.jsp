<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8"><br>
			<h2 class="text-center">게시글 작성</h2><br><br>
			<p></p>
				<div class="table table-responsive">
					<table class="table table-striped">
						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="tb_title" id="tb_title"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td><input type="text" class="form-control" name="tb_name" id="tb_name" value="${user.ui_name}" disabled="disabled" ></td>
						</tr>
						<tr>
							<td>닉네임</td>
							<td><input type="text" class="form-control" name="tb_nickName" id="tb_nickName" value="${user.ui_nickname}" disabled="disabled"></td>
						</tr>
						<tr>
							<td>분류</td>
							<td><input type="text" class="form-control" name="tb_field" id="tb_field"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" class="form-control" name="tb_password" id="tb_password"></td>
						</tr>

						<tr>
							<td>글내용</td>
							<td><input name="tb_content" id="tb_content" class="form-control"></td>
						</tr>
					</table><br>
					<button class="btn btn-info" onclick="doInsert()">게시글작성</button>
					<a href="/views/table/list"><button class="btn btn-info">취소하기</button></a>
				</div>
		</div>
	</div>
<script>
function doInsert() {
	var els = document.querySelectorAll('input');
	var params = {};
	for (var i = 0; i < els.length; i++) {
		var el = els[i];
		params[el.name] = el.value;
	}
	params.cmd = 'insert';
	$.ajax({
		url : '/ajax/table',
		method : 'POST',
		data : JSON.stringify(params),
		contentType : 'application/json',
		success : function(res) {
			if (res.result === 1) {
				alert('게시글 작성이 완료되었습니다.');
				location.href = '/views/table/list';
			} else {
				alert('게시글 작성 실패');
			}
		}

	})
}
</script>
</body>
</html>