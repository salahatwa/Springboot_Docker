package com.docker.container.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.container.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
