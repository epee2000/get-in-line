package com.epee.getinline.controller.api;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.dto.APIErrorResponse;
import com.epee.getinline.exception.GeneralException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIEventController {
  @GetMapping("/events")
  public List<String> getEvents() {
    //throw new GeneralException("GeneralException !!!");   // GeneralException > general 호출 됨
    //throw new RuntimeException("RuntimeException !!!"); // BaseErrorController 호출 됨 (지정하지 않은 에러) 또는
    return List.of("event1", "event2");
  }

  @PostMapping("/events")
  public Boolean createEvent() {
    return true;
  }

  @GetMapping("/events/{eventId}")
  public String getEvent(@PathVariable Long eventId) {
   return "event" + eventId;
  }

  @PutMapping("/events/{eventId}")
  public Boolean modifyEvent(@PathVariable Long eventId) {
    return true;
  }

  @DeleteMapping("/events/{eventId}")
  public Boolean removeEvent(@PathVariable Long eventId) {
    return true;
  }

}
