package com.epee.getinline.service;

import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.dto.EventDTO;
import com.epee.getinline.repository.EventRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EventService {
  private final EventRepository eventRepository;
  public List<EventDTO> getEvents(
      Long placeId,
      String eventName,
      EventStatus eventStatus,
      LocalDateTime eventStartDatetime,
      LocalDateTime eventEndDatetime
  ) {
    return eventRepository.findEvents(
        placeId,
        eventName,
        eventStatus,
        eventStartDatetime,
        eventEndDatetime
    );
  }

  public Optional<EventDTO> getEvent(Long eventId) {
    return eventRepository.findEvent(eventId);
    //return Optional.empty();
  }

  public boolean createEvent(EventDTO eventDTO) {
    return eventRepository.insertEvent(eventDTO);
  }

  public boolean modifyEvent(Long eventId, EventDTO dto) {
    return eventRepository.updateEvent(eventId, dto);
  }

  public boolean removeEvent(Long eventId) {
    return eventRepository.deleteEvent(eventId);
  }


}
