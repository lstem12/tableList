package com.tablelist.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

public interface TableListService {
	List<TableListVO> tableListService(TableListVO tableListVO);
	int insertTableService(TableListVO tableListVO);
	int tableDeleteService(TableListVO tableListVO);
	int tableModifyService(TableListVO tableListVO);
	List<TableListVO> tableViewService(String tb_num);
	boolean doLogin(UserInfoVO user, HttpSession hs);
}
