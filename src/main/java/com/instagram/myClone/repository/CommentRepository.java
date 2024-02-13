package com.instagram.myClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.myClone.modal.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
