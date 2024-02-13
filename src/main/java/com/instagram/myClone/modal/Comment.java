package com.instagram.myClone.modal;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.instagram.myClone.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id", column=@Column(name="user_id")),
		@AttributeOverride(name="email", column=@Column(name="user_email"))
	})
	private UserDto user;
	
	private String content;
	private LocalDateTime createdAt;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	@Embedded
	@ElementCollection
	private Set<UserDto> likedByUsers = new HashSet();

	public Comment(Integer id, UserDto user, String content, LocalDateTime createdAt, Set<UserDto> likedByUsers) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
		this.likedByUsers = likedByUsers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Set<UserDto> getLikedByUsers() {
		return likedByUsers;
	}

	public void setLikedByUsers(Set<UserDto> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}
	
	
}
