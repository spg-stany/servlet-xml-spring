package ru.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.project.model.Account;

import java.sql.SQLException;

@Repository
public class AccountDAO {

    @Autowired
    protected JdbcOperations jdbcOperations;

    public int createAccount(Account account) {
        return jdbcOperations.update("INSERT INTO servlet.account (agent_id, balance) VALUES(?, ?)",
                account.getAgentId(), account.getBalance());
    }

    public Account findAccount(int agentId) {
        try {
            return jdbcOperations.queryForObject
                    ("SELECT * FROM servlet.account WHERE agent_id = ?",
                            new Object[]{agentId},
                            (rs, rowNum) -> new Account(rs.getInt("agent_id"), rs.getBigDecimal("balance"))

                    );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
