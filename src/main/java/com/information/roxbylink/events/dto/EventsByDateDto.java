package com.information.roxbylink.events.dto;

import com.information.roxbylink.events.repositories.EventsByDatePrj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EventsByDateDto {
    private LocalDate eventDate;
    private String facilityCode;
    private Integer eventCount;
    private Integer totalParticipants;

    public EventsByDateDto(EventsByDatePrj events) {
        eventDate = events.getEventDate();
        facilityCode = events.getFacilityCode();
        eventCount = events.getEventCount();
        totalParticipants = events.getTotalParticipants();
    }
}
