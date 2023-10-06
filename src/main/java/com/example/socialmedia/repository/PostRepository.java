package com.example.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.socialmedia.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
  @Query("select p from Post p where p.user.id=?1")
  public List<Post> findByUserId(long userId);

  @Query("select p from Post p where p.user.id IN :users ORDER BY p.createdAt DESC")
  public List<Post> findAllPostByUserIds(@Param("users") List<Long> userIds);
}
