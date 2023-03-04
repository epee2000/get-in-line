package com.epee.getinline.controller.error;


import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.dto.APIErrorResponse;
import com.epee.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

  /**
   * throw new GeneralException("GeneralException !!!")
   * {
   *     "success": false,
   *     "errorCode": 20000,
   *     "message": "GeneralException !!!"
   * }
   */
  @ExceptionHandler // APIEventController 안에서 GeneralException 를 잡아준다!
  public ResponseEntity<APIErrorResponse> general(GeneralException e) {
    ErrorCode errorCode = e.getErrorCode();
    HttpStatus status = errorCode.isClientSideError() ?
        HttpStatus.BAD_REQUEST :  // 400
        HttpStatus.INTERNAL_SERVER_ERROR; // 500

    return ResponseEntity
        .status(status)
        .body(APIErrorResponse.of(false, errorCode, errorCode.getMessage(e)));
  }


  @ExceptionHandler
  public ResponseEntity<APIErrorResponse> exception(Exception e) {
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    return ResponseEntity
        .status(status)
        .body(APIErrorResponse.of(false, errorCode, errorCode.getMessage(e)));
  }




}
