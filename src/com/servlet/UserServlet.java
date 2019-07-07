package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//声明日志对象
	Logger logger =Logger.getLogger(UserServlet.class);
	//获取service层对象
	UserService us=new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取操做符
		String oper=req.getParameter("oper");
		if("login".equals(oper)){
			//调用登录处理方法
			checkUserLogin(req,resp);
		}else if("reg".equals(oper)){
			//调用注册功能
			userReg(req,resp);
		}else{
			logger.debug("没有找到对应的操作符："+oper);
		}
	}

	/**
	 * 处理登录
	 * @param req
	 * @param resp
	 */
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求信息
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		//处理请求信息
			//校验
			User u=us.checkUserLoginService(uname, pwd);
			if(u!=null){
				//获取session对象
				HttpSession hs=req.getSession();
				//将用户数据存储到session中
				hs.setAttribute("user", u);
				//重定向
				resp.sendRedirect("/mg/main/main.jsp");
				return;
			}else{
				//添加标识符到request中
				req.setAttribute("flag",0);
				//请求转发
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
		//响应处理结果
			//直接响应
			//请求转发
	}
	
	/**
	 * 注册用户
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求信息
			String uname=req.getParameter("uname");
			String pwd=req.getParameter("pwd");
			String sex=req.getParameter("sex");
			int age=req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
			String birth=req.getParameter("birth");
			String[] bs=null;
			if(birth!=""){
				bs=birth.split("/");
				birth=bs[2]+"-"+bs[0]+"-"+bs[1];
			}
			System.out.println(uname+":"+pwd+":"+sex+":"+age+":"+birth);
			User u=new User(0, uname, pwd, sex, age, birth);
		//处理请求信息
			//调用业务层处理
			int index=us.userRegService(u);
		//响应处理结果
			if(index>0){
				//获取session
				HttpSession hs=req.getSession();
				hs.setAttribute("reg", "true");
				//重定向
				resp.sendRedirect("/mg/login.jsp");
			}
		
	}
}
