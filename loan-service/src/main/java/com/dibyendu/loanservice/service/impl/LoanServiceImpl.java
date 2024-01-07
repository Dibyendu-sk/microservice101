package com.dibyendu.loanservice.service.impl;

import com.dibyendu.loanservice.Dto.LoansDto;
import com.dibyendu.loanservice.constants.LoanConstants;
import com.dibyendu.loanservice.entity.Loans;
import com.dibyendu.loanservice.exception.ResourceAlreadyExistsException;
import com.dibyendu.loanservice.exception.ResourceNotFoundException;
import com.dibyendu.loanservice.mapper.LoansMapper;
import com.dibyendu.loanservice.repository.LoansRepository;
import com.dibyendu.loanservice.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Slf4j
@Service
public class LoanServiceImpl implements LoanService {

    LoansRepository loansRepository;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> existingLoan=loansRepository.findByMobileNumber(mobileNumber);
        if (existingLoan.isPresent()){
            throw new ResourceAlreadyExistsException("Loan Already Registered With The Given Mobile Number.");
        }
        Loans savedLoan = loansRepository.save(createNewLoan(mobileNumber));
        if (savedLoan.getLoanNumber()!=null){
            log.info("New Created Loan - "+savedLoan);
        }
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan=new Loans();
        long loanNumber=100000000000L+new Random().nextInt(900000000);

        newLoan.setLoanNumber(String.valueOf(loanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    /**
     *
     * @param mobileNumber
     * @return Loan Details Associated With the Mobile Number
     */

    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        Loans loan=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobile number",mobileNumber)
        );

        log.info("Loan with the Given mobile number - "+loan);
        return LoansMapper.mapToLoansDto(loan,new LoansDto());
    }

    /**
     *
     * @param loansDto
     * @return boolean value indicating if the update operation is successful or not
     */
    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {
        Loans loan=loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loan","loan number",loansDto.getLoanNumber())
        );
        log.info("Old Loan details - "+loan);

        LoansMapper.mapToLoans(loansDto,loan);
        Loans updatedLoan= loansRepository.save(loan);

        log.info("Updated loan details - "+updatedLoan);
        return  true;
    }

    /**
     *
     * @param mobileNumber
     * @return boolean value indicating if the delete operation is successful or not
     */
    @Override
    public boolean deleteLoanDetails(String mobileNumber) {
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobile number",mobileNumber)
        );
        log.info("---- Loan with loan number "+loans.getLoanNumber()+" will be deleted ----");
        loansRepository.deleteById(loans.getLoanId());
        log.info("---- Loan deleted successfully ----");
        return true;
    }
}
