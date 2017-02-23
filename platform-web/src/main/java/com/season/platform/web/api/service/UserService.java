package com.season.platform.web.api.service;

import com.season.platform.web.api.model.User;

import java.util.List;


/**
 * Created by jiyc on 2017/2/17.
 */
public interface UserService {
	public void save(User user);

	public void delete(Long id);

	public User findByName(String name);

	public List<User> findAll();

}
