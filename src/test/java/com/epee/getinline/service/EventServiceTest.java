package com.epee.getinline.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.epee.getinline.constant.EventStatus;
import com.epee.getinline.dto.EventDTO;
import com.epee.getinline.repository.EventRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // junit5
class EventServiceTest {
  @InjectMocks
  private EventService eventService; // Mock 을 주입 할 Service (테스트 하고자 하는 service)
  @Mock
  private EventRepository eventRepository; // Mock 으로 주입 할 Repository

  // eventRepository 를 eventService 에 주입한다!


  @DisplayName("검색 조건 없이 이벤트를 검색하면, 전체 결과를 출력하여 보여준다.")
  @Test
  void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
    // given
    given(eventRepository.findEvents(null, null, null, null, null))
        .willReturn(List.of(
            createEventDTO(1L, "오전 운동", true),
            createEventDTO(1L, "오후 운동", false)
        ));  // 위 정의된 2개 리스트를 내보내 줘라.. 실제 Repository 는 구현되어 있지 않지만 데이터를 리턴하는것 처럼 사전에 가상 환경을 만든다!


    // when
    List<EventDTO> list
        = eventService.getEvents(null, null, null, null, null);

    // then
    assertThat(list).hasSize(2);

    // == then(eventRepository).should(),findEvents
    verify(eventRepository).findEvents(null, null, null, null, null);
  }

  @DisplayName("검색 조건과 함께 이벤트를 검색하면, 검색 결과를 출력하여 보여준다.")
  @Test
  void givenSearchParameters_whenSearchingEvents_thenReturnsEventList() {
    // given
    Long placeId = 1L;
    String eventName = "오전 운동";
    EventStatus eventStatus = EventStatus.OPENED;
    LocalDateTime eventStartDatetime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
    LocalDateTime eventEndDatetime = LocalDateTime.of(2021, 1, 2, 0, 0, 0);

    // 검색어 넣어주고 데이터가 1개만 리턴되는 환경 (예상 되는 상황!)
    given(eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime))
        .willReturn(List.of(
            createEventDTO(1L, "오전 운동", eventStatus, eventStartDatetime, eventEndDatetime)
        ));


    // when
    List<EventDTO> list
        = eventService.getEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);

    // then
    assertThat(list)
        .hasSize(1)
        .allSatisfy(event -> { // 1개가 아닌 여러개(n)가 나올수 있다는 가정하에... 여려개 리스트가 조건 모두 만족시키는지...
          assertThat(event)
              .hasFieldOrPropertyWithValue("placeId", placeId)
              .hasFieldOrPropertyWithValue("eventName", eventName)
              .hasFieldOrPropertyWithValue("eventStatus", eventStatus);
          assertThat(event.eventStartDatetime()).isAfterOrEqualTo(eventStartDatetime);
          assertThat(event.eventEndDatetime()).isBeforeOrEqualTo(eventEndDatetime);
        });

    // 아래 파라미터로 호출 되었다를 검증한다!
    verify(eventRepository).findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
  }




  @DisplayName("이벤트 ID로 존재하는 이벤트를 조회하면, 해당 이벤트 정보를 출력하여 보여준다.")
  @Test
  void givenEventId_whenSearchingExistingEvent_thenReturnsEvent() {
    // Given
    long eventId = 1L;
    EventDTO eventDTO = createEventDTO(1L, "오전 운동", true);

    given(eventRepository.findEvent(eventId)).willReturn(Optional.of(eventDTO));

    // When
    Optional<EventDTO> result = eventService.getEvent(eventId);

    // Then
    assertThat(result).hasValue(eventDTO);
    verify(eventRepository).findEvent(eventId);
  }

  @DisplayName("이벤트 ID로 이벤트를 조회하면, 빈 정보를 출력하여 보여준다.")
  @Test
  void givenEventId_whenSearchingNonexistentEvent_thenReturnsEmptyOptional() {
    // Given
    long eventId = 2L;
    given(eventRepository.findEvent(eventId)).willReturn(Optional.empty());

    // When
    Optional<EventDTO> result = eventService.getEvent(eventId);

    // Then
    assertThat(result).isEmpty();
    verify(eventRepository).findEvent(eventId);
  }

  @DisplayName("이벤트 정보를 주면, 이벤트를 생성하고 결과를 true 로 보여준다.")
  @Test
  void givenEvent_whenCreating_thenCreatesEventAndReturnsTrue() {
    // Given
    EventDTO dto = createEventDTO(1L, "오후 운동", false);
    given(eventRepository.insertEvent(dto)).willReturn(true);

    // When
    boolean result = eventService.createEvent(dto);

    // Then
    assertThat(result).isTrue();
    verify(eventRepository).insertEvent(dto);
  }

  @DisplayName("이벤트 정보를 주지 않으면, 생성 중단하고 결과를 false 로 보여준다.")
  @Test
  void givenNothing_whenCreating_thenAbortCreatingAndReturnsFalse() {
    // Given
    given(eventRepository.insertEvent(null)).willReturn(false);

    // When
    boolean result = eventService.createEvent(null);

    // Then
    assertThat(result).isFalse();
    verify(eventRepository).insertEvent(null);

  }

  @DisplayName("이벤트 ID와 정보를 주면, 이벤트 정보를 변경하고 결과를 true 로 보여준다.")
  @Test
  void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue() {
    // Given
    long eventId = 1L;
    EventDTO dto = createEventDTO(1L, "오후 운동", false);
    given(eventRepository.updateEvent(eventId, dto)).willReturn(true);

    // When
    boolean result = eventService.modifyEvent(eventId, dto);

    // Then
    assertThat(result).isTrue();
    verify(eventRepository).updateEvent(eventId, dto);

  }

  @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 변경 중단하고 결과를 false 로 보여준다.")
  @Test
  void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse() {
    // Given
    EventDTO dto = createEventDTO(1L, "오후 운동", false);
    given(eventRepository.updateEvent(null, dto)).willReturn(false);

    // When
    boolean result = eventService.modifyEvent(null, dto);

    // Then
    assertThat(result).isFalse();
    verify(eventRepository).updateEvent(null, dto);
  }

  @DisplayName("이벤트 ID만 주고 변경할 정보를 주지 않으면, 이벤트 정보 변경 중단하고 결과를 false 로 보여준다.")
  @Test
  void givenEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
    // Given
    long eventId = 1L;
    given(eventRepository.updateEvent(eventId, null)).willReturn(false);

    // When
    boolean result = eventService.modifyEvent(eventId, null);

    // Then
    assertThat(result).isFalse();
    verify(eventRepository).updateEvent(eventId, null);
  }

  @DisplayName("이벤트 ID를 주면, 이벤트 정보를 삭제하고 결과를 true 로 보여준다.")
  @Test
  void givenEventId_whenDeleting_thenDeletesEventAndReturnsTrue() {
    // Given
    long eventId = 1L;
    given(eventRepository.deleteEvent(eventId)).willReturn(true);

    // When
    boolean result = eventService.removeEvent(eventId);

    // Then
    assertThat(result).isTrue();
    verify(eventRepository).deleteEvent(eventId);
  }

  @DisplayName("이벤트 ID를 주지 않으면, 삭제 중단하고 결과를 false 로 보여준다.")
  @Test
  void givenNothing_whenDeleting_thenAbortsDeletingAndReturnsFalse() {
    // Given
    given(eventRepository.deleteEvent(null)).willReturn(false);

    // When
    boolean result = eventService.removeEvent(null);

    // Then
    assertThat(result).isFalse();
    verify(eventRepository).deleteEvent(null);
  }

  private EventDTO createEventDTO(
      long placeId,
      String eventName,
      EventStatus eventStatus,
      LocalDateTime eventStartDateTime,
      LocalDateTime eventEndDateTime
  ) {
    return EventDTO.of( // new EventDTO
        placeId,
        eventName,
        eventStatus,
        eventStartDateTime,
        eventEndDateTime,
        0,
        24,
        "마스크 꼭 착용하세요",
        LocalDateTime.now(),
        LocalDateTime.now()
    );
  }

  private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning) {
    String hourStart = isMorning ? "09" : "13";
    String hourEnd = isMorning ? "12" : "16";

    return createEventDTO(
        placeId,
        eventName,
        EventStatus.OPENED,
        LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourStart)),
        LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourEnd))
    );
  }





}