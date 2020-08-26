package com.tablelist.service.impl;

import java.util.List;

import com.tablelist.dao.TableListDAO;
import com.tablelist.dao.impl.TableListDAOImpl;
import com.tablelist.service.TableListService;
import com.tablelist.servlet.InitServlet;
import com.tablelist.vo.TableListVO;

public class TableListServiceImpl implements TableListService {
	TableListDAO tableListDAO = new TableListDAOImpl();
	@Override
	public List<TableListVO> tableListService(TableListVO tableListVO) {

		return tableListDAO.tableListDAO(null);
	}

	@Override
	public int tableDeleteService(TableListVO tableListVO) {

		return 0;
	}
	public static void main(String[] args) {
		InitServlet is = new InitServlet();
		is.init();
		TableListService tableListService = new TableListServiceImpl();
		System.out.println(tableListService.tableListService(null));
	}
}
