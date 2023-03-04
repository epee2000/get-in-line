package com.epee.getinline.controller.api;

import com.epee.getinline.constant.PlaceType;
import com.epee.getinline.dto.APIDataResponse;
import com.epee.getinline.dto.PlaceDto;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

  @GetMapping("/places")
  public APIDataResponse<List<PlaceDto>> getPlaces() {
    return APIDataResponse.of(
                              List.of(
                                        PlaceDto.of(
                                                  1L,
                                                  PlaceType.COMMON,
                                                  "랄라배드민턴장",
                                                  "서울시 강남구 강남대로 1234",
                                                  "010-1234-5678",
                                                  30,
                                                  "신장개업"
                                        )
                              )
    );
  }


  @PostMapping("/places")
  public Boolean createPlace() {
    return true;
  }

  @GetMapping("/places/{placeId}")
  public APIDataResponse<PlaceDto> getPlace(@PathVariable Long placeId) {
    if (placeId.equals(2L)) {
      return APIDataResponse.empty();
    }

    return APIDataResponse.of(
        PlaceDto.of(
            1L,
            PlaceType.COMMON,
            "랄라배드민턴장",
            "서울시 강남구 강남대로 1234",
            "010-1234-5678",
            30,
            "신장개업"
        )
    );
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
