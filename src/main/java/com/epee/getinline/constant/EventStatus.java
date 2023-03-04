package com.epee.getinline.constant;

import java.util.Arrays;

public enum EventStatus {
  PENDING("P", "대기"),
  OPENED("O", "오픈"),
  CLOSED("C", "종료"),
  CANCELLED("D", "취소"),
  ABORTED("A", "중단"),
  UNKNOWN("U", "모름");

  private String code;
  private String desc;

  EventStatus(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return this.code;
  }

  public String geDesc() {
    return this.desc;
  }

  public static EventStatus ofCode(String code){
    return Arrays.stream(EventStatus.values())
        .filter(e -> e.getCode().equals(code))
        .findAny()
        .orElse(EventStatus.UNKNOWN);
  }

}
