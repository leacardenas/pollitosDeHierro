package com.pdh.apps.util;

import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ReadCompanyMailProperties {
    
    private static final Logger LOGGER = Logger.getLogger(ReadCompanyMailProperties.class);
    
    private String mailSubject;
    private String mailBody;
    private String sslSocketFactory;
    private String smtpAuth;
    
    public ReadCompanyMailProperties() throws ExceptionPollitosDeHierro {
        try {
            Properties prop = new Properties();
            InputStream file = ReadCompanyMailProperties.class.getResourceAsStream("/companyMailProperties.properties");
            prop.load(file);
            
           mailSubject = prop.getProperty("subject");
           mailBody = prop.getProperty("mailBody");
           sslSocketFactory = prop.getProperty("SSLSocketFactory");
           smtpAuth = prop.getProperty("smtpAuth");
            
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ExceptionPollitosDeHierro("Error while reading company mail properties, more details: " + e.getMessage());
        }
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getSslSocketFactory() {
        return sslSocketFactory;
    }

    public void setSslSocketFactory(String sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }
            
}