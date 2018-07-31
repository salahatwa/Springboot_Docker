package com.docker.container.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.container.entities.User;
import com.docker.container.repo.UserRepo;
import com.docker.container.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User save(User user) {
		return userRepo.save(user);
	}

}
