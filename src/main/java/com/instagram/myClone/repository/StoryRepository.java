package com.instagram.myClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.instagram.myClone.modal.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	@Query("select s from story s where s.user.id = :userId")
	List<Story> findAllStoryByUserId(@Param("userId") Integer userId);
}
