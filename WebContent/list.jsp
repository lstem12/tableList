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
function searchList(){
	getList(1);
}
var pageSize = 10;
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
			pageSize : pageSize,
			MI_NUM:$('#MI_NUM').val(),
			MI_NAME:$('#MI_NAME').val(),
			MI_ID:$('#MI_ID').val()
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
			var pageHtml = '<a href="#" onclick="getList()">◀◀</a> <a href="#" onclick="getList(' + (page-1) + ')">◀</a>';
			if(page==1){
				pageHtml ='◀';
			}
			var sNum = (Math.ceil(page/10)-1) * 10 + 1;
			var lNum = sNum + (10-1);
			if(lNum>totalPage){
				lNum = totalPage;
			}
			for(var i=sNum;i<=lNum;i++){
				if(page==i){
					pageHtml += '[<b>' + i + '</b>]';
				}else{
					pageHtml += '[<a href="#" onclick="getList('+ i +')">' + i + '</a>] ';
				}
			}
			
			var lastPageHtml = '<a href="#" onclick="getList('+ (page+1) +')">▶</a>';
			if(totalPage==page){
				lastPageHtml = '▶';
			}
			lastPageHtml += ' <a href="#" onclick="getList(' + totalPage + ')">▶▶</a>';
			$('#pageDiv').html(pageHtml + lastPageHtml);
		}
	})
}
</script>
<div class="search">
	<input type="number" id="MI_NUM" placeholder="번호검색">
	<input type="text" id="MI_NAME" placeholder="이름검색">
	<input type="text" id="MI_ID" placeholder="아이디검색">
	<button onclick="searchList()">검색</button>
</div>
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

<!-- 
1. crud 
2. mvc
3. ajax + JSON
- ajax : 언어, 라이브러리를 의미하는게 아니라 웹에서 할 수 있는 비동기 통신 방법, 규칙 의미함.
- JSON : Javascript Object Structure Notation : 자바스크립트에서 쉽게 알아 먹을 수 있는 구조
- ajax 의  x는 원래 xml 을 의미하나(xml통신은 soap통신) JSON 형태로 주로 주고 받는다.
4. maven
5. mybatis
6. pagination
 -->