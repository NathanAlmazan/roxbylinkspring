package com.information.roxbylink.events.mappers;

import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.repositories.EventsByDatePrj;

import java.util.List;

public interface EventByDateMapper {
    EventsByDateDto eventsByDateDto(EventsByDatePrj event);
    List<EventsByDateDto> eventsByDateListToDto(List<EventsByDatePrj> events);
}
