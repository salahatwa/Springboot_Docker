package com.docker.container.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docker.container.entities.User;


public interface UserService {

	public User save(User user);
	
	public List<User> getAllUsers();
}
