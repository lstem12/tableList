package com.tablelist.dao;

import java.util.List;

import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

public interface TableListDAO {
	List<TableListVO> tableListDAO(TableListVO tableListVO);
	int inserTableDAO(TableListVO tableListVO);
	int tableDeleteDAO(TableListVO tableListVO);
	int tableModifyDAO(TableListVO tableListVO);
	List<TableListVO> tableViewDAO(String tb_num);
	UserInfoVO selectUserForLogin(UserInfoVO user);
}
