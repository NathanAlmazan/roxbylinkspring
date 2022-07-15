package com.information.roxbylink.facilities.services;

import com.information.roxbylink.errors.types.EntityNotFoundException;
import com.information.roxbylink.facilities.dto.FacilityDto;
import com.information.roxbylink.facilities.dto.FacilityEventsDto;
import com.information.roxbylink.facilities.mappers.FacilitiesMapperImpl;
import com.information.roxbylink.facilities.models.Facility;
import com.information.roxbylink.facilities.repositories.FacilityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class FacilitiesServices {
    private final FacilityRepo facilityRepo;
    private final FacilitiesMapperImpl facilitiesMapper;

    public FacilityDto createNewFacility(FacilityDto facilityInfo) {
        facilityRepo.insertFacility(
                facilityInfo.getFacilityCode(),
                facilityInfo.getFacilityName(),
                facilityInfo.getFacilityCapacity()
        );

        return facilityInfo;
    }

    public FacilityDto updateFacility(FacilityDto facilityInfo) {
        facilityRepo.updateFacility(
                facilityInfo.getFacilityCode(),
                facilityInfo.getFacilityName(),
                facilityInfo.getFacilityCapacity()
        );

        return facilityInfo;
    }

    public FacilityDto deleteFacility(String code) {
        Facility facility = facilityRepo.findFacilityByCode(code);

        if (facility == null) throw new EntityNotFoundException("Facility not found.");

        facilityRepo.deleteFacility(code);

        return facilitiesMapper.facilityToDto(facility);
    }

    @PermitAll
    public FacilityDto getFacilityByCode(String code) {
        return facilitiesMapper.facilityToDto(
                facilityRepo.findFacilityByCode(code)
        );
    }

    public List<FacilityDto> getAllFacilities() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<List<FacilityEventsDto>> fetchEvents = executor.submit(new Callable<List<FacilityEventsDto>>() {
            @Override
            public List<FacilityEventsDto> call() throws Exception {
                return getFacilityEventsSummary();
            }
        });

        Future<List<FacilityDto>> fetchFacilities = executor.submit(new Callable<List<FacilityDto>>() {
            @Override
            public List<FacilityDto> call() throws Exception {
                return facilitiesMapper.facilityListToDto(
                        facilityRepo.findAllFacility()
                );
            }
        });

        executor.shutdown();

        List<FacilityDto> facilities = fetchFacilities.get();
        List<FacilityEventsDto> events = fetchEvents.get();

        facilities.forEach(facility -> facility.setFacilityEvents(
                events.stream().filter(event -> facility.getFacilityCode().equals(event.getFacilityCode()))
                        .findAny()
                        .orElse(null)
        ));

        return facilities;
    }

    private List<FacilityEventsDto> getFacilityEventsSummary() {
        return facilitiesMapper.facilityEventListToDto(
                facilityRepo.findFacilityEventsSummary()
        );
    }
}
