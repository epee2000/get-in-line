package com.epee.getinline.domain;

import com.epee.getinline.constant.EventStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Event {
  private Long id;

  private Long placeId;
  private String eventName;
  private EventStatus eventStatus;
  private LocalDateTime eventStartDatetime;
  private LocalDateTime eventEndDatetime;
  private Integer currentNumberOfPeople;
  private Integer capacity;
  private String memo;

  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
