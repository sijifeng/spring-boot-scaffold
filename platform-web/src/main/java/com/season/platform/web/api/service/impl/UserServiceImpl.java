package com.season.platform.web.api.service.impl;

import com.season.platform.web.api.mapper.UserRepository;
import com.season.platform.web.api.model.User;
import com.season.platform.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiyc on 2017/2/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}

	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
