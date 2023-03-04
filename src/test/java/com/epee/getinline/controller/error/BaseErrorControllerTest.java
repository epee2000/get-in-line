package com.epee.getinline.controller.error;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(BaseErrorController.class)
class BaseErrorControllerTest {
  private final MockMvc mvc;

  public BaseErrorControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[view][GET] 에러 페이지 - 페이지 없음")
  @Test
  void givenNothing_whenRequestingRgivenWrongURI_whenRequestingPage_thenReturns404ErrorPageootPage_thenReturnsIndexPage() throws Exception {
    // given

    //Expected :200
    //Actual   :404

    // when & then
    mvc.perform(get("/wrong-uri"))
        .andExpect(status().isNotFound())
        .andDo(print());
  }



}