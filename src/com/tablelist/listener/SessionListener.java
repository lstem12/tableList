package com.tablelist.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.tablelist.vo.TableListVO;
import com.tablelist.vo.UserInfoVO;

@WebListener
public class SessionListener implements HttpSessionListener {

	private static Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

	public static synchronized void checkUserId(String ui_id) {
		Iterator<String> it = sessionMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			HttpSession hs = sessionMap.get(key);
			if (hs.getAttribute("user") != null) {
				UserInfoVO user = (UserInfoVO) hs.getAttribute("user");
				if (ui_id.equals(user.getUi_id())) {
					hs.invalidate();
					sessionMap.remove(key);
				}
			}
		}
	}
	public SessionListener() {

	}

	public void sessionCreated(HttpSessionEvent hse) {
		String sessionId = hse.getSession().getId();
		sessionMap.put(sessionId, hse.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		String sessionId = hse.getSession().getId();
		sessionMap.remove(sessionId);
	}

}
