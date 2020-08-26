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
				$('th[data-col]').each(function(idx,th){
					var col = th.getAttribute('data-col');
					html += '<td>' +table[col]+ '</td>';
				})
				html += '</tr>';
			}
			$('#tbody').html(html);
		}
	})
</script>
<table class="table table-bordered">
	<tr>
		<th data-col="tb_num">번호</th>
		<th data-col="tb_title">제목</th>
		<th data-col="tb_name">작성자</th>
		<th data-col="tb_credat">작성일</th>
		<th data-col="tb_nickName">닉네임</th>
		<th data-col="tb_id">아이디</th>
		<th data-col="tb_etc">비고</th>
		<th data-col="tb_grd">게시물등급</th>	
		<th data-col="tb_views">조회수</th>
	</tr>
	<tbody id="tbody">
	</tbody>
</table>
</body>
</html>