package com.dibyendu.accountservice.service.impl;

import com.dibyendu.accountservice.Dto.CustomerDto;
import com.dibyendu.accountservice.constants.AccountsConstants;
import com.dibyendu.accountservice.entity.Accounts;
import com.dibyendu.accountservice.entity.Customer;
import com.dibyendu.accountservice.exception.CustomerAlreadyExistsException;
import com.dibyendu.accountservice.mapper.CustomerMapper;
import com.dibyendu.accountservice.repository.AccountsRepository;
import com.dibyendu.accountservice.repository.CustomerRepository;
import com.dibyendu.accountservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()){
            throw  new CustomerAlreadyExistsException("Customer already exists with the given mobile number "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("HDFC Pvt. Ltd");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long accountNumber=1000000000L+new Random().nextInt(900000000);

        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("HDFC Pvt. Ltd");

        return newAccount;
    }
}
