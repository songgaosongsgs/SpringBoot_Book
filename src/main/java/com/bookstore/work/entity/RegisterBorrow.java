package com.bookstore.work.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
@TableName("register_borrow")
public class RegisterBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅人id
     */
	private Integer id;
    /**
     * 图书id
     */
	@TableField("book_id")
	private Integer bookId;
    /**
     * 借阅人id
     */
	@TableField("reader_id")
	private Integer readerId;
    /**
     * 借阅人名称
     */
	@TableField("reader_name")
	private String readerName;
    /**
     * 应归还日期
     */
	@TableField("should_date")
	private Date shouldDate;
    /**
     * 借阅人电话
     */
	private String tel;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;

//	实际归还日期
	private Date backTime;
	
	@TableField(exist=false)
	private Integer days;
	
	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * @return the backTime
	 */
	
	public Date getBackTime() {
		return backTime;
	}

	/**
	 * @param backTime the backTime to set
	 */
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	 @JsonFormat(pattern="yyyy-MM-dd")
	public Date getShouldDate() {
		return shouldDate;
	}

	public void setShouldDate(Date shouldDate) {
		this.shouldDate = shouldDate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "RegisterBorrow{" +
			"id=" + id +
			", bookId=" + bookId +
			", readerId=" + readerId +
			", readerName=" + readerName +
			", shouldDate=" + shouldDate +
			", tel=" + tel +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
