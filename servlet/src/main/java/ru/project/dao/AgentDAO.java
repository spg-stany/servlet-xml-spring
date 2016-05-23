package ru.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.project.model.Agent;
import ru.project.utils.HashGen;

import java.sql.SQLException;

@Repository
public class AgentDAO {

    @Autowired
    protected JdbcOperations jdbcOperations;

    public int createAgent(Agent agent) {
        return jdbcOperations.update
                ("INSERT INTO servlet.agent (phone, password) VALUES(?, ?)", agent.getPhone(), agent.getPassword());
    }

    public Integer getByLogin(Agent agent) {
        try {
            return jdbcOperations.queryForObject
                    ("SELECT id FROM servlet.agent WHERE phone = ?",
                            Integer.class,
                            agent.getPhone());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
