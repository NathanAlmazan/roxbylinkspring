package com.information.roxbylink.facilities.mappers;

import com.information.roxbylink.facilities.dto.FacilityDto;
import com.information.roxbylink.facilities.models.Facility;

import java.util.List;

public interface FacilityMapper {
    FacilityDto facilityToDto(Facility facility);
    List<FacilityDto> facilityListToDto(List<Facility> facilities);
}
