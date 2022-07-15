package com.information.roxbylink.facilities.mappers;

import com.information.roxbylink.facilities.dto.FacilityDto;
import com.information.roxbylink.facilities.dto.FacilityEventsDto;
import com.information.roxbylink.facilities.models.Facility;
import com.information.roxbylink.facilities.repositories.FacilityEventsPrj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilitiesMapperImpl implements FacilityMapper, FacilityEventsMapper {

    @Override
    public FacilityDto facilityToDto(Facility facility) {
        return new FacilityDto(facility);
    }

    @Override
    public List<FacilityDto> facilityListToDto(List<Facility> facilities) {
        List<FacilityDto> facilityList = new ArrayList<>(facilities.size());

        facilities.forEach(facility -> facilityList.add(facilityToDto(facility)));
        return facilityList;
    }

    @Override
    public FacilityEventsDto facilityEventToDto(FacilityEventsPrj facilityEvent) {
        return new FacilityEventsDto(facilityEvent);
    }

    @Override
    public List<FacilityEventsDto> facilityEventListToDto(List<FacilityEventsPrj> facilityEventsList) {
        List<FacilityEventsDto> eventsList = new ArrayList<>(facilityEventsList.size());

        facilityEventsList.forEach(event -> eventsList.add(facilityEventToDto(event)));
        return eventsList;
    }
}
