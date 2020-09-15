package com.deutsche.ironbank2020.infra;

import com.deutsche.ironbank2020.dao.BankRepo;
import com.deutsche.ironbank2020.model.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Evgeny Borisov
 */
@Service
@RequiredArgsConstructor
public class BankFillerListener {

    private final BankRepo bankRepo;

    @Transactional
    @EventListener(ContextRefreshedEvent.class)
    public void fillBank(){
        Bank bank = bankRepo.findAll().get(0);
        bank.setAmount(100);
    }
}
