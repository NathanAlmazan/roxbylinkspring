package com.information.roxbylink.facilities.dto;

import com.information.roxbylink.facilities.models.Facility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class FacilityDto {

    @NotNull @NotBlank
    @Length(max = 5)
    private String facilityCode;

    @NotNull @NotBlank
    @Length(max = 64)
    private String facilityName;

    @NotNull
    private Integer facilityCapacity;

    private FacilityEventsDto facilityEvents;

    public FacilityDto(Facility facility) {
        facilityCode = facility.getFacilityCode();
        facilityName = facility.getFacilityName();
        facilityCapacity = facility.getFacilityCapacity();
    }
}
