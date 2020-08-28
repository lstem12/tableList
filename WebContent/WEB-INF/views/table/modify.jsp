<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body>
<input type="hidden" name="tb_num" id="tb_num" value="${param.tb_num}">
<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8"><br>
			<h2 class="text-center">게시글 수정</h2><br><br>
			<p></p>
				<div class="table table-responsive">
					<table class="table table-striped">
						<tr>
							<td data-col="tb_title">제목</td>
							<td id="tb_title"></td>
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
							<td data-col="tb_field">분류</td>
							<td id="tb_field"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" class="form-control" name="tb_password" id="tb_password"></td>
						</tr>

						<tr>
							<td data-col="tb_content">글내용</td>
							<td id="tb_content"></td>
						</tr>
					</table><br>
					<button class="btn btn-info" onclick="doModify()">게시글수정</button>
					<button class="btn btn-info" onclick="doDelete()">게시글삭제</button>
					<a href="/views/table/list"><button class="btn btn-info">취소하기</button></a>
				</div>
		</div>
</div>
<script>
var tb_num = ${param.tb_num};
	$(document).ready(function(){
		$.ajax({
			url:'/ajax/table',
			method :'GET',
			data:{
				cmd:'view',
				tb_num:tb_num
			},
			contentType : 'application/json',
			success:function(res){
				var html_title='';
				var html_field='';
				var html_content='';
				for(var i=0;i<res.result.length;i++){
					var result = res.result[i];
				}
				
				$('td[data-col]').each(function(idx,td){
					var col = td.getAttribute('data-col');
					if('tb_title'===col){
						html_title +='<input type="text" class="form-control" name="'+col+'" id="'+col+'" value="'+ result[col] +'">';
					}else if('tb_field'===col){
						html_field +='<input type="text" class="form-control" name="'+col+'" id="'+col+'" value="'+ result[col] +'">';
					}else if('tb_content'===col){
						html_content +='<input type="text" class="form-control" name="'+col+'" id="'+col+'" value="'+ result[col] +'">';
					}
				})
				$('#tb_title').html(html_title);
				$('#tb_field').html(html_field);
				$('#tb_content').html(html_content);				
		}
	})
})

function doModify() {
	var els = document.querySelectorAll('input');
	var params = {};
	for (var i = 0; i < els.length; i++) {
		var el = els[i];
		params[el.name] = el.value;
	}
	params.cmd = 'modify';
	$.ajax({
		url : '/ajax/table',
		method : 'POST',
		data : JSON.stringify(params),
		contentType : 'application/json',
		success : function(res) {
			if (res.result === 1) {
				alert('회원수정 성공');
				location.href = '/views/table/list';
			} else {
				alert("회원수정 실패");
			}
		}

	}) 
}
function doDelete(){
	var params = {
			tb_num : tb_num,
			cmd : 'delete'
	}
	$.ajax({
		url : '/ajax/table',
		method : 'POST',
		data : JSON.stringify(params),
		success : function(res){
			if(res.result === 1){
				alert('삭제가 완료 되었습니다.');
				location.href = '/views/table/list';
			} else{
				alert('삭제가 실패 하였습니다.');
			}
		}
	})
}
</script>
</body>
</html>