package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.USER_INFO;

public interface userInfo extends CrudRepository<USER_INFO,String>{
	
	@Query(value = "SELECT  * FROM user_Info WHERE username = ?1 ", nativeQuery = true)
	List<USER_INFO> findByUserName(String userName);
}
