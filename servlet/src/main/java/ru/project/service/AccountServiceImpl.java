package ru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.project.dao.AccountDAO;
import ru.project.model.Account;

import java.sql.SQLException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO dao;

    public int createAccount(Account account) {
        return dao.createAccount(account);
    }

    @Override
    public Account findByAgentId(int id) {
        return dao.findAccount(id);
    }

}
