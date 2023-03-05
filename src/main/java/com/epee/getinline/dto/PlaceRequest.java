package com.epee.getinline.dto;

import com.epee.getinline.constant.PlaceType;

public record PlaceRequest(
    PlaceType placeType,
    String placeName,
    String address,
    String phoneNumber,
    Integer capacity,
    String memo
) {
  public static PlaceRequest of(
      PlaceType placeType,
      String placeName,
      String address,
      String phoneNumber,
      Integer capacity,
      String memo
  ) {
    return new PlaceRequest(placeType, placeName, address, phoneNumber, capacity, memo);
  }
}