package com.example.socialmedia.service;

import java.util.List;

import com.example.socialmedia.exception.StoryException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Story;

public interface IStoryService {
  
  public Story createStory(Story story, long userId) throws UserException, StoryException;
  public List<Story> findStoryByUserId(long userId) throws UserException, StoryException;

}
