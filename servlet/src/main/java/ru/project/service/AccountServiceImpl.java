package ru.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.project.service.dao.AccountDAO;
import ru.project.model.Account;

import javax.inject.Inject;
import java.sql.SQLException;

@Service
public class AccountServiceImpl implements AccountService {
    @Inject
    private AccountDAO dao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
    public int createAccount(Account account) {
        try {
            return dao.createAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Account findByAgentId(int id) {
        try {
            return dao.findAccount(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDao(AccountDAO dao) {
        this.dao = dao;
    }
}
