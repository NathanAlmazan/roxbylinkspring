package com.information.roxbylink.events;

import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventsController {
    private final EventServices eventServices;

    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return new ResponseEntity<>(eventServices.findAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/all/summary")
    public ResponseEntity<List<EventsByDateDto>> getEventsByDateSummary() {
        return new ResponseEntity<>(eventServices.findEventsByDateSummary(), HttpStatus.OK);
    }

    @GetMapping("/find/{eventId}")
    public ResponseEntity<List<EventDto>> getEventById(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(eventServices.findEventById(eventId), HttpStatus.OK);
    }

    @GetMapping("/all/filter/{start}/{end}")
    public ResponseEntity<List<EventDto>> filterEventsByDate(@PathVariable("start") LocalDate startDate, @PathVariable("end") LocalDate endDate) {
        return new ResponseEntity<>(eventServices.findEventBetweenDates(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<List<EventDto>> registerNewEvent(@RequestBody @Valid List<EventDto> eventInfo) {
        return new ResponseEntity<>(eventServices.createNewEvent(eventInfo), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<List<EventDto>> updateEventInfo(@RequestBody @Valid List<EventDto> eventInfo) {
        return new ResponseEntity<>(eventServices.updateEvent(eventInfo), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<EventDto> deleteEvent(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity<>(eventServices.deleteEvent(eventId), HttpStatus.OK);
    }
}
