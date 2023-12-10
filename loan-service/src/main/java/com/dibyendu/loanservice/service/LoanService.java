package com.dibyendu.loanservice.service;

import com.dibyendu.loanservice.Dto.LoansDto;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {

    /**
    * @param mobileNumber - Input Mobile Number
     * @return - void
    */
    void createAccout(String mobileNumber);

    LoansDto fetchLoanDetails(String mobileNumber);

    boolean updateLoanDetails(LoansDto loansDto);
    boolean deleteLoanDetails(String mobileNumber);
}
