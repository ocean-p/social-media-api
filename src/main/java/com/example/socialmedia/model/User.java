package com.example.socialmedia.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

import com.example.socialmedia.dto.UserDto;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String username;
  private String name;
  private String email;
  private String mobile;
  private String website;
  private String bio;
  private String gender;
  private String image;
  private String password;

  @Embedded
  @ElementCollection
  private Set<UserDto> followers = new HashSet<>();

  @Embedded
  @ElementCollection
  private Set<UserDto> followings = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Story> stories = new ArrayList<>();

  @ManyToMany
  private List<Post> savedPosts = new ArrayList<>();

}
