package com.dibyendu.loanservice.service.impl;

import com.dibyendu.loanservice.Dto.LoansDto;
import com.dibyendu.loanservice.service.LoanService;

public class LoanServiceImpl implements LoanService {
    @Override
    public void createAccout(String mobileNumber) {

    }

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {
        return false;
    }

    @Override
    public boolean deleteLoanDetails(String mobileNumber) {
        return false;
    }
}
