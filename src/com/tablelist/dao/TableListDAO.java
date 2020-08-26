package com.tablelist.dao;

import java.util.List;

import com.tablelist.vo.TableListVO;

public interface TableListDAO {
	List<TableListVO> tableListDAO(TableListVO tableListVO);
	int tableDelete(TableListVO tableListVO);
}
