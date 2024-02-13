package com.instagram.myClone.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.instagram.myClone.dto.UserDto;
import com.instagram.myClone.exceptions.StoryException;
import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.Story;
import com.instagram.myClone.modal.User;
import com.instagram.myClone.repository.StoryRepository;
import com.instagram.myClone.repository.UserRepository;

public class StoryServiceImplementation implements StoryService {

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public Story createStory(Story story, Integer userId) throws UserException {
		User user = userService.findUserById(userId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUsername(user.getUsername());
		userDto.setUserImage(user.getImage());
		
		story.setUser(userDto);
		
		story.setTimestamp(LocalDateTime.now());
		
		user.getStories().add(story);
		
		return storyRepository.save(story);		
	}
	
	
	public List<Story> findStoryById(Integer userId) throws StoryException, UserException {
		
		User user = userService.findUserById(userId);
		List<Story> stories = user.getStories();
		
		if (stories.size() == 0) {
			throw new StoryException("No stories by this user"); 
		}
		
		return stories;
	}


	@Override
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
