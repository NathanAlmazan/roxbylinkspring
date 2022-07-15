package com.information.roxbylink.events.repositories;

import com.information.roxbylink.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query(value = "INSERT INTO public.event (purpose, time_start, time_end, event_date, participants_num, customer_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6) RETURNING *", nativeQuery = true)
    Event insertEvent(String purpose, LocalTime start, LocalTime end, LocalDate eventDate, Integer participants, Long customerId);

    @Query(value = "SELECT e.eventDate AS eventDate, f.id.facilityCode AS facilityCode, COUNT(*) AS eventCount, SUM(e.participantsNum) AS totalParticipants " +
            "FROM EventFacility f, Event e WHERE f.id.eventId = e.eventId AND e.eventDate >= CURRENT_DATE GROUP BY e.eventDate, f.id.facilityCode " +
            "ORDER BY e.eventDate DESC")
    List<EventsByDatePrj> findEventsByDate();

    @Query(value = "SELECT e.eventDate AS eventDate, f.id.facilityCode AS facilityCode, COUNT(*) AS eventCount, SUM(e.participantsNum) AS totalParticipants " +
            "FROM EventFacility f, Event e WHERE f.id.eventId = e.eventId GROUP BY e.eventDate, f.id.facilityCode " +
            "ORDER BY e.eventDate DESC")
    List<EventsByDatePrj> findAllEventsByDate();

    @Query(value = "SELECT MAX(event_date), MIN(event_date) FROM event", nativeQuery = true)
    LocalDate findMaximumAndMinimumDate();

    @Query(value = "SELECT * FROM public.event, customer WHERE event.customer_id = customer.customer_id AND event_date BETWEEN ?1 AND ?2 ORDER BY public.event.event_date DESC", nativeQuery = true)
    List<Event> findAllEvents(LocalDate start, LocalDate end);

    @Query(value = "SELECT * FROM public.event, customer WHERE event.customer_id = customer.customer_id ORDER BY public.event.event_date DESC", nativeQuery = true)
    List<Event> findAllEvents();
}
