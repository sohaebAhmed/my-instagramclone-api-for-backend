package com.instagram.myClone.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader(SecurityContext.HEADER);
		
		if (jwt != null) {
			try {
				jwt = jwt.substring(7);
				
				SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
				
				Claims claims = Jwts.parseBuilder().setSigninKey(key).parseClaimsJws(jwt).getBody();
				
				String username = String.valueOf(claims.get("username"));
				
				String authorities = (String) claims.get("authorities");
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSepratedStringToAuthorityList(authorities);
				
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, auths);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token...");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
		return req.getServletPath().equals("/signin");
	}

}
