package com.hai.ws.intercepter;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

/**
 * Created by Administrator on 2017/12/14.
 */
public class AddAuthHeaderIntercepter extends AbstractPhaseInterceptor<SoapMessage> {

    private String name;
    private String pass;

    public AddAuthHeaderIntercepter(String name, String pass) {
        this(Phase.PREPARE_SEND, name, pass);// 发送SOAP消息之前调用拦截器
    }

    public AddAuthHeaderIntercepter(String phase, String name, String pass) {
        super(phase);
        this.name = name;
        this.pass = pass;
    }

    public void handleMessage(SoapMessage message) throws Fault {
        Document document = DOMUtils.newDocument();
        Element authHeaderElement = document.createElement("auth-header");
        Element nameHeaderElement = document.createElement("username");
        Element passHeaderElement = document.createElement("password");

        nameHeaderElement.setTextContent(this.name);
        passHeaderElement.setTextContent(this.pass);

        authHeaderElement.appendChild(nameHeaderElement);
        authHeaderElement.appendChild(passHeaderElement);

        Header authHeader = new Header(new QName("auth-header"), authHeaderElement);
        message.getHeaders().add(authHeader);
    }
}
