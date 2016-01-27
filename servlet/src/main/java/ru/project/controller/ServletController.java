package ru.project.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.Agent;
import ru.project.service.BaseService;
import ru.project.transport.Request;
import ru.project.utils.XmlUtils;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

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
        try {
            //Request req = XmlUtils.xmlToRequest(IOUtils.toInputStream(request, "UTF-8"));

            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Request req = (Request) unmarshaller.unmarshal(IOUtils.toInputStream(request, "UTF-8"));

            Agent agent = new Agent(req.getLogin(), req.getPassword());

            switch (req.getRequestType()) {
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
                    result = "UNKNOWN RequestType";
                    break;
                }
            }

        } catch (Exception e) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<response>Fail</response>";
        }

        return result;

    }

}
