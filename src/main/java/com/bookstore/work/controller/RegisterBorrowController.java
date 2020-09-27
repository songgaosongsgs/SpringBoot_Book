package com.bookstore.work.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.bookstore.work.config.ObjectVo;
import com.bookstore.work.entity.RegisterBorrow;
import com.bookstore.work.service.IBookstoreService;
import com.bookstore.work.service.IRegisterBorrowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@RestController
@RequestMapping("/work/registerBorrow")
public class RegisterBorrowController {
	@Autowired
	private IRegisterBorrowService borrowService;

	/**
	 * @param page
	 * @param rows
	 * @param id
	 * @return
	 * @throws Exception 图书借还记录
	 * @description
	 * @author	
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/regBorrowList")
	public Page regBorrowList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, Long id) throws Exception {
		Page pageObj = new Page(page, rows);

		pageObj = borrowService.regBorrowList(pageObj, id);
		return pageObj;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception 最近借还信息
	 * @description
	 * @author	
	 */
	@RequestMapping(value = "/getByBookId")
	public ObjectVo getByBookId(Long id) throws Exception {

		try {
			RegisterBorrow r = borrowService.getByBookId(id);
			if(null!=r){
				return new ObjectVo().ok("", r);
			}else{
				return new ObjectVo().error(500, "该图书 目前未借出过");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}

}
