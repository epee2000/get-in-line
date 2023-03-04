package com.epee.getinline.controller.error;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.exception.GeneralException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


// 전체 Controller의 동작(Exception) 를 감사한다.
@ControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler
  public ModelAndView general(GeneralException e) {
    ErrorCode errorCode = e.getErrorCode();
    HttpStatus status = errorCode.isClientSideError() ?
        HttpStatus.BAD_REQUEST :  // 400
        HttpStatus.INTERNAL_SERVER_ERROR; // 500

    return new ModelAndView(
        "error",
        Map.of(
            "statusCode", status.value(),
            "errorCode", errorCode,
            "message", errorCode.getMessage(e)
        ),
        status
    );
  }

  @ExceptionHandler
  public ModelAndView exception(Exception e) {
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    return new ModelAndView(
        "error",
        Map.of(
            "statusCode", status.value(),
            "errorCode", errorCode,
            "message", errorCode.getMessage(e)
        ),
        status
    );
  }


}
