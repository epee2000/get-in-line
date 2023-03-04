package com.epee.getinline.dto;

import com.epee.getinline.constant.PlaceType;

public record PlaceDto(
    Long id,
    PlaceType placeType,
    String placeName,
    String address,
    String phoneNumber,
    Integer capacity,
    String memo
) {
  public static PlaceDto of(
      Long id,
      PlaceType placeType,
      String placeName,
      String address,
      String phoneNumber,
      Integer capacity,
      String memo
  ) {
    return new PlaceDto(id, placeType, placeName, address, phoneNumber, capacity, memo);
  }

}
