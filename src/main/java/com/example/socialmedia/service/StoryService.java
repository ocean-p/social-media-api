package com.example.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.exception.StoryException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Story;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.StoryRepository;
import com.example.socialmedia.repository.UserRepository;

@Service
public class StoryService implements IStoryService {

  @Autowired
  private StoryRepository storyRepository;

  @Autowired
  private IUserService userService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Story createStory(Story story, long userId) throws UserException, StoryException {
    User user = userService.findUserById(userId);

    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setUserImage(user.getImage());
    userDto.setUsername(user.getUsername());

    story.setUser(userDto);
    story.setTimestamp(LocalDateTime.now());
    user.getStories().add(story);
    userRepository.save(user);
    
    return storyRepository.save(story);
  }

  @Override
  public List<Story> findStoryByUserId(long userId) throws UserException, StoryException {
    User user = userService.findUserById(userId);
    List<Story> stories = user.getStories();
    // if(stories.size() == 0){
    //   throw new StoryException("This user does not have any stories.");
    // }

    return stories;
  }
  
}
