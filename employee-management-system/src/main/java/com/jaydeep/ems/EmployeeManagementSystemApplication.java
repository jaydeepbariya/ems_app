package com.jaydeep.ems;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jaydeep.ems.entity.Role;
import com.jaydeep.ems.repository.RoleRepository;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
		
		System.out.println("EmployeeManagementSystem Running Fine...");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Role> roles = List.of(new Role(1,"ROLE_ADMIN"),new Role(2,"ROLE_USER"));
		
		List<Role> savedRoles = roleRepository.saveAll(roles);
		
		System.out.println(savedRoles.get(0)+"   "+ savedRoles.get(1));
	}

}
