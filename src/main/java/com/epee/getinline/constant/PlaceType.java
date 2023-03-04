package com.epee.getinline.constant;

import java.util.Arrays;

public enum PlaceType {
  COMMON("C", "공공"),
  SPORTS("S", "스포츠"),
  RESTAURANT("R", "음식"),
  PARTY("P", "파티"),
  UNKNOWN("U", "모름");

  private String code;
  private String desc;

  PlaceType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return this.code;
  }

  public String geDesc() {
    return this.desc;
  }

  public static PlaceType ofCode(String code){
    return Arrays.stream(PlaceType.values())
        .filter(e -> e.getCode().equals(code))
        .findAny()
        .orElse(PlaceType.UNKNOWN);
  }

}
