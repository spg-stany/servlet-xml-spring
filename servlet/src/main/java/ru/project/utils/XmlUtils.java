package ru.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.transport.ResponseAccount;
import ru.project.transport.ResponseAgent;
import ru.project.transport.ResultEnum;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;

public class XmlUtils {

    private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static String responseAgentToXml(ResultEnum result) {
        ResponseAgent response = new ResponseAgent();
        response.setResultCode(result.getCode());
        try {
            JAXBContext jc = JAXBContext.newInstance(ResponseAgent.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter xml = new StringWriter();
            marshaller.marshal(response, xml);

            return xml.toString();
        } catch (JAXBException e) {
            logger.error("JAXBException", e);
            throw new RuntimeException(e);
        }
    }


    public static String responseAccountToXml(ResultEnum result) {
        return responseAccountToXml(result, BigDecimal.ZERO);
    }


    public static String responseAccountToXml(ResultEnum result, BigDecimal balance) {
        ResponseAccount response = new ResponseAccount();
        response.setResultCode(result.getCode());
        response.setBalance(balance);
        try {
            JAXBContext jc = JAXBContext.newInstance(ResponseAccount.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter xml = new StringWriter();
            marshaller.marshal(response, xml);

            return xml.toString();
        } catch (JAXBException e) {
            logger.error("JAXBException", e);
            throw new RuntimeException(e);
        }
    }

}
