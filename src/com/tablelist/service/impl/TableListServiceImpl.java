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
	@Override
	public List<TableListVO> tableListService(TableListVO tableListVO) {

		return tableListDAO.tableListDAO(null);
	}

	@Override
	public int tableDeleteService(TableListVO tableListVO) {

		return 0;
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
	public boolean tableViewService(TableListVO tableListVO) {
		tableListVO = tableListDAO.tableViewDAO(tableListVO);
		if (tableListVO != null) {
			return true;
		}
		return false;
	}
}
