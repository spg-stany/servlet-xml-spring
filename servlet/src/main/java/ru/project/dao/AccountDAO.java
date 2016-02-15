package ru.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.project.model.Account;

import java.sql.SQLException;

@Repository
public class AccountDAO// extends JdbcDaoSupport
{
    @Autowired
    protected JdbcOperations jdbcOperations;

    /*
    //@Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAO(DataSource dataSource) throws SQLException {
        setDataSource(dataSource);
        //jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */


    public int createAccount(Account account) throws SQLException {
        return //getJdbcTemplate().update
                jdbcOperations.update("INSERT INTO servlet.account (agent_id, balance) VALUES(?, ?)",
                        account.getAgentId(), account.getBalance());
    }

    public Account findAccount(int agentId) throws SQLException {
        try {
            return //getJdbcTemplate().queryForObject
                    jdbcOperations.queryForObject
                            ("SELECT * FROM servlet.account WHERE agent_id = ?",
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
