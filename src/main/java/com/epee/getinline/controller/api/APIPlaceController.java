package com.epee.getinline.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/api")
//@RestController
public class APIPlaceController {

  @GetMapping("/places")
  public List<String> getPlaces() {
    return List.of("place1", "place2");
  }

  @PostMapping("/places")
  public Boolean createPlace() {
    return true;
  }

  @GetMapping("/places/{placeId}")
  public String getPlace(@PathVariable Long placeId) {
    return "place" + placeId;
  }

  @PutMapping("/places/{placeId}")
  public Boolean modifyPlace(@PathVariable Long placeId) {
    return true;
  }

  @DeleteMapping("/places/{placeId}")
  public Boolean removePlace(@PathVariable Long placeId) {
    return true;
  }


}
