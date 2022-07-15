package com.information.roxbylink.events.dto;

import com.information.roxbylink.events.models.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long customerId;

    @NotNull @NotBlank
    @Length(max = 30)
    private String commName;

    @NotNull @NotBlank
    @Length(max = 30)
    private String contactPerson;

    @NotNull
    @Length(min = 10, max = 10)
    private String phone;

    @NotNull @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotNull @NotBlank
    @Length(max = 100)
    private String postAddress;

    private List<EventDto> registeredEvents;

    public CustomerDto(Customer customer) {
        customerId = customer.getCustomerId();
        commName = customer.getCommName();
        contactPerson = customer.getContactPerson();
        phone = customer.getPhone();
        email = customer.getEmail();
        postAddress = customer.getPostAddress();
    }
}
