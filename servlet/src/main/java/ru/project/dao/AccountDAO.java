package ru.project.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.project.model.Account;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
public class AccountDAO extends JdbcDaoSupport {
    //@Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    public AccountDAO(DataSource dataSource) {
        setDataSource(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createAccount(Account account) throws SQLException {
        return getJdbcTemplate().update("INSERT INTO account (agent_id, balance) VALUES(?, ?)",
                account.getAgentId(), account.getBalance());
    }

    public Account findAccount(int agentId) throws SQLException {
        try {
            return getJdbcTemplate().queryForObject("SELECT * FROM account WHERE agent_id = ?",
                    new Object[]{agentId},
                    (rs, rowNum) -> new Account(rs.getInt("agent_id"), rs.getBigDecimal("balance"))
                /*
                new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getInt("agent_id"), rs.getBigDecimal("balance"));
                    }
                }
                */
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
