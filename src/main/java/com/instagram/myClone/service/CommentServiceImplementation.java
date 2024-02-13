package com.instagram.myClone.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.myClone.dto.UserDto;
import com.instagram.myClone.exceptions.CommentException;
import com.instagram.myClone.exceptions.PostException;
import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.Comment;
import com.instagram.myClone.modal.Post;
import com.instagram.myClone.modal.User;
import com.instagram.myClone.repository.CommentRepository;
import com.instagram.myClone.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException {

		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.setUser(userDto);		
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment createdComment = commentRepository.save(comment);
		
		post.getComments().add(createdComment);
		
		postRepository.save(post);	
		
		return createdComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws CommentException {
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if (opt.isPresent()) {
			return opt.get();
		}
		
		throw new CommentException("Comment doesn't exists with id : "+commentId);
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException {

		User user = userService.findUserById(userId);
		Comment comment = findCommentById(commentId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.getLikedByUsers().add(userDto);
		
		return commentRepository.save(comment);
	}

	@Override
	public Comment unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Comment comment = findCommentById(commentId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.getLikedByUsers().remove(userDto);
		
		return commentRepository.save(comment);
	}

}
