package com.example.socialmedia.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.exception.CommentException;
import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Comment;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.repository.PostRepository;

@Service
public class CommentService implements ICommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private IUserService userService;

  @Autowired
  private IPostService postService;

  @Autowired
  private PostRepository postRepository;

  @Override
  public Comment createComment(Comment comment, long postId, long userId) throws UserException, PostException {
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
  public Comment findCommentById(long commentId) throws CommentException {
    Optional<Comment> opt = commentRepository.findById(commentId);
    if(opt.isPresent()){
      return opt.get();
    }
    throw new CommentException("Comment not found with id:" + commentId);
  }

  @Override
  public Comment likeComment(long commentId, long userId) throws CommentException, UserException {
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
  public Comment unlikeComment(long commentId, long userId) throws CommentException, UserException {
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
