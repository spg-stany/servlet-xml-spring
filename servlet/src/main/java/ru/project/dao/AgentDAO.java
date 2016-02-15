package ru.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.project.model.Agent;
import ru.project.utils.HashGen;

import java.sql.SQLException;

@Repository
public class AgentDAO// extends JdbcDaoSupport
{
    @Autowired
    protected JdbcOperations jdbcOperations;

    /*
    //@Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AgentDAO(DataSource dataSource) {
        setDataSource(dataSource);
        //jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */

    public int createAgent(Agent agent) throws SQLException {
        return //getJdbcTemplate().update
                jdbcOperations.update
                ("INSERT INTO servlet.agent (phone, password) VALUES(?, ?)", agent.getPhone(), agent.getPassword());
        /*
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getJdbcTemplate());
        jdbcInsert.withTableName("agent").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("phone", agent.getPhone());
        parameters.put("password", agent.getPassword());

        return jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
        */
    }

    public Integer getByLogin(Agent agent) throws SQLException {
        try {
            return //getJdbcTemplate().queryForObject
                    jdbcOperations.queryForObject
                    ("SELECT id FROM servlet.agent WHERE phone = ?",
                    Integer.class,
                    agent.getPhone());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer getByLoginAndPassword(Agent agent) throws SQLException {
        try {
            return //getJdbcTemplate().queryForObject
                    jdbcOperations.queryForObject
                    ("SELECT id FROM servlet.agent WHERE phone = ? AND password = ?",
                    Integer.class,
                    agent.getPhone(),
                    HashGen.hash(agent.getPassword()));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
