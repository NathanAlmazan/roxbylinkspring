package com.information.roxbylink.events.services;

import com.information.roxbylink.events.dto.CustomerDto;
import com.information.roxbylink.events.mappers.EventsMapperImpl;
import com.information.roxbylink.events.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServices {
    private final CustomerRepo customerRepo;
    private final EventsMapperImpl customerMapper;

    public CustomerDto createNewCustomer(CustomerDto newCustomer) {
        return customerMapper.customerToDto(customerRepo.insertCustomer(
                newCustomer.getCommName(),
                newCustomer.getContactPerson(),
                newCustomer.getEmail(),
                newCustomer.getPhone(),
                newCustomer.getPostAddress()
            )
        );
    }

    public CustomerDto updateCustomer(CustomerDto customer) {
        customerRepo.updateCustomer(
                customer.getCommName(),
                customer.getContactPerson(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getPostAddress(),
                customer.getCustomerId()
        );

        return customer;
    }

    public CustomerDto getCustomerById(Long customerId) {
        return customerMapper.customerToDto(
                customerRepo.getCustomerById(customerId)
        );
    }
}
