package com.instagram.myClone.service;

import com.instagram.myClone.exceptions.CommentException;
import com.instagram.myClone.exceptions.PostException;
import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.Comment;

public interface CommentService {

	public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException;
	
	public Comment findCommentById(Integer commentId) throws CommentException;
	
	public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException; 

	public Comment unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException; 
}
