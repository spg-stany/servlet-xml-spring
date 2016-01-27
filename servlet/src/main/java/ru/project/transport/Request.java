package ru.project.transport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "request")
public class Request {

    public static final String REQUEST_TYPE = "request-type";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    //@XmlElement(name = "request-type", required = true)
    private RequestTypeEnum requestType;
    //@XmlElement(name = "login", required = true)
    private String login;
    //@XmlElement(name = "password", required = true)
    private String password;


    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEnum requestType) {
        this.requestType = requestType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
