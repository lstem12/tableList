<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Map<String,Object>> strList = new ArrayList<>();
for(int i=1;i<=100;i++){
	Map<String,Object> map = new HashMap<>();
	map.put("name", "이름"+i);
	map.put("age", i);
	map.put("area", "지역"+i);
	strList.add(map);
}
Gson g = new Gson();
out.println(g.toJson(strList));
/*
임의의 맵을 만듬
맵은 반드시 3개 이상의 키,밸류
리스트 위에서 만든 임의의 맵이 최소 100개가 있어야됨.
*/
%>