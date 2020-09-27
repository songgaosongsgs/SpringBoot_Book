package com.bookstore.work.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sgs
 * @since 2018-06-17
 */
public class Bookstore implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 出版社
     */
	private String dublisher;
	/**
     * 作者
     */
	private String name;
    /**
     * 作者
     */
	private String author;
    /**
     * ISBN号
     */
	private String isbn;
    /**
     * 图书简介
     */
	private String description;
    /**
     * 数据为图片地址
     */
	private String picture;
    /**
     * 借阅状态 1未借出 2已借出
     */
	private Integer status;
    /**
     * 页数
     */
	private Integer pages;
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
	
	private Double price;
	
    /**
     * 修改时间
     */
	@TableField("back_time")
	private Date backTime;
	
//	中文价格
	@TableField(exist=false)
	private String chprice;


	/**
	 * @return the chprice
	 */
	public String getChprice() {
		return chprice;
	}

	/**
	 * @param chprice the chprice to set
	 */
	public void setChprice(String chprice) {
		this.chprice = chprice;
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

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDublisher() {
		return dublisher;
	}

	public void setDublisher(String dublisher) {
		this.dublisher = dublisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
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
		return "Bookstore{" +
			"id=" + id +
			", dublisher=" + dublisher +
			", author=" + author +
			", isbn=" + isbn +
			", description=" + description +
			", picture=" + picture +
			", status=" + status +
			", pages=" + pages +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
