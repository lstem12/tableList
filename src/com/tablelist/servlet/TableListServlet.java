package com.tablelist.servlet;

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

@WebServlet("/ajax/table")
public class TableListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private TableListService tableListService = new TableListServiceImpl();
	private TableListVO tableListVO = new TableListVO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		Map<String,Object> result = new HashMap<>();
		if("list".equals(cmd)) {
			result.put("list", tableListService.tableListService(null));
		}
		PrintWriter pw = response.getWriter();
		pw.println(gson.toJson(result));

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
