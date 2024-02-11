package com.instagram.myClone.config;

import java.io.IOException;
import java.sql.Date;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
			
			String jtw = Jwts.builder()
						.setIssuer("instagram")
						.setIssueAt(new Date())
						.claim("authorities", populateAuthorities(authentication.getAuthorities()))
						.claim("username", authentication.getName())
						.claim();
		}
	}
	
}
