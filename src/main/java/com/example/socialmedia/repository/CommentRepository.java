package com.example.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
  
}
