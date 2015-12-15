package com.ccolor.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccolor.mybatis.bean.User;
import com.ccolor.mybatis.bean.UserExample;
import com.ccolor.mybatis.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User getUser(String username) {
		return userMapper.selectByPrimaryKey(username);
	}
	public  List<User> getAllUser() {
		UserExample ue=null;
		return userMapper.selectByExample(ue);
	}
	public List<User> getUser(UserExample user){
		return userMapper.selectByExample(user);
	}
	public void addUser(User user) {
		userMapper.insert(user);
	}
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}
}
