package com.bookstore.work.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.bookstore.work.config.ObjectVo;
import com.bookstore.work.entity.Bookstore;
import com.bookstore.work.service.IBookstoreService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2018-06-17
 */
@RestController
@RequestMapping("/work/bookstore")
public class BookstoreController {
	@Autowired
	private IBookstoreService	bookstoreService;
	
	
	
	/**
	 * @param page 当前页
	 * @param rows 每页条数
	 * @param bookName 书名
	 * @param author	作者
	 * @param status	状态 是否借出
	 * @return
	 * @throws Exception
	 * @description	获取图书列表
	 * @author	
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/bookList")
	public  Page bookList (@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,String bookName,String author,Integer status,Integer byprice,Integer byday)throws Exception {
		Page pageObj = new Page(page, rows);

		pageObj = bookstoreService.bookList(pageObj,bookName,author,status,byprice,byday);
		return pageObj;
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 * @description	根据 图书id 获取图书详情
	 * @author	 
	 */
	@RequestMapping(value = "/getbookByid")
	public  ObjectVo getByid (Integer id ){

		try {
			Bookstore b  = bookstoreService.getByid(id);
			if(null!=b){
				return new ObjectVo().ok("", b);
			}else{
				return new ObjectVo().error(500, "未找到数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}
	
	/**
	 * @param id	图书主键
	 * @param json 图书信息
	 * @return
	 * @description 修改图书信息
	 * @author	 
	 */
	@RequestMapping(value = "/updatebookByid")
	public  ObjectVo updateByid (Integer id ,String json ) {

		try {
			boolean b  = bookstoreService.updateBookByid(id,json);
			if(b){
				return new ObjectVo().ok("操作成功", b);
			}else{
				return new ObjectVo().error(500, "执行");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 * @description	删除图书
	 * @author	 
	 */
	@RequestMapping(value = "/deletebookByid")
	public  ObjectVo deleteByid (Integer id ){

		try {
			boolean b  = bookstoreService.deleteBookByid(id);
			if(b){
				return new ObjectVo().ok("操作成功", b);
			}else{
				return new ObjectVo().error(500, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}
	
	/**
	 * @param json 图书信息
	 * @return
	 * @throws Exception
	 * @description	新增图书
	 * @author	 
	 */
	@RequestMapping(value = "/addBook")
	public  ObjectVo addBook (String json) {

		try {
			boolean b  = bookstoreService.addBook(json);
			if(b){
				return new ObjectVo().ok("操作成功", b);
			}else{
				return new ObjectVo().error(500, "保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}

	/**
	 * @param gout 借书登记信息
	 * @return
	 * @throws Exception
	 * @description	借出图书 
	 * @author	 
	 */
	@RequestMapping(value = "/gout")
	public  ObjectVo gout (String json ){

		try {
			boolean b  = bookstoreService.gout(json);
			if(b){
				return new ObjectVo().ok( "执行成功");
			}else{
				return new ObjectVo().error(500, "执行失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 * @description	归还 图书  更新时间 状态
	 * @author	 
	 */
	@RequestMapping(value = "/gin")
	public  ObjectVo gin (Integer id ){

		try {
			boolean b  = bookstoreService.gin(id);
			if(b){
				return new ObjectVo().ok("执行成功");
			}else{
				return new ObjectVo().error(500, "未找到数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ObjectVo().error(500, e.getMessage());
		}
	}
	

}

