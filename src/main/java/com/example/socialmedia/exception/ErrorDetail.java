package com.example.socialmedia.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetail {
  private String message;
  private String detail;
  private LocalDateTime timestamp;
}
