package com.information.roxbylink.events.repositories;

import java.time.LocalDate;

public interface EventsByDatePrj {
    LocalDate getEventDate();
    String getFacilityCode();
    Integer getEventCount();
    Integer getTotalParticipants();
}
