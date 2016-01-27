package ru.project.controller.transport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class Request {

    //public static final String REQUEST_TYPE = "request-type";
    //public static final String LOGIN = "login";
    //public static final String PASSWORD = "password";
    //private RequestTypeEnum requestType;
    private String requestType;
    private String login;
    private String password;


    @XmlElement(name = "request-type", required = true)
    public String getRequestType() {
        return requestType;
    }

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

    /*
    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEnum requestType) {
        this.requestType = requestType;
    }
    */
}
