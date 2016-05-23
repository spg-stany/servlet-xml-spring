package ru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.project.dao.AgentDAO;
import ru.project.model.Agent;

import java.sql.SQLException;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentDAO dao;

    @Override
    public int createAgent(Agent agent) {
        return dao.createAgent(agent);
    }

    @Override
    public Integer findIdByLogin(Agent agent) {
        return dao.getByLogin(agent);
    }

}
