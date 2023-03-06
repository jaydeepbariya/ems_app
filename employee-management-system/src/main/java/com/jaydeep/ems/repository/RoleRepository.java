package com.jaydeep.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaydeep.ems.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
