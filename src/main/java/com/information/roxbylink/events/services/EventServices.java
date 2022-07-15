package com.information.roxbylink.events.services;

import com.information.roxbylink.events.dto.CustomerDto;
import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.mappers.EventsMapperImpl;
import com.information.roxbylink.events.models.Event;
import com.information.roxbylink.events.repositories.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServices {
    private final EventRepo eventRepo;
    private final EventsMapperImpl eventsMapper;
    private final CustomerServices customerServices;
    private final EventFacilityServices eventFacilityServices;

    public List<EventDto> createNewEvent(List<EventDto> eventInfo) {
        CustomerDto newCustomer = customerServices.createNewCustomer(eventInfo.get(0).getCustomer());

        List<Event> savedEvents = new ArrayList<>(eventInfo.size());

        for (EventDto event : eventInfo) {
            Event newEvent = eventRepo.insertEvent(
                    event.getPurpose(),
                    event.getTimeStart(),
                    event.getTimeEnd(),
                    event.getEventDate(),
                    event.getParticipantsNum(),
                    newCustomer.getCustomerId()
            );

            event.getFacilities().forEach(facility -> eventFacilityServices.connectEventToFacility(newEvent.getEventId(), facility));

            savedEvents.add(newEvent);
        }

        return eventsMapper.eventListToDto(savedEvents);
    }

    public List<EventDto> findAllEvents() {
        List<EventDto> events = eventsMapper.eventListToDto(eventRepo.findAllEvents());

        events.forEach(event -> {
            List<String> facilities = new ArrayList<>(10);
            eventFacilityServices.getAllEventsFacility(event.getEventId()).forEach(facility -> facilities.add(facility.getFacilityCode()));

            event.setCustomer(customerServices.getCustomerById(event.getCustomerId()));
            event.setFacilities(facilities);
        });

        return events;
    }

    public List<EventsByDateDto> findEventsByDateSummary() {
        return eventsMapper.eventsByDateListToDto(eventRepo.findAllEventsByDate());
    }
}
