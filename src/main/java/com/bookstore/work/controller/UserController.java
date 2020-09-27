package com.bookstore.work.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.work.config.ObjectVo;
import com.bookstore.work.service.IUserService;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@RestController
@RequestMapping("/work/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 * @description  登录 
	 * @author	
	 */
	@RequestMapping(value = "/login")
	public  ObjectVo login (String username,String password)throws Exception {
		try {
			System.out.println("username : "+ username +", password: "+password);
			ObjectVo vo = new ObjectVo();
			vo = userService.login(username,password);
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}


}

