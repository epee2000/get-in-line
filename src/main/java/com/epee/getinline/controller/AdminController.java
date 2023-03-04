package com.epee.getinline.controller;


import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.constant.PlaceType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/admin")
@Controller
public class AdminController {

  @GetMapping("/places")
  public ModelAndView adminPlaces(PlaceType placeType, String placeName, String address) {
    Map<String, Object> map = new HashMap<>();
    map.put("placeType", placeType);
    map.put("placeName", placeName);
    map.put("address", address);

    return new ModelAndView("admin/places", map);
  }

  @GetMapping("/places/{placeId}")
  public ModelAndView adminPlaceDetail(@PathVariable Long placeId) {
    Map<String, Object> map = new HashMap<>();

    return new ModelAndView("admin/place-detail", map);
  }

  @GetMapping("/events")
  public ModelAndView adminEvents(
      Long placeId,
      String eventName,
      EventStatus eventStatus,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
  ) {
    Map<String, Object> map = new HashMap<>();
    map.put("placeName", "place-" + placeId);
    map.put("eventName", eventName);
    map.put("eventStatus", eventStatus);
    map.put("eventStartDatetime", eventStartDatetime);
    map.put("eventEndDatetime", eventEndDatetime);

    return new ModelAndView("admin/events", map);
  }

  @GetMapping("/events/{eventId}")
  public ModelAndView adminEventDetail(@PathVariable Long eventId) {
    Map<String, Object> map = new HashMap<>();

    return new ModelAndView("admin/event-detail", map);
  }

}
