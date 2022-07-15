package com.information.roxbylink.facilities.mappers;

import com.information.roxbylink.facilities.dto.FacilityEventsDto;
import com.information.roxbylink.facilities.repositories.FacilityEventsPrj;

import java.util.List;

public interface FacilityEventsMapper {
    FacilityEventsDto facilityEventToDto(FacilityEventsPrj facilityEvent);
    List<FacilityEventsDto> facilityEventListToDto(List<FacilityEventsPrj> facilityEventsList);
}
