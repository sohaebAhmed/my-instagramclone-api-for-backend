package com.instagram.myClone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.instagram.myClone.dto.UserDto;
import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.User;
import com.instagram.myClone.repository.UserRepository;

public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) throws UserException {

		Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if (isEmailExist.isPresent()) {
			throw new UserException("Email is already exist");
		}
		
		Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
		
		if (isUsernameExist.isPresent()) {
			throw new UserException("Username is already exist");
		}
		
		if (user.getEmail() == null || user.getPassword() == null || user.getUsername() == null || user.getName() == null) {
			throw new UserException("All fields are required");
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());
		
		return userRepository.save(newUser);
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		 
		Optional<User> opt = userRepository.findById(userId);
		
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not exists with id: " +userId);
		
	}

	@Override
	public User findUserProfile(String token) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException {


		User reqUser = findUserById(reqUserId);
		User followUser = findUserById(followUserId);
		
		UserDto follower = new UserDto();
		
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUsername(reqUser.getUsername());
		follower.setUserImage(reqUser.getUsername());
		
		UserDto following = new UserDto();
		
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserImage(follower.getUserImage());
		following.setUsername(follower.getUsername());
		following.setName(follower.getName());
		
		reqUser.getFollowing().add(following);
		followUser.getFollower().add(follower);
		
		userRepository.save(followUser);
		userRepository.save(reqUser);		
		
		return "you are following "+followUser.getUsername();
	}

	@Override
	public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException {

		User reqUser = findUserById(reqUserId);
		User followUser = findUserById(followUserId);
		
		UserDto follower = new UserDto();
		
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUsername(reqUser.getUsername());
		follower.setUserImage(reqUser.getUsername());
		
		UserDto following = new UserDto();
		
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserImage(follower.getUserImage());
		following.setUsername(follower.getUsername());
		following.setName(follower.getName());
		
		reqUser.getFollowing().remove(following);
		followUser.getFollower().remove(follower);
		
		userRepository.save(followUser);
		userRepository.save(reqUser);		
		
		return "you have unfollowed "+followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
