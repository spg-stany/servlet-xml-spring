package ru.project.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.project.service.BaseService;
import ru.project.model.Agent;
import ru.project.transport.Request;
import ru.project.utils.XmlUtils;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/servlet")
public class ServletController {

    @Inject
    private BaseService service;

    @RequestMapping(//value = "/servlet",
            method = RequestMethod.POST,
            headers = {"content-type=application/xml"}
    )
    String handleRequestPost(@RequestBody String request) throws Exception {
        String result = "UNKNOWN RequestType";

        Request req = XmlUtils.xmlToRequest(IOUtils.toInputStream(request, "UTF-8"));
        Agent agent = new Agent(req.getLogin(), req.getPassword());

        switch (req.getRequestType()) {
            case NEW_AGENT: {
                result = service.createAgent(agent);
                break;
            }
            case AGT_BALANCE: {
                result = service.getAgentBalance(agent);
                break;
            }
        }

        return result;
    }

}
