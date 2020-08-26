package com.tablelist.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {


    public SessionListener() {

    }

    public void sessionCreated(HttpSessionEvent se)  { 

    }


    public void sessionDestroyed(HttpSessionEvent se)  { 

    }
	
}
