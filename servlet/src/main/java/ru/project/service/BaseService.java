package ru.project.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.xml.ResultEnum;
import ru.project.model.Account;
import ru.project.model.Agent;
import ru.project.utils.HashGen;
import ru.project.xml.XMLResponse;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BaseService {
    private final static Logger logger = LoggerFactory.getLogger(BaseService.class);

    private final AgentService agentService;
    private final AccountService accountService;

    @Autowired
    public BaseService(AgentService agentService,
                       AccountService accountService) {
        this.agentService = agentService;
        this.accountService = accountService;
    }


    @Transactional
    public XMLResponse createAgent(Agent agent) {
        XMLResponse response = new XMLResponse();

        ResultEnum validateResult = validate(agent);
        if (validateResult != null) {
            response.setResultCode(validateResult.getCode());
            return response;
        }

        agent.setPassword(HashGen.hash(agent.getPassword()));
        if (agentService.createAgent(agent) == 0) {
            response.setResultCode(ResultEnum.OTHER.getCode());
            return response;
        }

        Integer agentId = agentService.findIdByLogin(agent);
        if (agentId == null) {
            response.setResultCode(ResultEnum.AGENT_NOT_EXITS.getCode());
            return response;
        }

        if (accountService.createAccount(new Account(agentId, new BigDecimal(0))) == 0) {
            response.setResultCode(ResultEnum.OTHER.getCode());
            return response;
        }

        response.setResultCode(ResultEnum.OK.getCode());
        return response;
    }


    public XMLResponse getAgentBalance(Agent agent) {
        XMLResponse response = new XMLResponse();

        Integer id = agentService.findIdByLogin(agent);
        if (id == null) {
            response.setResultCode(ResultEnum.AGENT_NOT_EXITS.getCode());
            return response;
        }

        Account account = accountService.findByAgentId(id);
        if (account == null) {
            response.setResultCode(ResultEnum.ACCOUNT_NOT_EXITS.getCode());
            return response;
        }

        response.setResultCode(ResultEnum.OK.getCode());
        response.setBalance(account.getBalance());
        return response;
    }


    protected ResultEnum validate(Agent agent) {
        if (isExistAgent(agent))
            return ResultEnum.DUPLICATE_AGENT;
        else if (!isValidPhone(agent.getPhone()))
            return ResultEnum.WRONG_LOGIN;
        else if (isBadPassword(agent.getPassword()))
            return ResultEnum.BAD_PASSWORD;

        return null;
    }


    private boolean isValidPhone(String number) {
        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


    private boolean isExistAgent(Agent agent) {
        return agentService.findIdByLogin(agent) != null;
    }

    private boolean isBadPassword(String password) {
        return StringUtils.length(password) < 8;
    }
}
