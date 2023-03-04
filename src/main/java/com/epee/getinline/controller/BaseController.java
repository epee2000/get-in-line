package com.epee.getinline.controller;

import com.epee.getinline.exception.GeneralException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

  @GetMapping("/")
  public String root() throws Exception {
    throw new Exception("에러 테스트 RuntimeException !!!");
    //throw new GeneralException("에러 테스트 GeneralException !!!");

    //return "index";
  }

}
