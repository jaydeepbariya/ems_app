package com.jaydeep.ems.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestToken = request.getHeader("Authentication");
		
		//get the token
		String token = null;
		String username = null;
		
		if(requestToken!=null) {
			
			if(requestToken!=null && requestToken.startsWith("Bearer ")) {
				token = requestToken.substring(7);
				try {
					username = jwtTokenHelper.extractUsername(token);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Illegal Argument ");
				}
				catch(MalformedJwtException e) {
					System.out.println("Malformed Jwt Token");
				}
			}
			else {
				System.out.println("Invalid Token and/or doesn't start with Bearer ");
			}
			
		}
		else {
			System.out.println("RequestToken is null and/or AuthenticationContext is not null");
		}
		
		
		//validate the token
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			
			if(jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			else {
				System.out.println("Token Is Invalid");
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

}
