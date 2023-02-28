package com.epee.getinline.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
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
}
