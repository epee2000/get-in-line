package com.epee.getinline.dto;

import com.epee.getinline.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse<T> extends APIErrorResponse {
  private final Object data;

  private APIDataResponse(Boolean success, Integer errorCode, String message, Object data) {
    super(true, errorCode, message);
    this.data = data;
  }

  private static APIDataResponse of(Boolean success, Integer errorCode, String message, Object data) {
    return new APIDataResponse(success, errorCode, message, data);
  }


  /**

  private final T data;

  private APIDataResponse(T data) {
    super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
    this.data = data;
  }

  public static <T> APIDataResponse<T> of(T data) {
    return new APIDataResponse<>(data); // success, errorCode, message, data
  }

  public static <T> APIDataResponse<T> empty() {
    return new APIDataResponse<>(null);
  }

   */

}
