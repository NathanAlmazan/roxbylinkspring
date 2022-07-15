package com.information.roxbylink.events.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class EventFacilityKey implements Serializable {
    @Column(length = 5)
    private String facilityCode;

    @Column
    private Long eventId;
}
