package com.epee.getinline.controller.api;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.dto.APIDataResponse;
import com.epee.getinline.dto.APIErrorResponse;
import com.epee.getinline.dto.EventRequest;
import com.epee.getinline.dto.EventResponse;
import com.epee.getinline.exception.GeneralException;
import com.epee.getinline.service.EventService;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import org.springframework.web.context.request.WebRequest;


@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class APIEventController {
  private final EventService eventService; // 컨트롤러에 의존성이 추가됨

  @GetMapping("/events")
  public APIDataResponse<List<EventResponse>> getEvents(
      @Positive Long placeId,   // 에러 나면 > ConstraintViolationException
      @Size(min = 2) String eventName,
      EventStatus eventStatus,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
  ) {
    //throw new GeneralException("GeneralException !!!");   // GeneralException > general 호출 됨
    //throw new RuntimeException("RuntimeException !!!"); // BaseErrorController 호출 됨 (지정하지 않은 에러) 또는

    // EventDTO -> EventResponse 변환해야 된다!
    List<EventResponse> eventResponses = eventService.getEvents(
        placeId,
        eventName,
        eventStatus,
        eventStartDatetime,
        eventEndDatetime
    ).stream().map(EventResponse::from).toList();

    return APIDataResponse.of(eventResponses);

    /**
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
     */
  }


  /**
   * @Valid 에러는 ResponseEntityExceptionHandler > MethodArgumentNotValidException.class 에서 잡아 준다.
   * Customize the response for MethodArgumentNotValidException.
   * This method delegates to handleExceptionInternal.
   * @param :
   * ex - the exception
   * headers - the headers to be written to the response
   * status - the selected response status
   * request - the current request
   * Return : a ResponseEntity instance

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      return handleExceptionInternal(ex, null, headers, status, request);
    }
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/events")
  public APIDataResponse<String> createEvent(@Valid @RequestBody EventRequest eventRequest) {
    boolean result = eventService.createEvent(eventRequest.toDTO());

    return APIDataResponse.of(Boolean.toString(result));
  }

  @GetMapping("/events/{eventId}")
  public APIDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
    //EventResponse eventResponse = EventResponse.from(eventService.getEvent(eventId).orElse(null));
    //return APIDataResponse.of(eventResponse);

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
  public APIDataResponse<String> modifyEvent(
      @PathVariable Long eventId,
      @RequestBody EventRequest eventRequest
  ) {
    boolean result = eventService.modifyEvent(eventId, eventRequest.toDTO());

    return APIDataResponse.of(Boolean.toString(result));
  }

  @DeleteMapping("/events/{eventId}")
  public APIDataResponse<String> removeEvent(@PathVariable Long eventId) {
    boolean result = eventService.removeEvent(eventId);

    return APIDataResponse.of(Boolean.toString(result));
  }
}
