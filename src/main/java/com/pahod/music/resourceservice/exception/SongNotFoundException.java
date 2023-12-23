package com.pahod.music.resourceservice.exception;

public class SongNotFoundException extends RuntimeException {
  public SongNotFoundException(String message) {
    super(message);
  }
}
