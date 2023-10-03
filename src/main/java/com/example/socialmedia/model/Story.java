package com.example.socialmedia.model;

import java.time.LocalDateTime;

import com.example.socialmedia.dto.UserDto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "stories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Story {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name="user_id")),
    @AttributeOverride(name = "email", column = @Column(name="user_email"))
  })
  private UserDto user;

  @NotNull
  private String image;
  private String caption;
  private LocalDateTime timestamp;
}
