package com.bookstore.work.service;

import com.bookstore.work.config.ObjectVo;
import com.bookstore.work.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
public interface IUserService extends IService<User> {

	ObjectVo login(String username, String password) throws Exception;

}
