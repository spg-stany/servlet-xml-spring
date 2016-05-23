package ru.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ru.project.controller.transport.Request;
import ru.project.controller.transport.ResponseAccount;
import ru.project.controller.transport.ResponseAgent;
import ru.project.controller.transport.ResultEnum;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
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
