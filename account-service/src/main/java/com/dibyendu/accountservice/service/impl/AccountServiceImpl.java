package com.dibyendu.accountservice.service.impl;

import com.dibyendu.accountservice.Dto.AccountsDto;
import com.dibyendu.accountservice.Dto.CustomerDto;
import com.dibyendu.accountservice.constants.AccountsConstants;
import com.dibyendu.accountservice.entity.Accounts;
import com.dibyendu.accountservice.entity.Customer;
import com.dibyendu.accountservice.exception.CustomerAlreadyExistsException;
import com.dibyendu.accountservice.exception.ResourceNotFoundException;
import com.dibyendu.accountservice.mapper.AccountsMapper;
import com.dibyendu.accountservice.mapper.CustomerMapper;
import com.dibyendu.accountservice.repository.AccountsRepository;
import com.dibyendu.accountservice.repository.CustomerRepository;
import com.dibyendu.accountservice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
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

        return newAccount;
    }

    /**
     * @param mobileNumber - input mobile number
     * @return - Account details based on the given number
     **/
    @Override
    public CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                "Customer",
                "Mobile number",
                mobileNumber
                ));

        log.info("customer with the given phone number - "+customer.toString());

        Accounts account=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "Account",
                        "customer id",
                        customer.getCustomerId().toString()
                )
        );
        log.info("Account details according to the customer id - "+account.toString());
        CustomerDto customerDto = CustomerMapper.mapToCustomersDto(customer, new CustomerDto());
        customerDto.setAccountDetails(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));

        log.info("customer dto - "+ customerDto);

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException {
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountDetails();

//        if(customerRepository.existsByMobileNumber(customerDto.getMobileNumber())){
//            throw  new CustomerAlreadyExistsException("Customer already exists with the given mobile number "+customerDto.getMobileNumber());
//        }


        if(accountsDto!=null){
            log.info("extracted account details from customer dto - "+accountsDto);

            Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account","Account Number",accountsDto.getAccountNumber().toString())
            );

            log.info("Old Account Details - "+accounts);
            Accounts updatedAccount = AccountsMapper.mapToAccounts(accountsDto, accounts);

            updatedAccount=accountsRepository.save(updatedAccount);
            log.info("Updated Account - "+updatedAccount);

            Long customerId= accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Customer",
                            "customer id",
                            customerId.toString()
                    )
            );

            log.info("Old Customer details - "+customer);

            CustomerMapper.mapToCustomer(customerDto,customer);
            Customer updatedCustomer = customerRepository.save(customer);
            log.info("Updated Customer details - "+updatedCustomer);

            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException {
        boolean isDeleted=false;
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Customer",
                        "Mobile Number",
                        mobileNumber
                )
        );

        log.info("Customer details to be deleted - "+customer);
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        log.info("--- Account deleted ---");
        customerRepository.deleteById(customer.getCustomerId());
        log.info("--- Customer deleted ---");
        isDeleted=true;

        return  isDeleted;
    }
}
