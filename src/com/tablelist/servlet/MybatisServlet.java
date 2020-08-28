package com.tablelist.servlet;

import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


@WebServlet(value="/MybatisServlet",loadOnStartup = 1)
public class MybatisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory ssf;
	static {
		InputStream is = InitServlet.class.getClassLoader().getResourceAsStream("resources/mybatis-config.xml");
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		ssf = ssfb.build(is);
	}
	public static SqlSession getSession() {
		return ssf.openSession();
	}
	public static void main(String[] args) {
		try(SqlSession ss = getSession()){
			System.out.println(ss.selectList("Member.selectMember"));
		}
	}

}
