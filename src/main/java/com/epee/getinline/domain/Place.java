package com.epee.getinline.domain;

import com.epee.getinline.constant.PlaceType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Place {
  private Long id;

  private PlaceType placeType;
  private String placeName;
  private String address;
  private String phoneNumber;
  private Integer capacity;
  private String memo;

  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
