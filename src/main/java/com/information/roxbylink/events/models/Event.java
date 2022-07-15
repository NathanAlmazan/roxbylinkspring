package com.information.roxbylink.events.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @SequenceGenerator(name = "event_event_id_seq", sequenceName = "event_event_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_event_id_seq")
    @Column(name = "event_id", updatable = false)
    private Long eventId;

    @Column(length = 64)
    private String purpose;

    @Column
    private LocalTime timeStart;

    @Column
    private LocalTime timeEnd;

    @Column
    private LocalDate eventDate;

    @Column
    private Integer participantsNum;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventFacility> eventFacility;
}
