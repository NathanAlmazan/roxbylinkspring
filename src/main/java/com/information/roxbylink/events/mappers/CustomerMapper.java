package com.information.roxbylink.events.mappers;

import com.information.roxbylink.events.dto.CustomerDto;
import com.information.roxbylink.events.models.Customer;

import java.util.List;

public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);
    List<CustomerDto> customerListToDto(List<Customer> customers);
}
