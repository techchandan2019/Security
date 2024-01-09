package com.neosoft.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.neosoft.model.UserInfo;

@Component
public class MyUserDetails implements UserDetails{
	
	private String usn;
	private String pwd;
	private List<GrantedAuthority> groles;
	 public MyUserDetails() {
		// TODO Auto-generated constructor stub
	}
	 public MyUserDetails(UserInfo userInfo) {
		 this.usn=userInfo.getUsn();
		 this.pwd=userInfo.getPwd();
		 String roles=userInfo.getRoles();
		 String[] roleArray=roles.split(",");
		
		 this.groles=new ArrayList<>();
		 for(String role:roleArray) {
			 SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role);
			 groles.add(sga);
		 }
	
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return groles;
	}@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pwd;
	}@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usn;
	}@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
