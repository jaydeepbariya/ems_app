package com.jaydeep.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.ems.dto.UserDTO;
import com.jaydeep.ems.security.CustomUserDetailsService;
import com.jaydeep.ems.security.JwtAuthRequest;
import com.jaydeep.ems.security.JwtAuthResponse;
import com.jaydeep.ems.security.JwtTokenHelper;
import com.jaydeep.ems.service.AuthService;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@PostMapping("registerUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
		
		String result = authService.registerUser(userDTO);
		
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
		
	}
	
	@PostMapping("loginUser")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{ 
		
		authentication(jwtAuthRequest.getEmail(), jwtAuthRequest.getPassword());
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtAuthRequest.getEmail());
		
		String token = jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);		
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	private void authentication(String email, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(email, password);
		
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}
		catch(Exception e) {
			throw new Exception("USER_NOT_AUTHORISED");
		}
	}
}
