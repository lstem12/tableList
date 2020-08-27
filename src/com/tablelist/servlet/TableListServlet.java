package com.tablelist.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tablelist.service.TableListService;
import com.tablelist.service.impl.TableListServiceImpl;
import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

@WebServlet("/ajax/table")
public class TableListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private TableListService tableListService = new TableListServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String tb_num = request.getParameter("tb_num");
		Map<String,Object> result = new HashMap<>();
		if("list".equals(cmd)) {
			result.put("list", tableListService.tableListService(null));
		}else if("view".equals(cmd)){	
			result.put("result", tableListService.tableViewService(tb_num));
		}
		PrintWriter pw = response.getWriter();
		pw.println(gson.toJson(result));

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String str;
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		TableListVO tableListVO = gson.fromJson(sb.toString(), TableListVO.class);
		UserInfoVO user = gson.fromJson(sb.toString(), UserInfoVO.class);
		Map<String,Object> result = new HashMap<>();
		
		if("login".equals(user.getCmd())) {
			result.put("result", tableListService.doLogin(user, request.getSession()));
		}else if("logout".equals(tableListVO.getCmd())) {
			request.getSession().invalidate();
			result.put("result", true);
		}else if("insert".equals(tableListVO.getCmd())) {
			result.put("result", tableListService.insertTableService(tableListVO));
		}else if("delete".equals(tableListVO.getCmd())){
			result.put("result", tableListService.tableDeleteService(tableListVO));
		}else if("modify".equals(tableListVO.getCmd())){
			result.put("result", tableListService.tableModifyService(tableListVO));
		}
		
		String json = gson.toJson(result);
		PrintWriter pw = response.getWriter();
		pw.println(json);	
	}
}
