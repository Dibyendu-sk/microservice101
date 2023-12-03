package com.dibyendu.accountservice.service;

import com.dibyendu.accountservice.Dto.CustomerDto;
import com.dibyendu.accountservice.exception.ResourceNotFoundException;

public interface AccountService {


    void createAccount(CustomerDto customersDto);

    /**
     * @param mobileNumber - input mobile number
     * @return - Account details based on the given number
     */

    CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException;

    /**
     * @param customerDto - input mobile number
     * @return - true or false regarding the successful completion of the operation
     */

    boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException;

    boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException;
}
