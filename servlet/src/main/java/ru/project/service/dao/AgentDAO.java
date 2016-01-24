package ru.project.service.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.project.model.Agent;
import ru.project.utils.HashGen;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AgentDAO extends JdbcDaoSupport {
    //@Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    public AgentDAO(DataSource dataSource) {
        setDataSource(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createAgent(Agent agent) throws SQLException {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate());
        jdbcInsert.withTableName("agent").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("phone", agent.getPhone());
        parameters.put("password", agent.getPassword());

        return jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
        //String sql = "INSERT INTO agent (phone, password) VALUES(?, ?)";
        //return getJdbcTemplate().update(sql, agent.getPhone(), agent.getPassword());
    }

    public Integer getByLogin(Agent agent) throws SQLException {
        try {
            return getJdbcTemplate().queryForObject("SELECT id FROM agent WHERE phone = ?",
                    Integer.class,
                    agent.getPhone());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer getByLoginAndPassword(Agent agent) throws SQLException {
        try {
            return getJdbcTemplate().queryForObject("SELECT id FROM agent WHERE phone = ? AND password = ?",
                    Integer.class,
                    agent.getPhone(),
                    HashGen.hash(agent.getPassword()));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
