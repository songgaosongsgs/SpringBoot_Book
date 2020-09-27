package com.bookstore.work.service;

import com.bookstore.work.entity.Bookstore;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
public interface IBookstoreService extends IService<Bookstore> {
	/**
	 * @param byday 
	 * @param byprice 
	 * @description	查询图书列表
	 */
	Page bookList(Page pageObj, String bookName, String author, Integer status, Integer byprice, Integer byday);
	/**
	 * @description 根据ID查询图书详情
	 */
	Bookstore getByid(Integer id)  throws Exception;
	/**
	 * @description 修改图书信息
	 */
	boolean updateBookByid(Integer id, String json) throws Exception;

	/**
	 * @description 删除图书
	 */
	boolean deleteBookByid(Integer id) throws Exception;
	/**
	 * @description 新增图书
	 */
	boolean addBook(String json) throws Exception;
	/**
	 * @param id
	 * @return
	 * @description 借出
	 * @author	sgsong 
	 */
	boolean gin(Integer id);
	boolean gout(String json) throws Exception;

}
