package com.tablelist.service;

import java.util.List;

import com.tablelist.vo.TableListVO;

public interface TableListService {
	List<TableListVO> tableListService(TableListVO tableListVO);
	int tableDeleteService(TableListVO tableListVO);
}
