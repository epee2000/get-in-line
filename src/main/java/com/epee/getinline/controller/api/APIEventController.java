package com.epee.getinline.controller.api;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.dto.APIDataResponse;
import com.epee.getinline.dto.APIErrorResponse;
import com.epee.getinline.dto.EventRequest;
import com.epee.getinline.dto.EventResponse;
import com.epee.getinline.exception.GeneralException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIEventController {

  @GetMapping("/events")
  public APIDataResponse<List<EventResponse>> getEvents() {

    //throw new GeneralException("GeneralException !!!");   // GeneralException > general 호출 됨
    //throw new RuntimeException("RuntimeException !!!"); // BaseErrorController 호출 됨 (지정하지 않은 에러) 또는

    return APIDataResponse.of(List.of(EventResponse.of(
        1L,
        "오후 운동",
        EventStatus.OPENED,
        LocalDateTime.of(2021, 1, 1, 13, 0, 0),
        LocalDateTime.of(2021, 1, 1, 16, 0, 0),
        0,
        24,
        "마스크 꼭 착용하세요"
    )));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/events")
  public APIDataResponse<Void> createEvent(@RequestBody EventRequest eventRequest) {
    return APIDataResponse.empty();
  }

  @GetMapping("/events/{eventId}")
  public APIDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
    if (eventId.equals(2L)) {
      return APIDataResponse.empty();
    }

    return APIDataResponse.of(EventResponse.of(
        1L,
        "오후 운동",
        EventStatus.OPENED,
        LocalDateTime.of(2021, 1, 1, 13, 0, 0),
        LocalDateTime.of(2021, 1, 1, 16, 0, 0),
        0,
        24,
        "마스크 꼭 착용하세요"
    ));
  }

  @PutMapping("/events/{eventId}")
  public APIDataResponse<Void> modifyEvent(
      @PathVariable Long eventId,
      @RequestBody EventRequest eventRequest
  ) {
    return APIDataResponse.empty();
  }

  @DeleteMapping("/events/{eventId}")
  public APIDataResponse<Void> removeEvent(@PathVariable Long eventId) {
    return APIDataResponse.empty();
  }
}
