package com.information.roxbylink.facilities;

import com.information.roxbylink.errors.types.InternalErrorException;
import com.information.roxbylink.facilities.dto.FacilityDto;
import com.information.roxbylink.facilities.services.FacilitiesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/facility")
@RequiredArgsConstructor
public class FacilitiesController {
    private final FacilitiesServices facilitiesServices;

    @GetMapping("/all")
    public ResponseEntity<List<FacilityDto>> getAllFacilities() {
        try {
            return new ResponseEntity<>(facilitiesServices.getAllFacilities(), HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/find/{code}")
    public ResponseEntity<FacilityDto> getFacilityByCode(@PathVariable("code") String facilityCode) {
        return new ResponseEntity<>(facilitiesServices.getFacilityByCode(facilityCode), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<FacilityDto> createNewFacility(@RequestBody @Valid FacilityDto facilityInfo) {
        return new ResponseEntity<>(facilitiesServices.createNewFacility(facilityInfo), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<FacilityDto> updateFacility(@RequestBody @Valid FacilityDto facilityInfo) {
        return new ResponseEntity<>(facilitiesServices.updateFacility(facilityInfo), HttpStatus.OK);
    }

    @GetMapping("/delete/{code}")
    public ResponseEntity<FacilityDto> deleteFacility(@PathVariable("code") String facilityCode) {
        return new ResponseEntity<>(facilitiesServices.deleteFacility(facilityCode), HttpStatus.OK);
    }
}
