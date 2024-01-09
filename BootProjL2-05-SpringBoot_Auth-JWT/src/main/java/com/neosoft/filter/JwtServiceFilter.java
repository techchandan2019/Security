package com.neosoft.filter;

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

import com.neosoft.service.JWTService;
import com.neosoft.service.UserInfoDetailsService;

@Component
public class JwtServiceFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;
	@Autowired
	private UserInfoDetailsService userinfoDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String userName=null;
		//token===>Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGFuZGFuIiwiaWF0IjoxNjgzMDk0MzI3LCJleHAiOjE2ODMwOTYxMjd9.MVN8ThZ2pnsEYkC5lxr9AgE9g1EnRymDu6cLWJQPSSU
		if(authHeader!=null && authHeader.startsWith("Bearer")) {
			token=authHeader.substring(7).trim();
			System.out.println("==="+authHeader+"=====");
			System.out.println("==="+token+"=====");
			userName=jwtService.extractUsername(token);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userinfoDetailsService.loadUserByUsername(userName);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
