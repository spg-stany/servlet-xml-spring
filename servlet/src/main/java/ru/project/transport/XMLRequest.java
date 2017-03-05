package ru.project.transport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class XMLRequest {

    private String requestType;
    private String login;
    private String password;

    private RequestTypeEnum reqType;

    public String getRequestType() {
        return requestType;
    }

    @XmlElement(name = "request-type", required = true)
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @XmlElement(name = "login", required = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlElement(name = "password", required = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestTypeEnum getReqType() {
        return reqType;
    }

    public void setReqType(RequestTypeEnum requestType) {
        this.reqType = requestType;
    }

}
