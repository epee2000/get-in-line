package com.epee.getinline.service;

import com.epee.getinline.constant.ErrorCode;
import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.dto.EventDTO;
import com.epee.getinline.exception.GeneralException;
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
    try {
      return eventRepository.findEvents(
          placeId,
          eventName,
          eventStatus,
          eventStartDatetime,
          eventEndDatetime
      );
    }
    catch (Exception e) {
      throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
    }
  }

  public Optional<EventDTO> getEvent(Long eventId) {
    try {
      return eventRepository.findEvent(eventId);
    }
    catch (Exception e) {
      throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
    }
  }

  public boolean createEvent(EventDTO eventDTO) {
    try {
      return eventRepository.insertEvent(eventDTO);
    }
    catch (Exception e) {
      throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
    }
  }

  public boolean modifyEvent(Long eventId, EventDTO dto) {
    try {
      return eventRepository.updateEvent(eventId, dto);
    }
    catch (Exception e) {
      throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
    }
  }

  public boolean removeEvent(Long eventId) {
    try {
      return eventRepository.deleteEvent(eventId);
    }
    catch (Exception e) {
      throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
    }
  }
}