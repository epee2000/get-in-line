package com.epee.getinline.dto;


import com.epee.getinline.constant.ErrorCode;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED) // 상속 받으면 사용할수 있다.
public class APIErrorResponse {
  private final Boolean success;
  private final Integer errorCode;
  private final String message;

  public static APIErrorResponse of(Boolean success, Integer errorCode, String message) {
    return new APIErrorResponse(success, errorCode, message);
  }

  public static APIErrorResponse of(Boolean success, ErrorCode errorCode) {
    return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage());
  }

  public static APIErrorResponse of(Boolean success, ErrorCode errorCode, Exception e) {
    return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(e));
  }

  public static APIErrorResponse of(Boolean success, ErrorCode errorCode, String message) {
    return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(message));
  }
}
