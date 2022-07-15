package com.information.roxbylink.events.dto;

import com.information.roxbylink.events.models.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    private Long eventId;

    @NotNull @NotBlank
    @Length(max = 64)
    private String purpose;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime timeStart;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime timeEnd;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate eventDate;

    @NotNull
    private Integer participantsNum;

    @NotNull
    private List<String> facilities;

    @Valid @NotNull
    private CustomerDto customer;

    private Long customerId;

    public EventDto(Event event) {
        eventId = event.getEventId();
        purpose = event.getPurpose();
        timeStart = event.getTimeStart();
        timeEnd = event.getTimeEnd();
        eventDate = event.getEventDate();
        participantsNum = event.getParticipantsNum();
        customerId = event.getCustomer().getCustomerId();
    }
}
