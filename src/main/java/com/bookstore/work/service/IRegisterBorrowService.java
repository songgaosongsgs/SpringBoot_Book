package com.bookstore.work.service;

import com.bookstore.work.entity.RegisterBorrow;
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
public interface IRegisterBorrowService extends IService<RegisterBorrow> {

	Page regBorrowList(Page pageObj, Long id);

	RegisterBorrow getByBookId(Long id);

}
