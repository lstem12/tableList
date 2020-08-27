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
import com.tablelist.vo.UserInfoVO;

public class TableListDAOImpl implements TableListDAO {

	@Override
	public List<TableListVO> tableListDAO(TableListVO tableListVO) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TableListVO> tableList = new ArrayList<TableListVO>();
		String sql = "select tb_num, tb_title, tb_name, tb_nickName, tb_credat, tb_views, "
				+ "tb_field, tb_password, tb_content, tb_grd from table_list order by tb_num desc";
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
				voList.setTb_field(rs.getString("tb_field"));
				voList.setTb_password(rs.getString("tb_password"));
				voList.setTb_content(rs.getString("tb_content"));
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
	public int tableDeleteDAO(TableListVO tableListVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from table_list where tb_num=?";
		try {
			con = InitServlet.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, tableListVO.getTb_num());
			result = ps.executeUpdate();
			con.commit();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
				InitServlet.close(ps, con);
		}

		return 0;
	}
	
	@Override
	public UserInfoVO selectUserForLogin(UserInfoVO user) {
		String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, "
				+ "ui_password, ui_phone, ui_email, ui_credat, ui_nickname, ui_admin from user_info "
				+ "where ui_id=? and ui_password=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = InitServlet.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUi_id());
			ps.setString(2, user.getUi_password());
			rs = ps.executeQuery();
			if(rs.next()) {
				UserInfoVO ui = new UserInfoVO();
				ui.setUi_num(rs.getInt("ui_num"));
				ui.setUi_age(rs.getInt("ui_age"));
				ui.setUi_name(rs.getString("ui_name"));
				ui.setUi_birth(rs.getString("ui_birth"));
				ui.setUi_id(rs.getString("ui_id"));
				ui.setUi_password(rs.getString("ui_password"));
				ui.setUi_phone(rs.getString("ui_phone"));
				ui.setUi_email(rs.getString("ui_email"));
				ui.setUi_credat(rs.getString("ui_credat"));
				ui.setUi_nickname(rs.getString("ui_nickname"));
				ui.setUi_admin(rs.getString("ui_admin"));
				return ui;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			InitServlet.close(rs, ps, con);
		}
		return null;
	}

	@Override
	public int inserTableDAO(TableListVO tableListVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into table_list(tb_num, tb_title, tb_name, tb_nickName, tb_credat, tb_field, tb_password, tb_content)\n" + 
				"values(seq_tb_num.nextval, ?, ?, ?, sysdate, ?, ?, ?)";
		try {
			con = InitServlet.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, tableListVO.getTb_title());
			ps.setString(2, tableListVO.getTb_name());
			ps.setString(3, tableListVO.getTb_nickName());
			ps.setString(4, tableListVO.getTb_field());
			ps.setString(5, tableListVO.getTb_password());
			ps.setString(6, tableListVO.getTb_content());
			result = ps.executeUpdate();
			con.commit();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
				InitServlet.close(ps, con);
		}

		return 0;
	}

	@Override
	public List<TableListVO> tableViewDAO(String tb_num) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TableListVO> tableList = new ArrayList<TableListVO>();
		String sql = "select tb_num, tb_title, tb_name, tb_nickName, tb_credat, tb_views, "
				+ "tb_field, tb_password, tb_content, tb_grd from table_list where tb_num=?";
		try {
			con = InitServlet.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, tb_num);
			rs = ps.executeQuery();
			if(rs.next()) {
				TableListVO voList = new TableListVO();
				voList.setTb_num(rs.getInt("tb_num"));
				voList.setTb_title(rs.getString("tb_title"));;
				voList.setTb_name(rs.getString("tb_name"));
				voList.setTb_nickName(rs.getString("tb_nickName"));
				voList.setTb_credat(rs.getString("tb_credat"));
				voList.setTb_views(rs.getInt("tb_views"));
				voList.setTb_field(rs.getString("tb_field"));
				voList.setTb_password(rs.getString("tb_password"));
				voList.setTb_content(rs.getString("tb_content"));
				voList.setTb_grd(rs.getString("tb_grd"));
				tableList.add(voList);
				return tableList;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			InitServlet.close(rs,ps,con);
		}
		return null;
	}

	@Override
	public int tableModifyDAO(TableListVO tableListVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = InitServlet.getConnection();
			String sql = "update table_list set tb_title=?,tb_field=?,tb_content=? where tb_num=? and tb_password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, tableListVO.getTb_title());
			ps.setString(2, tableListVO.getTb_field());
			ps.setString(3, tableListVO.getTb_content());
			ps.setInt(4, tableListVO.getTb_num());
			ps.setString(5, tableListVO.getTb_password());
			int result = ps.executeUpdate();
			con.commit();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			InitServlet.close(ps, con);
		}
		
		return 0;
	}

}
