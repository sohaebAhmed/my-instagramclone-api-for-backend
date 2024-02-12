package com.instagram.myClone.security;

import javax.crypto.SecretKey;

import com.instagram.myClone.config.SecurityContext;

public class JwtTokenProvider {

	public JwtTokenClaims getClaimsFromToken(String token) {
		
		SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
		
		Claims claims = Jwts.parser().setSigninKey(key).parseClaimsJwt(token).gtBody();
		
		String username = String.valueOf(claims.get("username"));
		
		JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
		
		jwtTokenClaims.setUsername(username);
		
		return jwtTokenClaims;
	}
}
