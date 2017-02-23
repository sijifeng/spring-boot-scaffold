package com.season.platform.web.api.controller;

import com.season.platform.web.api.model.User;
import com.season.platform.web.api.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiyc on 2017/2/17.
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * 添加成员
	 *
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "添加成员", notes = "添加成员")
	@ApiImplicitParam(name = "user", value = "user实体bean", required = true, dataType = "User")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		userService.save(user);
		return "SUCCESS";
	}

	/**
	 * 查询出所有user集合
	 *
	 * @return
	 */
	@ApiOperation(value = "查询出所有user集合", notes = "")
	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public List<User> getUserList() {
		List<User> userList = userService.findAll();
		return userList;
	}

	/**
	 * 根据用户名获取用户详细信息
	 *
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "用户详细信息", notes = "根据用户名来获取用户详细信息")
	@ApiImplicitParam(name = "name", value = "name", required = true, dataType = "String")
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public User getUser(@PathVariable String name) {
		User user = userService.findByName(name);
		return user;
	}

	/**
	 * 更新用户详细信息
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "更新用户详细信息", notes = "根据id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String"),
			@ApiImplicitParam(name = "user", value = "用户详细", required = true, dataType = "User")
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		userService.save(user);
		return "SUCCESS";
	}

	/**
	 * 根据id删除 user
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "根据url的ISBN来指定删除图书")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable Long id) {
		userService.delete(id);
		return "SUCCESS";
	}

}
