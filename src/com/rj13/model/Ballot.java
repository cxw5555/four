package com.rj13.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.UpdateListener;

public class Ballot extends BmobObject {
	private String userid;// 发起人的用户名
	private String title;// 投票标题
	private String detail;// 问题详情
	private String yes;// 选是的人数
	private String no;// 选否的人数

	public Ballot() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getYes() {
		return yes;
	}

	public void setYes(String yes) {
		this.yes = yes;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	
}
