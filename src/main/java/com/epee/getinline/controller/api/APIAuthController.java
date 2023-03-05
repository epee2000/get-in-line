package com.epee.getinline.controller.api;


import com.epee.getinline.dto.APIDataResponse;
import com.epee.getinline.dto.AdminRequest;
import com.epee.getinline.dto.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIAuthController {
  @GetMapping("/sign-up")
  public String signup() {
    return "signup";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/sign-up")
  public APIDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
    return APIDataResponse.empty();
  }

  @PostMapping("/login")
  public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
    return APIDataResponse.empty();
  }


}
