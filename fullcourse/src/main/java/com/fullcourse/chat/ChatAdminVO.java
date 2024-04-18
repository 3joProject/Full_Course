package com.fullcourse.chat;

public class ChatAdminVO {
	
	private String adminId;
	private String adminPw;
	
	public ChatAdminVO() {
		// TODO Auto-generated constructor stub
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	@Override
	public String toString() {
		return "ChatAdminVO [adminId=" + adminId + ", adminPw=" + adminPw + "]";
	}
	
	
}
