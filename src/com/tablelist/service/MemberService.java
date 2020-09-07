package com.tablelist.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	List<Map<String, Object>> selectMemberInfoList(Map<String, Object> mi);	
	int selectCountMember(Map<String,Object> mi);
}
