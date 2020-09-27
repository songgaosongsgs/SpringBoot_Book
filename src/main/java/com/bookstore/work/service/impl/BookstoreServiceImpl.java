package com.bookstore.work.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bookstore.work.config.Utils;
import com.bookstore.work.dao.BookstoreDao;
import com.bookstore.work.entity.Bookstore;
import com.bookstore.work.entity.RegisterBorrow;
import com.bookstore.work.service.IBookstoreService;
import com.bookstore.work.service.IRegisterBorrowService;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@Service
public class BookstoreServiceImpl extends ServiceImpl<BookstoreDao, Bookstore> implements IBookstoreService {

	@Autowired
	private IRegisterBorrowService regBooService;

	/**
	 * @throws Exception
	 * @description 借出
	 */
	@Override
	@Transactional
	public boolean gout(String json) throws Exception {
		json = URLDecoder.decode(json, "utf-8");
		RegisterBorrow reg = JSONUtil.toBean(json, RegisterBorrow.class);
		Integer bookId = reg.getBookId();
		Bookstore bookstore = selectById(bookId);
		if (null != bookstore) {
			bookstore.setUpdateTime(new Date());
			bookstore.setBackTime(null);
			bookstore.setStatus(2);
			updateById(bookstore);
			updateAllColumnById(bookstore);
		} else {
			throw new Exception("此图书已经不存在");
		}
		reg.setCreateTime(new Date());
		reg.setUpdateTime(new Date());
		regBooService.insert(reg);

		return true;
	}

	/**
	 * @description 归还图书 修改图书时间状态
	 */
	@Override
	@Transactional
	public boolean gin(Integer id) {
		Bookstore bookstore = selectById(id);
		bookstore.setBackTime(new Date());
		bookstore.setUpdateTime(new Date());
		bookstore.setStatus(1);
		updateById(bookstore);
		EntityWrapper<RegisterBorrow> ew = new EntityWrapper<RegisterBorrow>();
		List<RegisterBorrow> list = regBooService.selectList(ew.eq("book_id", id).orderBy("id", false));
		if (null != list && list.size() > 0) {
			RegisterBorrow borrow = list.get(0);
			borrow.setUpdateTime(new Date());
			borrow.setBackTime(new Date());
			regBooService.updateById(borrow);
		}
		return true;
	}

	/**
	 * @description 查询图书列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page bookList(Page pageObj, String bookName, String author, Integer status, Integer byprice, Integer byday) {
		EntityWrapper<Bookstore> ew = new EntityWrapper<Bookstore>();
		if (StrUtil.isNotBlank(bookName)) {
			ew.like("name", bookName);
		}
		if (StrUtil.isNotBlank(author)) {
			ew.like("author", author);
		}
		if (null != status) {
			ew.eq("status", status);
		}
		if (null != byprice && byprice == 1) {
			ew.orderBy("price");
		}
		if (null != byprice && byprice == 2) {
			ew.orderBy("price", false);
		}

		if (null != byday && byday == 1) {// 正序
			ew.orderBy("status").orderBy("update_time", false);
		} else {// 倒序
			ew.orderBy("status", false).orderBy("update_time");
		}
		// 分页查询
		pageObj = selectPage(pageObj, ew);
		if (null != pageObj && pageObj.getRecords().size() > 0) {
			List<Bookstore> records = pageObj.getRecords();
			for (Bookstore b : records) {
				Double price = b.getPrice();
				if (null == price)
					b.setPrice(Double.parseDouble("0"));
				Double price2 = b.getPrice();
				// 价格转汉字
				String chinese = Utils.toChinese(String.valueOf(Math.round(price2)));
				b.setChprice(chinese);
			}
			return pageObj;
		}
		return pageObj;
	}

	/**
	 * @description 根据ID查询图书详情
	 */
	@Override
	public Bookstore getByid(Integer id) throws Exception {
		Bookstore bookstore = selectById(id);
		if (null != bookstore) {
			return bookstore;
		}
		return null;
	}

	/**
	 * @description 修改图书信息
	 */
	@Override
	@Transactional
	public boolean updateBookByid(Integer id, String json) throws Exception {
		json = URLDecoder.decode(json, "utf-8");
		Bookstore b = JSONUtil.toBean(json, Bookstore.class);
		Bookstore bookstore = selectById(id);
		if (null == bookstore)
			throw new Exception("此图书已经不存在 可能被其他人删除 请重新查询确认");
		b.setUpdateTime(new Date());
		b.setCreateTime(bookstore.getCreateTime());
		b.setBackTime(bookstore.getBackTime());
		b.setId(bookstore.getId());
		b.setPicture(bookstore.getPicture());
		b.setStatus(bookstore.getStatus());
		updateById(b);
		return true;
	}

	/**
	 * @description 删除图书
	 */
	@Override
	@Transactional
	public boolean deleteBookByid(Integer id) throws Exception {
		Bookstore bookstore = selectById(id);
		if (null != bookstore) {
			deleteById(id);
			return true;
		} else {
			throw new Exception("此图书已经不存在 可能被其他人删除 请重新查询确认");
		}
	}

	/**
	 * @description 新增图书
	 */
	@Override
	public boolean addBook(String json) throws Exception {
		json = URLDecoder.decode(json, "utf-8");
		Bookstore b = JSONUtil.toBean(json, Bookstore.class);
		b.setCreateTime(new Date());
		b.setUpdateTime(new Date());
		b.setBackTime(new Date());
		b.setStatus(1);
		b.setPicture("555");
		insert(b);
		return true;
	}

}
