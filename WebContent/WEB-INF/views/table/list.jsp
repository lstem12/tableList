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
<script>
	var param = {
			cmd : 'list'
	}
	$.ajax({
		url:'/ajax/table',
		method:'GET',
		data:{cmd:'list'},
		success : function(res){
			var html = '';
			for(var i=0;i<res.list.length;i++){
				var table = res.list[i];
				html += '<tr>';
				$('th[data-col],th[data-pk]').each(function(idx,th){
					var col = th.getAttribute('data-col');
					if(col){
						html += '<td>' + table[col] +'</td>';
					}else{
		                col=th.getAttribute('data-pk');
		                html+='<td><input type="radio" name="'+col+'"value="'+ table[col] + '"></td>';
					}
				})
				html += '</tr>';
			}
			$('#tbody').html(html);
		}
	})
				
</script>
<table class="table table-bordered table-center">
	<tr id="tr">
		<th data-pk="tb_num"></th>
		<th data-col="tb_num">번호</th>
		<th data-col="tb_title">제목</th>
		<th data-col="tb_name">작성자</th>
		<th data-col="tb_nickName">닉네임</th>
		<th data-col="tb_credat">작성일</th>
		<th data-col="tb_views">조회수</th>
	</tr>
	<tbody id="tbody">
	</tbody>
</table>
	<div class="container">
		${sessionScope.user.ui_name} 님 반갑습니다.		
			<a href="/views/table/insert"><button class="btn btn-info">게시글 작성</button></a>
			<button class="btn btn-info" id="viewBtn">게시글 보기</button>
		<button class="btn btn-info" onclick="doLogout()">로그아웃</button>
	</div>
<script>
		function doLogout() {
			$.ajax({
				url : '/ajax/table',
				method : 'POST',
				data : JSON.stringify({
					cmd : 'logout'
				}),
				success : function(res) {
					if (res.result) {
						alert('로그아웃 되었습니다.');
						location.href = '/views/table/login';
					}
				}
			})
		}
		document.querySelector('#viewBtn').onclick = function(){
			var tb_num = $('[name=tb_num]:checked');
			if(!tb_num.length){
				alert('선택을 하고 수정버튼을 누르세요.');
				return;
			}
			tb_num = tb_num[0].value;
			location.href='/views/table/modify?tb_num='+tb_num;
		}
		document.querySelector('#deleteBtn').onclick = function(){
			var tb_numObjs = $('[name=tb_num]:checked');
			if(!tb_numObjs.length){
				alert('선택을 하고 삭제버튼을 누르세요.');
				return;
			}
		}
</script>
</body>
</html>