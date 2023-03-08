package com.epee.getinline.controller.error;


import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.dto.APIErrorResponse;
import com.epee.getinline.exception.GeneralException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// APIErrorResponse

@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
    return handleExceptionInternal(e, ErrorCode.VALIDATION_ERROR, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
  }


  /**
   * throw new GeneralException("GeneralException !!!")
   * {
   *     "success": false,
   *     "errorCode": 20000,
   *     "message": "GeneralException !!!"
   * }
   */
  @ExceptionHandler // APIEventController 안에서 GeneralException 를 잡아준다!
  public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
    ErrorCode errorCode = e.getErrorCode();
    HttpStatus status = errorCode.isClientSideError() ?
        HttpStatus.BAD_REQUEST :  // 400
        HttpStatus.INTERNAL_SERVER_ERROR; // 500

    return handleExceptionInternal(e, errorCode, HttpHeaders.EMPTY, status, request);
  }


  /**
   * 일반 예외 처리
   */
  @ExceptionHandler
  public ResponseEntity<Object> exception(Exception e, WebRequest request) {
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    return handleExceptionInternal(e, ErrorCode.INTERNAL_ERROR, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }


  /**
   * This base class provides an @ExceptionHandler method for handling internal Spring MVC exception
   * Object body = null 이기 때문에 오버라이드 필수...
   * protected ResponseEntity<Object> handleExceptionInternal( ...
   * https://github.com/djkeh/get-in-line/blob/feature/%232-api/src/main/java/com/uno/getinline/controller/error/APIExceptionHandler.java
   */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorCode errorCode = status.is4xxClientError() ?
        ErrorCode.SPRING_BAD_REQUEST :  // @Vaild 에러 > 스프링 예외!
        ErrorCode.SPRING_INTERNAL_ERROR;

    return handleExceptionInternal(ex, errorCode, headers, status, request);
  }


  private ResponseEntity<Object> handleExceptionInternal(
      Exception e,
      ErrorCode errorCode,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return super.handleExceptionInternal(
        e,
        APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage(e)),
        headers,
        status,
        request
    );
  }


}


