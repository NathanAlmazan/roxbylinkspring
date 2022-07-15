package com.information.roxbylink.facilities.models;

import com.information.roxbylink.events.models.EventFacility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Facility {

    @Id
    @Column(length = 5, nullable = false, unique = true)
    private String facilityCode;

    @Column(length = 64, nullable = false)
    private String facilityName;

    @Column(nullable = false)
    private Integer facilityCapacity;

    @OneToMany(mappedBy = "facility", fetch = FetchType.LAZY)
    private List<EventFacility> eventFacility;
}
