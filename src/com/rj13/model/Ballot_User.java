package com.rj13.model;

import cn.bmob.v3.BmobObject;

public class Ballot_User extends BmobObject {

	private String BallotDetail;
	private String User;
	private String P_User;
	private String BallotInfo;

	public Ballot_User() {

	}

	public String getBallotDetail() {
		return BallotDetail;
	}

	public void setBallotDetail(String ballotDetail) {
		BallotDetail = ballotDetail;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getP_User() {
		return P_User;
	}

	public void setP_User(String p_User) {
		P_User = p_User;
	}

	public String getBallotInfo() {
		return BallotInfo;
	}

	public void setBallotInfo(String ballotInfo) {
		BallotInfo = ballotInfo;
	}

}
