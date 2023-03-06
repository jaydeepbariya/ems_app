package com.jaydeep.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.ems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String username);

}