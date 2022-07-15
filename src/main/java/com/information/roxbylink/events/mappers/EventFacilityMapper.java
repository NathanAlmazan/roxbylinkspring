package com.information.roxbylink.events.mappers;

import com.information.roxbylink.events.dto.EventFacilityDto;
import com.information.roxbylink.events.models.EventFacility;

import java.util.List;

public interface EventFacilityMapper {
    EventFacilityDto eventFacilityToDto(EventFacility facility);
    List<EventFacilityDto> eventFacilityListToDto(List<EventFacility> facilities);
}
