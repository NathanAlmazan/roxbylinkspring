package com.information.roxbylink.events.mappers;

import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.models.Event;

import java.util.List;

public interface EventMapper {
    EventDto eventToDto(Event event);
    List<EventDto> eventListToDto(List<Event> events);
}
