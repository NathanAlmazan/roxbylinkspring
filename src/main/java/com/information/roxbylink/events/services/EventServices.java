package com.information.roxbylink.events.services;

import com.information.roxbylink.errors.types.EntityNotFoundException;
import com.information.roxbylink.events.dto.CustomerDto;
import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.mappers.EventsMapperImpl;
import com.information.roxbylink.events.models.Event;
import com.information.roxbylink.events.repositories.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        CustomerDto newCustomer;

        if (eventInfo.get(0).getCustomer().getCustomerId() == null) {
            newCustomer = customerServices.createNewCustomer(eventInfo.get(0).getCustomer());
        } else {
            newCustomer = customerServices.updateCustomer(eventInfo.get(0).getCustomer());
        }

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

    public List<EventDto> updateEvent(List<EventDto> eventInfo) {
        customerServices.updateCustomer(eventInfo.get(0).getCustomer());

        for (EventDto event : eventInfo) {
            eventFacilityServices.deleteEventFacilityByEventId(event.getEventId());
            eventRepo.updateEvent(
                    event.getPurpose(),
                    event.getTimeStart(),
                    event.getTimeEnd(),
                    event.getEventDate(),
                    event.getParticipantsNum(),
                    event.getEventId()
            );

            event.getFacilities().forEach(facility -> eventFacilityServices.connectEventToFacility(event.getEventId(), facility));
        }

        return eventInfo;
    }

    public EventDto deleteEvent(Long eventId) {
        Event deletedEvent = eventRepo.findEventById(eventId);

        if (deletedEvent == null) throw new EntityNotFoundException("Event not found.");

        eventRepo.deleteEvent(eventId);
        return eventsMapper.eventToDto(deletedEvent);
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

    public List<EventDto> findEventById(Long eventId) {
        Event event = eventRepo.findEventById(eventId);

        return eventsMapper.eventCustomerPrjListToEventDto(
            eventRepo.findEventCustomersById(event.getCustomer().getCustomerId())
        );
    }

    public List<EventDto> findAllEventsWithCustomers() {
        return eventsMapper.eventListToDto(
                eventRepo.findAllEventsWithCustomers()
        );
    }

    public List<EventDto> findEventBetweenDates(LocalDate min, LocalDate max) {
        return eventsMapper.eventListToDto(
                eventRepo.findAllEvents(min, max)
        );
    }
}
