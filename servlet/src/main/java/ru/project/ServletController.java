package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.model.Agent;
import ru.project.service.BaseService;
import ru.project.xml.XMLRequest;
import ru.project.xml.ResultEnum;
import ru.project.xml.XMLResponse;

@RestController
@RequestMapping(value = "/servlet")
public class ServletController {

    @Autowired
    private BaseService service;

    @RequestMapping(value = "/xml",
            method = RequestMethod.POST,
            headers = {"content-type=application/xml"}
    )
    @ResponseBody
    XMLResponse handleRequestPost(@RequestBody XMLRequest xmlRequest) {
        XMLResponse result = new XMLResponse();
        try {
            Agent agent = new Agent(xmlRequest.getLogin(), xmlRequest.getPassword());

            switch (xmlRequest.getRequestType()) {
                case "new-agt": {
                    result = service.createAgent(agent);
                    break;
                }
                case "agt-bal": {
                    result = service.getAgentBalance(agent);
                    break;
                }
                default: {
                    result.setResultCode(ResultEnum.OTHER.getCode());
                    throw new Exception("UNKNOWN RequestType");
                }
            }
        } catch (Exception e) {
            result.setResultCode(ResultEnum.OTHER.getCode());
            e.printStackTrace();
        }

        return result;
    }

}
