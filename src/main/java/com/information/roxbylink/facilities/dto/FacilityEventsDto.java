package com.information.roxbylink.facilities.dto;

import com.information.roxbylink.facilities.repositories.FacilityEventsPrj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FacilityEventsDto {
    private String facilityCode;
    private Integer eventCount;
    private Integer totalParticipants;

    public FacilityEventsDto(FacilityEventsPrj facilityEventsPrj) {
        facilityCode = facilityEventsPrj.getFacilityCode();
        eventCount = facilityEventsPrj.getEventCount();
        totalParticipants = facilityEventsPrj.getTotalParticipants();
    }
}
