package com.information.roxbylink.facilities.repositories;

import com.information.roxbylink.facilities.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FacilityRepo extends JpaRepository<Facility, Long> {

    @Modifying
    @Query(value = "INSERT INTO facility (facility_code, facility_name, facility_capacity) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertFacility(String code, String name, Integer capacity);

    @Modifying
    @Query(value = "UPDATE facility SET facility_name = ?2, facility_capacity = ?3 WHERE facility_code = ?1", nativeQuery = true)
    void updateFacility(String code, String name, Integer capacity);

    @Modifying
    @Query(value = "DELETE FROM facility WHERE facility_code = ?1", nativeQuery = true)
    void deleteFacility(String code);

    @Query(value = "SELECT * FROM facility WHERE facility_code = ?1", nativeQuery = true)
    Facility findFacilityByCode(String code);

    @Query(value = "SELECT * FROM facility ORDER BY facility_code", nativeQuery = true)
    List<Facility> findAllFacility();

    @Query(value = "SELECT f.id.facilityCode AS facilityCode, COUNT(*) AS eventCount, SUM(e.participantsNum) AS totalParticipants " +
            "FROM EventFacility f, Event e WHERE f.id.eventId = e.eventId GROUP BY f.id.facilityCode")
    List<FacilityEventsPrj> findFacilityEventsSummary();
}
