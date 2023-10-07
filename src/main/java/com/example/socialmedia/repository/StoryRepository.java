package com.example.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.socialmedia.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
  
  @Query("select s from Story s where s.user.id = :userId")
  List<Story> findAllStoryByUserId(@Param("userId") long userId);

}
