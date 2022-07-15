package com.information.roxbylink.events.services;

import com.information.roxbylink.events.dto.EventFacilityDto;
import com.information.roxbylink.events.mappers.EventsMapperImpl;
import com.information.roxbylink.events.repositories.EventFacilityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventFacilityServices {
    private final EventFacilityRepo eventFacilityRepo;
    private final EventsMapperImpl eventFacilityMapper;

    public EventFacilityDto connectEventToFacility(Long eventId, String code) {
        eventFacilityRepo.insertEventFacility(eventId, code);
        return new EventFacilityDto(code, eventId);
    }

    public List<EventFacilityDto> getAllEventsFacility(Long eventId) {
        return eventFacilityMapper.eventFacilityListToDto(
                eventFacilityRepo.findAllEventsFacility(eventId)
        );
    }
}
