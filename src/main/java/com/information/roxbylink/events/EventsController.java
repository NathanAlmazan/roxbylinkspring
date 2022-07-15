package com.information.roxbylink.events;

import com.information.roxbylink.events.dto.EventDto;
import com.information.roxbylink.events.dto.EventsByDateDto;
import com.information.roxbylink.events.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<List<EventDto>> registerNewEvent(@RequestBody @Valid List<EventDto> eventInfo) {
        return new ResponseEntity<>(eventServices.createNewEvent(eventInfo), HttpStatus.CREATED);
    }
}
