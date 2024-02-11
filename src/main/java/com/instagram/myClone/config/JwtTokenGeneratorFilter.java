package com.instagram.myClone.config;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
						.setExpiration(new Date(new Date.getTime()+300000000))
						.signWith(key).compact();
			
			response.setHeader(SecurityContext.HEADER, jtw);
		}
		
		filterChain.doFilter(request, response);
	}
	
	
	public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		
		Set<String> authorities = new HashSet();
		for (GratedAuthority authority : collection) {
			authorities.add(authority.getAuthority());
		}	
		
		return String.join(",", authorities);
	}
	
	protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
		return !req.getServletPath().equals("/signin");
	}
	
}
