package com.tablelist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tablelist.dao.TableListDAO;
import com.tablelist.servlet.InitServlet;
import com.tablelist.vo.TableListVO;

public class TableListDAOImpl implements TableListDAO {

	@Override
	public List<TableListVO> tableListDAO(TableListVO tableListVO) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TableListVO> tableList = new ArrayList<TableListVO>();
		String sql = "select tb_num, tb_title, tb_name, tb_nickName, tb_credat, tb_views, "
				+ "tb_id, tb_etc, tb_grd from table_list";
		try {
			con = InitServlet.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				TableListVO voList = new TableListVO();
				voList.setTb_num(rs.getInt("tb_num"));
				voList.setTb_title(rs.getString("tb_title"));;
				voList.setTb_name(rs.getString("tb_name"));
				voList.setTb_nickName(rs.getString("tb_nickName"));
				voList.setTb_credat(rs.getString("tb_credat"));
				voList.setTb_views(rs.getInt("tb_views"));
				voList.setTb_id(rs.getString("tb_id"));
				voList.setTb_etc(rs.getString("tb_etc"));
				voList.setTb_grd(rs.getString("tb_grd"));				
				tableList.add(voList);
			}
			return tableList;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			InitServlet.close(rs,ps,con);
		}
		return null;
	}

	@Override
	public int tableDelete(TableListVO tableListVO) {
		return 0;
	}
	public static void main(String[] args) {
		InitServlet is = new InitServlet();
		is.init();
		TableListDAO tableListDAO = new TableListDAOImpl();
		System.out.println(tableListDAO.tableListDAO(null));
	}
}
