package com.information.roxbylink.events.models;

import com.information.roxbylink.facilities.models.Facility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EventFacility {

    @EmbeddedId
    private EventFacilityKey id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @MapsId("facilityCode")
    @JoinColumn(name = "facility_code")
    private Facility facility;

}
