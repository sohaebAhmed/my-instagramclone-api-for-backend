package com.instagram.myClone.service;

import java.util.List;

import com.instagram.myClone.exceptions.StoryException;
import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.Story;


public interface StoryService {

	public Story createStory(Story story, Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;
	
}
