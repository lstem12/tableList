package com.tablelist.dao;

import java.util.List;
import java.util.Map;

public interface MemberInfoDAO {
	List<Map<String, Object>> selectMemberInfoList(Map<String, Object> mi);
}
