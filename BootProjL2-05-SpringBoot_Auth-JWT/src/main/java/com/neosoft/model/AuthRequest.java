package com.neosoft.model;

public class AuthRequest {
	
	private String usn;
	private String pwd;
	
	public AuthRequest() {
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(String usn, String pwd) {
		super();
		this.usn = usn;
		this.pwd = pwd;
	}
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "AuthRequest [usn=" + usn + ", pwd=" + pwd + "]";
	}
	
	
	

}
