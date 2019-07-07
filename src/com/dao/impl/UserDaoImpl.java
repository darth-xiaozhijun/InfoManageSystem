package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.dao.UserDao;
import com.pojo.User;

public class UserDaoImpl implements UserDao{
	
	Logger logger = Logger.getLogger(UserDaoImpl.class);

	/**
	 * 根据用户名和密码查询用户信息
	 */
	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		
		logger.debug("根据用户名和密码查询用户信息,请求参数：uname:"+uname+" pwd:"+pwd);
		//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//声明变量
		User u=null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/info_manage_system?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true","root", "123456");
			//创建sql命令
			String sql = "select * from t_user where uname = ? and pwd = ?";
			//创建sql命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setObject(1, uname);
			ps.setObject(2, pwd);
			//执行sql
			rs=ps.executeQuery();
			//遍历结果
			while(rs.next()){
				//给变量赋值
				u=new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				logger.debug("查询数据库结果赋值成功,u:"+u.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//返回结果
		return u;
	}

	/**
	 * 用户注册
	 */
	@Override
	public int userRegDao(User u) {
		//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		//声明变量
		int index=-1;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/info_manage_system","root", "123456");
			//创建SQL命令
			String sql="insert into t_user values(default,?,?,?,?,?)";
			//创建SQL命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1,u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			//执行
			index=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{//关闭资源
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//返回结果
		return index;
	}
}
