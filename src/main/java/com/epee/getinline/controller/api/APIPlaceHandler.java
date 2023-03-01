package com.epee.getinline.controller.api;


import java.net.URI;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class APIPlaceHandler {

  public ServerResponse getPlaces(ServerRequest request) {
    return ServerResponse.ok().body(List.of("place1", "place2"));
  }

  public ServerResponse createPlace(ServerRequest request) {
    return ServerResponse.created(URI.create("/api/places/1")).body(true);
  }

  public ServerResponse getPlace(ServerRequest request) {
    return ServerResponse.ok().body("place" + request.pathVariable("placeId"));
  }

  public ServerResponse modifyPlace(ServerRequest request) {
    return ServerResponse.ok().body(true);
  }

  public ServerResponse removePlace(ServerRequest request) {
    return ServerResponse.ok().body(true);
  }
}
