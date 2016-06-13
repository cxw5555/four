package com.rj13.model;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
	private String UserName;// 账号，和后面的id是一样的
	private String UserPwd;
	private String NickName;// 昵称
	private String MailAddress;
	private String Sex;
	private String Phone;
	private String Score;
	private String QianDao;

	public User() {
	}

	public User(String UserName) {
		this.UserName = UserName;
	}

	public String getQianDao() {
		return QianDao;
	}

	public void setQianDao(String qianDao) {
		QianDao = qianDao;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getMailAddress() {
		return MailAddress;
	}

	public void setMailAddress(String mailAddress) {
		MailAddress = mailAddress;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPwd() {
		return UserPwd;
	}

	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}

}
