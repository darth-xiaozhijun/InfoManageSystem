package com.dao;

import com.pojo.User;

public interface UserDao {

	/**
	 * 根据用户名和密码查询用户信息
	 * @param uname 用户名
	 * @param pwd	密码
	 * @return 返回查询到的用户信息
	 */
	User checkUserLoginDao(String uname,String pwd);
	
	/**
	 * 用户注册
	 * @param u
	 * @return
	 */
	int userRegDao(User u);
}
