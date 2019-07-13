package com.dao;

import java.util.List;

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

	/**
	 * 用户修改密码
	 * @param newPwd
	 * @param uid
	 * @return
	 */
	int userChangePwdDao(String newPwd, int uid);

	/**
	 * 显示所有的用户信息
	 * @return
	 */
	List<User> userShowDao();
}
