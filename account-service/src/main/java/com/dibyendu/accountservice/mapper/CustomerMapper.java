package com.dibyendu.accountservice.mapper;

import com.dibyendu.accountservice.Dto.CustomerDto;
import com.dibyendu.accountservice.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomersDto(Customer customer, CustomerDto customerDto){

        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());

        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }
}
