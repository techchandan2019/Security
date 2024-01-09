package com.neosoft.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neosoft.model.UserInfo;
import com.neosoft.repo.UserInforepository;

@Service
public class UserInfoDetailsService implements UserDetailsService{
	
	@Autowired
	private UserInforepository repo;
	@Autowired
	private MyUserDetails myUserDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> optUserInfo=repo.getByUserName(username);
	
		return new MyUserDetails(optUserInfo.get());
	}

}
