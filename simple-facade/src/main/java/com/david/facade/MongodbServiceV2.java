package com.david.facade;

import java.util.List;

import com.david.dto.User;

public interface MongodbServiceV2 {
	
	void mongodbInsertUser(User user);
	
	void mongodbDeleteUser(Long userId);
	
	void mongodbUpdateUser(User user);
	
	User mongodbQueryUser(Long userId);
	
	List<User> mongodbQueryUsers();
	
}
