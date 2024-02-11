package com.instagram.myClone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.myClone.repository.UserRepository;

@Service
public class UserUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.instagram.myClone.modal.User> opt = userRepository(findByEmail(username));
		
		if (opt.isPresent()) {
			com.instagram.myClone.modal.User user = opt.get();
			
			List<GrantedAutority> authorities = new ArrayList();
			
			return new User(user.getEmail(), user.getPassword(), authorities);
		}
		
		throw new ExceptionBadCredentialsException("user not found with username" + username); 
	}
}
