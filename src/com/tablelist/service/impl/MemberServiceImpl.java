package com.tablelist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tablelist.dao.MemberInfoDAO;
import com.tablelist.dao.impl.MemberInfoDAOImpl;
import com.tablelist.service.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberInfoDAO memberInfoDAO = new MemberInfoDAOImpl();
	@Override
	public List<Map<String, Object>> selectMemberInfoList(Map<String, Object> mi) {
		int page = Integer.parseInt(mi.get("page").toString());
		int pageSize = Integer.parseInt(mi.get("pageSize").toString());
		int startNum = (page-1) * pageSize +1;
		int endNum = (startNum-1) +pageSize;
		mi.put("startNum", startNum);
		mi.put("endNum",endNum);
		return memberInfoDAO.selectMemberInfoList(mi);
	}
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		Map<String,Object> param = new HashMap<>();
		param.put("page", 2);
		param.put("pageSize", 20);
		List<Map<String,Object>>mList = memberService.selectMemberInfoList(param);
		for(Map<String,Object>m : mList) {
			System.out.println(m);
		}
	}
}
