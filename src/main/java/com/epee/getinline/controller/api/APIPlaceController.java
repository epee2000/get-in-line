package com.epee.getinline.controller.api;

import com.epee.getinline.constant.PlaceType;
import com.epee.getinline.dto.APIDataResponse;
import com.epee.getinline.dto.PlaceDTO;
import com.epee.getinline.dto.PlaceRequest;
import com.epee.getinline.dto.PlaceResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

  @GetMapping("/places")
  public APIDataResponse<List<PlaceResponse>> getPlaces() {
    return APIDataResponse.of(
                              List.of(
                                        /*PlaceDto.of(
                                                  1L,
                                                  PlaceType.COMMON,
                                                  "랄라배드민턴장",
                                                  "서울시 강남구 강남대로 1234",
                                                  "010-1234-5678",
                                                  30,
                                                  "신장개업"
                                        )*/
                                  PlaceResponse.of(
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


  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/places")
  public APIDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
    return APIDataResponse.empty();
  }

  @GetMapping("/places/{placeId}")
  public APIDataResponse<PlaceResponse> getPlace(@PathVariable Long placeId) {
    if (placeId.equals(2L)) {
      return APIDataResponse.empty();
    }

    return APIDataResponse.of(
        PlaceResponse.of(
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
  public APIDataResponse<Void> modifyPlace(
      @PathVariable Long placeId,
      @RequestBody PlaceRequest placeRequest
  ) {
    return APIDataResponse.empty();
  }

  @DeleteMapping("/places/{placeId}")
  public APIDataResponse<Void> removePlace(@PathVariable Long placeId) {
    return APIDataResponse.empty();
  }


}
