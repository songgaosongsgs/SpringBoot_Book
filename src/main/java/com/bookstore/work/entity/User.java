package com.bookstore.work.entity;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 密码
     */
	@TableField("user_password")
	private String userPassword;
    /**
     * 真实姓名
     */
	@TableField("real_name")
	private String realName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", userName=" + userName +
			", userPassword=" + userPassword +
			", realName=" + realName +
			"}";
	}
}
