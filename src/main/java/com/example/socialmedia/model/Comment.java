package com.example.socialmedia.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.socialmedia.dto.UserDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@Getter
@Setter
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name="user_id")),
    @AttributeOverride(name = "email", column = @Column(name="user_email"))
  })
  private UserDto user;

  private String content;

  @Embedded
  @ElementCollection
  private Set<UserDto> likedByUsers = new HashSet<>();

  private LocalDateTime createdAt;
}
