package com.tablelist.dao;

import java.util.List;

import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

public interface TableListDAO {
	List<TableListVO> tableListDAO(TableListVO tableListVO);
	int inserTableDAO(TableListVO tableListVO);
	int tableDelete(TableListVO tableListVO);
	TableListVO tableViewDAO(TableListVO tableListVO);
	UserInfoVO selectUserForLogin(UserInfoVO user);
}
