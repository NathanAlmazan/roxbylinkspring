package com.information.roxbylink.events.repositories;

import com.information.roxbylink.events.models.EventFacility;
import com.information.roxbylink.events.models.EventFacilityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventFacilityRepo extends JpaRepository<EventFacility, EventFacilityKey> {

    @Modifying
    @Query(value = "INSERT INTO event_facility (event_id, facility_code) VALUES (?1, ?2)", nativeQuery = true)
    void insertEventFacility(Long eventId, String facilityCode);

    @Modifying
    @Query(value = "DELETE FROM event_facility WHERE event_id = ?1", nativeQuery = true)
    void deleteEventFacilityByEventId(Long eventId);

    @Query(value = "SELECT * FROM event_facility WHERE event_id = ?1", nativeQuery = true)
    List<EventFacility> findAllEventsFacility(Long eventId);
}
