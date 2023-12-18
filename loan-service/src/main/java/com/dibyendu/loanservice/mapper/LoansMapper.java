package com.dibyendu.loanservice.mapper;

import com.dibyendu.loanservice.Dto.LoansDto;
import com.dibyendu.loanservice.entity.Loans;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoansMapper {
    public static LoansDto mapToLoansDto(Loans loan, LoansDto loansDto){
        loansDto.setLoanType(loan.getLoanType());
        loansDto.setLoanNumber(loan.getLoanNumber());
        loansDto.setTotalLoan(loan.getTotalLoan());
        loansDto.setAmountPaid(loan.getAmountPaid());
        loansDto.setOutstandingAmount(loan.getOutstandingAmount());
        loansDto.setMobileNumber(loan.getMobileNumber());

        log.info("Loans->LoansDto - "+loansDto);
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto,Loans loans){
        loans.setLoanType(loansDto.getLoanType());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setMobileNumber(loansDto.getMobileNumber());

        log.info("LoansDto->Loans - "+loans);
        return loans;
    }
}
