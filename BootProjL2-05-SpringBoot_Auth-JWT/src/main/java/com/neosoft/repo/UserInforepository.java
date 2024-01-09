package com.neosoft.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.UserInfo;

public interface UserInforepository extends CrudRepository<UserInfo, Integer> {

	@Query("from UserInfo where usn=:usn")
	public Optional<UserInfo> getByUserName(String usn);

}
