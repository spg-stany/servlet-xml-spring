package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.Agent;
import ru.project.service.BaseService;
import ru.project.transport.XMLRequest;
import ru.project.transport.ResultEnum;

@RestController
@RequestMapping(value = "/servlet")
public class ServletController {

    @Autowired
    private BaseService service;

    @RequestMapping(value = "/xml",
            method = RequestMethod.POST,
            headers = {"content-type=application/xml"}
    )
    String handleRequestPost(@RequestBody XMLRequest xmlRequest) {
        String result;
        try {
            Agent agent = new Agent(xmlRequest.getLogin(), xmlRequest.getPassword());

            switch (xmlRequest.getRequestType()) {
                //NEW_AGENT
                case "new-agt": {
                    result = service.createAgent(agent);
                    break;
                }
                //AGT_BALANCE
                case "agt-bal": {
                    result = service.getAgentBalance(agent);
                    break;
                }
                default: {
                    throw new Exception("UNKNOWN RequestType");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<response>\n<result-code>" + ResultEnum.OTHER.getCode() + "</result-code>\n</response>";
        }

        return result;
    }

}
