package com.tablelist.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tablelist.dao.MemberInfoDAO;
import com.tablelist.servlet.MybatisServlet;

public class MemberInfoDAOImpl implements MemberInfoDAO {
	@Override
	public List<Map<String, Object>> selectMemberInfoList(Map<String, Object> mi) {
		try(SqlSession ss = MybatisServlet.getSession()){
			return ss.selectList("Member.selectMemberList",mi);
		}
	}
	public static void main(String[] args) {
		MemberInfoDAO memberInfoDAO = new MemberInfoDAOImpl();
		Map<String,Object> param = new HashMap<>();
		param.put("startNum", 51);
		param.put("endNum", 60);
		List<Map<String,Object>>mList = memberInfoDAO.selectMemberInfoList(param);
		for(Map<String,Object>m : mList) {
			System.out.println(m);
		}
	}
}
