package com.demo.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.config.JwtTokenUtil;
import com.demo.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Component
@Data
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();	
		
	final String requestTokenHeader = request.getHeader("Authorization");
	String username = null;
	String jwtToken = null;
	
	if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
		jwtToken = requestTokenHeader.substring(7);
		try {
			username = jwtTokenUtil.getUserNameFromToken(jwtToken);
		
		} catch (IllegalArgumentException e) {
			logger.error("No se puede acceder al token");
		} catch (ExpiredJwtException e) {
			logger.error("El token se encuentra expirado");
		} 
		
	} else {
		logger.error("El Token no comienza con Bearer");
	}
	
	
	if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		
		if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
		
		}
	}
	
	filterChain.doFilter(request, response);

}
}
