package com.tablelist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tablelist.service.MemberService;
import com.tablelist.service.impl.MemberServiceImpl;

@WebServlet("/ajax/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private Gson gson = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> param = new HashMap<>();
		param.put("page",request.getParameter("page"));
		param.put("pageSize",request.getParameter("pageSize"));
		List<Map<String,Object>> mList = memberService.selectMemberInfoList(param);
		PrintWriter pw = response.getWriter();
		Map<String,Object> rMap = new HashMap<>();
		rMap.put("list", mList);
		rMap.put("totalCnt", 10000);
		pw.println(gson.toJson(rMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
