package com.deutsche.ironbank2020.controllers;

import com.deutsche.ironbank2020.dao.BankRepo;
import com.deutsche.ironbank2020.dto.Loan;
import com.deutsche.ironbank2020.exceptions.NotEnoughMoneyException;
import com.deutsche.ironbank2020.model.Bank;
import com.deutsche.ironbank2020.services.BankService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceMockConf.class)
public class BankServiceImplTest {
    @Autowired
    private BankService bankService;
    @MockBean
    BankRepo bankRepo;

    @PostConstruct
    public void init() {
        Mockito.when(bankRepo.findAll()).thenReturn(List.of(Bank.builder().amount(90).build()));
    }

    @Test
    public void loanRejectedForStarks() {
        String answer = bankService.loan(new Loan("Nedd stark", 10));
        Assert.assertTrue(answer.toLowerCase().contains("reject"));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void exceptionWillBeThrownWhenTryingToLoanMoreMoneyThanWeHave() {
        bankService.loan(new Loan("Lanister", 100));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void exceptionWillBeThrownEvenForStarkWhenLoanMoreGreaterThanMoneyWeHave() {
        bankService.loan(new Loan("Robbert Stark", 100));
    }
}




