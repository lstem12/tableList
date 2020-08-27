package com.tablelist.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tablelist.dao.TableListDAO;
import com.tablelist.dao.impl.TableListDAOImpl;
import com.tablelist.listener.SessionListener;
import com.tablelist.service.TableListService;
import com.tablelist.servlet.InitServlet;
import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

public class TableListServiceImpl implements TableListService {
	TableListDAO tableListDAO = new TableListDAOImpl();
	TableListVO tableListVO = new TableListVO();
	@Override
	public List<TableListVO> tableListService(TableListVO tableListVO) {

		return tableListDAO.tableListDAO(null);
	}

	@Override
	public int tableDeleteService(TableListVO tableListVO) {
		
		return tableListDAO.tableDeleteDAO(tableListVO);
	}

	@Override
	public boolean doLogin(UserInfoVO user, HttpSession hs) {
		user = tableListDAO.selectUserForLogin(user);
		if (user != null) {
			SessionListener.checkUserId(user.getUi_id());
			hs.setAttribute("user", user);
			return true;
		}
		return false;
	}
	
	@Override
	public int insertTableService(TableListVO tableListVO) {
		return tableListDAO.inserTableDAO(tableListVO);
	}

	@Override
	public TableListVO tableViewService(String tb_num, HttpSession hs) {
		tableListVO = tableListDAO.tableViewDAO(tb_num);
		if (tableListVO != null) {
			SessionListener.tableModify(tb_num);
			hs.setAttribute("table", tableListVO);
			return tableListVO;
		}
		return tableListVO;
	}

	@Override
	public int tableModifyService(TableListVO tableListVO) {
		return tableListDAO.tableModifyDAO(tableListVO);
	}
}
