package com.deutsche.ironbank2020.services;

import com.deutsche.ironbank2020.dto.Loan;
import org.springframework.stereotype.Service;

@Service
public class LoanValidatorServiceImpl implements LoanValidatorService {
    @Override
    public String getAnswer(Loan loan) {
        if (loan.getUserName().toLowerCase().contains("stark")) {
            return "rejected";
        } else {
            return "loan accepted";
        }
    }
}
