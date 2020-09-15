package com.deutsche.ironbank2020.services;

import com.deutsche.ironbank2020.dao.BankRepo;
import com.deutsche.ironbank2020.dto.Loan;
import com.deutsche.ironbank2020.exceptions.NotEnoughMoneyException;
import com.deutsche.ironbank2020.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private LoanValidator loanValidator;

    @Override
    public Bank getBank() {
        return bankRepo.findAll().get(0);
    }

    @Override
    public String loan(Loan loan) {
        if (loan.getAmount() <= 0) {
            throw new IllegalStateException("loan must be greater 0");
        }
        Bank bank = bankRepo.findAll().get(0);
        if (loan.getAmount() > bank.getAmount()) {
            throw new NotEnoughMoneyException();
        }
        return loanValidator.getAnswer(loan);
    }
}
