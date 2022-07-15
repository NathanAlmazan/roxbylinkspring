package com.information.roxbylink.events.dto;

import com.information.roxbylink.events.models.EventFacility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventFacilityDto {

    @NotNull
    @Length(max = 30)
    private String facilityCode;

    @NotNull
    private Long eventId;

    public EventFacilityDto(EventFacility eventFacility) {
        facilityCode = eventFacility.getId().getFacilityCode();
        eventId = eventFacility.getId().getEventId();
    }
}
