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
var pageSize =10;
var totalCnt = 0;
var totalPage = 0;
$(document).ready(getList);
function getList(page){
	if(isNaN(page)){
		page = 1;
	}
	$.ajax({
		url : '/ajax/member',
		method : 'GET',
		data : {
			cmd : 'list',
			page : page,
			pageSize : pageSize
		},
		success : function(res){
			totalCnt = res.totalCnt;
			totalPage = Math.ceil(totalCnt/pageSize);
			var html ='';
			for(var i=0;i<res.list.length;i++){
				html+= '<tr>';
				var member = res.list[i];
				$('th[data-col]').each(function(idx,th){
					var col = th.getAttribute('data-col');
					html+='<td>'+member[col]+'</td>';
				})
				html+= '</tr>';
			}
			$('#tbody').html(html);			
					var pageHtml ='<a href="#" onclick="getList('+ (page-1) +')">◀</a>';
				if(page<=1){
					pageHtml ='<a href="#" onclick="getList('+ 1 +')">◀</a>';
				}
			
			for(var i=1;i<=pageSize;i++){
				if(page==i){
					pageHtml += '[<b>' + i + '</b>]';
				}else{
					pageHtml += '[<a href="#" onclick="getList('+ i +')">' + i + '</a>] ';
				}
			}
			if(totalPage%pageSize == 0){
				pageSize = pageSize + 10;
				pageHtml += '<a href="#" onclick="getList('+ (pageSize) +')"></a>';
			}
			pageHtml += '<a href="#" onclick="getList('+ (page+1) +')">▶</a>';
			$('#pageDiv').html(pageHtml);
		}
	})
}
</script>
<table class="table table-bordered">
	<tr>
		<th data-col="MI_NUM">번호</th>
		<th data-col="MI_ID">아이디</th>
		<th data-col="MI_NAME">이름</th>
	</tr>
	<tbody id="tbody">
	</tbody>
</table>
<div id="pageDiv">
</div>
</body>
</html>
