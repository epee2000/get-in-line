package com.epee.getinline.controller.error;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class BaseErrorController implements ErrorController {

  /**
   * 브라우저 URL > http://localhost:8888
   * throw new Exception("에러 테스트");
   * This is 500 error page.
   *
   * errorCode: INTERNAL_ERROR (20000)
   * message: Internal Server Error
   */
  @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE) // Accept : text/html
  public ModelAndView errorHtml(HttpServletResponse response) {
    HttpStatus status = HttpStatus.valueOf(response.getStatus());
    ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

    return new ModelAndView(
        "error",
        Map.of(
            "statusCode", status.value(),
            "errorCode", errorCode,
            "message", errorCode.getMessage(status.getReasonPhrase())
        ),
        status
    );
  }

  /**
  @RequestMapping("/error")
  public ModelAndView errorHtml(HttpServletResponse response) {           // 응답 status code 가 들어 있다!
    HttpStatus status = HttpStatus.valueOf(response.getStatus());         // int : response.getStatus(), Get the HTTP status code for this Response.
    ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

    Map<String, Object> map = new HashMap<>();
    map.put("statusCode", status.value());  // Return the integer value of this status code.
    map.put("errorCode", errorCode);
    map.put("message", errorCode.getMessage(status.getReasonPhrase()));

    // Create a new ModelAndView given a view name, model, and HTTP status.
    return new ModelAndView("error", map, status); // ModelAndView 도 status 를 넘겨준다!
  }
*/

  /**
   * PostMan 호출 > GET http://localhost:8888/api/events
   * throw new RuntimeException("RuntimeException !!!"); // 지정하지 않은 에러, BaseErrorController
   * {
   *     "success": false,
   *     "errorCode": 20000,
   *     "message": "internal error"
   * }
   */
  @RequestMapping("/error")
  public ResponseEntity<APIErrorResponse> error(HttpServletResponse response) {
    HttpStatus status = HttpStatus.valueOf(response.getStatus());
    ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

    return ResponseEntity
        .status(status)
        .body(APIErrorResponse.of(false, errorCode));
  }

}
