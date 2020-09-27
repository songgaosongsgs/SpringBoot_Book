package com.bookstore.work.service.impl;

import com.bookstore.work.entity.RegisterBorrow;
import com.bookstore.work.dao.RegisterBorrowDao;
import com.bookstore.work.service.IRegisterBorrowService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@Service
public class RegisterBorrowServiceImpl extends ServiceImpl<RegisterBorrowDao, RegisterBorrow>
		implements IRegisterBorrowService {

	/**
	 * @description 图书借还记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page regBorrowList(Page pageObj, Long id) {
		EntityWrapper<RegisterBorrow> ew = new EntityWrapper<RegisterBorrow>();
		if (null != id) {
			ew.eq("book_id", id).orderBy("id", false);
		}
		pageObj = selectPage(pageObj, ew);
		if (null != pageObj && pageObj.getRecords().size() > 0) {
			List<RegisterBorrow> records = pageObj.getRecords();
			for (RegisterBorrow r : records) {
				if (null != r.getBackTime()) {
					long backTime = r.getBackTime().getTime();
					long createTime = r.getCreateTime().getTime();
					int days = (int) ((backTime - createTime) / (1000 * 60 * 60 * 24) + 1);
					r.setDays(days);
					System.err.println(days);
				} else {
					long time = new Date().getTime();
					long createTime = r.getCreateTime().getTime();
					int days = (int) ((time - createTime) / (1000 * 60 * 60 * 24) + 1);
					r.setDays(days);
					System.err.println(days);
				}
			}
			return pageObj;
		}
		return pageObj;
	}

	/**
	 * @description 最近借还信息
	 */
	@Override
	public RegisterBorrow getByBookId(Long id) {

		EntityWrapper<RegisterBorrow> ew = new EntityWrapper<RegisterBorrow>();
		if (null != id) {
			ew.eq("book_id", id).orderBy("id", false);
		}
		List<RegisterBorrow> list = selectList(ew);
		if (null != list && list.size() > 0) {
			RegisterBorrow r = list.get(0);
			if (null != r.getBackTime()) {
				long backTime = r.getBackTime().getTime();
				long createTime = r.getCreateTime().getTime();
				int days = (int) ((backTime - createTime) / (1000 * 60 * 60 * 24) + 1);
				r.setDays(days);
				System.err.println(days);
			} else {
				long time = new Date().getTime();
				long createTime = r.getCreateTime().getTime();
				int days = (int) ((time - createTime) / (1000 * 60 * 60 * 24) + 1);
				r.setDays(days);
				System.err.println(days);
			}
			return r;
		}
		return null;
	}

}
