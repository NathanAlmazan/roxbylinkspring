package com.information.roxbylink.events.repositories;

import com.information.roxbylink.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query(value = "INSERT INTO public.event (purpose, time_start, time_end, event_date, participants_num, customer_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6) RETURNING *", nativeQuery = true)
    Event insertEvent(String purpose, LocalTime start, LocalTime end, LocalDate eventDate, Integer participants, Long customerId);

    @Modifying
    @Query(value = "UPDATE public.event SET purpose = ?1, time_start = ?2, time_end = ?3, event_date = ?4, participants_num = ?5 WHERE event_id = ?6", nativeQuery = true)
    void updateEvent(String purpose, LocalTime start, LocalTime end, LocalDate eventDate, Integer participantsNum, Long eventId);

    @Modifying
    @Query(value = "DELETE FROM public.event WHERE event_id = ?1", nativeQuery = true)
    void deleteEvent(Long eventId);

    @Query(value = "SELECT * FROM public.event e, public.customer s WHERE e.customer_id = s.customer_id ORDER BY e.event_date DESC", nativeQuery = true)
    List<Event> findAllEventsWithCustomers();

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

    @Query(value = "SELECT e as eventInfo, s AS customer, y AS eventFacility FROM Event e INNER JOIN Customer s ON e.customer.customerId = s.customerId " +
            "INNER JOIN EventFacility y ON e.eventId = y.id.eventId WHERE e.customer.customerId = ?1")
    List<EventCustomerPrj> findEventCustomersById(Long customerId);

    @Query(value = "SELECT * FROM public.event WHERE event_id = ?1", nativeQuery = true)
    Event findEventById(Long eventId);
}
