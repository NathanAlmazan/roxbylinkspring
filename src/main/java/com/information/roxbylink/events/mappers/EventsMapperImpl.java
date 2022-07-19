package com.information.roxbylink.events.mappers;

import com.information.roxbylink.events.dto.CustomerDto;
import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.dto.EventFacilityDto;
import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.models.Customer;
import com.information.roxbylink.events.models.Event;
import com.information.roxbylink.events.models.EventFacility;
import com.information.roxbylink.events.repositories.EventCustomerPrj;
import com.information.roxbylink.events.repositories.EventsByDatePrj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsMapperImpl implements CustomerMapper, EventMapper, EventByDateMapper, EventFacilityMapper {
    @Override
    public CustomerDto customerToDto(Customer customer) {
        return new CustomerDto(customer);
    }

    @Override
    public List<CustomerDto> customerListToDto(List<Customer> customers) {
        List<CustomerDto> customerList = new ArrayList<>(customers.size());

        customers.forEach(customer -> customerList.add(customerToDto(customer)));
        return customerList;
    }

    @Override
    public EventDto eventToDto(Event event) {
        return new EventDto(event);
    }

    @Override
    public List<EventDto> eventListToDto(List<Event> events) {
        List<EventDto> eventList = new ArrayList<>(events.size());

        events.forEach(event -> eventList.add(eventToDto(event)));
        return eventList;
    }

    @Override
    public EventsByDateDto eventsByDateDto(EventsByDatePrj event) {
        return new EventsByDateDto(event);
    }

    @Override
    public List<EventsByDateDto> eventsByDateListToDto(List<EventsByDatePrj> events) {
        List<EventsByDateDto> eventList = new ArrayList<>(events.size());

        events.forEach(event ->  eventList.add(eventsByDateDto(event)));
        return eventList;
    }

    @Override
    public EventFacilityDto eventFacilityToDto(EventFacility facility) {
        return new EventFacilityDto(facility);
    }

    @Override
    public List<EventFacilityDto> eventFacilityListToDto(List<EventFacility> facilities) {
        List<EventFacilityDto> facilityList = new ArrayList<>(facilities.size());

        facilities.forEach(facility -> facilityList.add(eventFacilityToDto(facility)));
        return facilityList;
    }

    public EventDto eventCustomerPrjToEventDto(EventCustomerPrj eventCustomerPrj) {
        EventDto eventDto = eventToDto(eventCustomerPrj.getEventInfo());

        List<String> facilities = new ArrayList<>();
        facilities.add(eventCustomerPrj.getEventFacility().getId().getFacilityCode());

        eventDto.setCustomer(customerToDto(eventCustomerPrj.getCustomer()));
        eventDto.setFacilities(facilities);
        return eventDto;
    }

    public List<EventDto> eventCustomerPrjListToEventDto(List<EventCustomerPrj> eventCustomerPrj) {
        List<EventDto> eventList = new ArrayList<>(eventCustomerPrj.size());

        eventCustomerPrj.forEach(event -> eventList.add(eventCustomerPrjToEventDto(event)));
        return eventList;
    }
}
