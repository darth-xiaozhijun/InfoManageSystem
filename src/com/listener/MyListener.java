package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements HttpSessionListener,ServletContextListener{

    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * session被创建时人数自增
     */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session被创建了");
		//获取ServletContext对象
		ServletContext sc=se.getSession().getServletContext();
		//获取在线统计人数的变量
		int count=(int) sc.getAttribute("count");
		//存储
		sc.setAttribute("count",++count);
	}
	
	/**
	 * session被销毁时人数自减
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session被销毁了");
		//获取ServletContext对象
		ServletContext sc=se.getSession().getServletContext();
		//获取在线统计人数的变量
		int count=(int) sc.getAttribute("count");
		//存储
		sc.setAttribute("count",--count);
	}
	
	/**
	 * application对象初始化
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("application对象被初始化了");
		//获取application
		ServletContext sc=sce.getServletContext();
		//在application对象存储变量用来统计在线人数
		sc.setAttribute("count",0);	
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("application对象被销毁了");
		
	}
}
