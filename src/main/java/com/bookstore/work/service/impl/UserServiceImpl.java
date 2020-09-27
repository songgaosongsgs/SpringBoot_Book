package com.bookstore.work.service.impl;

import com.bookstore.work.entity.User;
import com.bookstore.work.config.ObjectVo;
import com.bookstore.work.dao.UserDao;
import com.bookstore.work.service.IUserService;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

	@Override
	public ObjectVo login(String username, String password) throws Exception {
		ObjectVo vo = new ObjectVo();
		if(StrUtil.isBlank(username))
			return vo.error(500, "用户名为空");
		if(StrUtil.isBlank(password))
			return vo.error(500, "密码为空");
		List<User> list = selectList(new EntityWrapper<User>().eq("user_name", username).eq("user_password", password));
		if(null!=list&&list.size()>0){
			User user = list.get(0);
			return vo.ok("登录成功", user);
		}else{
			return vo.error(500, "登录失败");
		}
	}

}
