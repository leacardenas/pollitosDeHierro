package com.pdh.pollitosdehierroapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

public final class JsfUtil {

    private static final String MESSAGES = "messages";

    private JsfUtil() {
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public static void destroySession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public static void addErrorMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtil.getMessage("ERROR.PROCESS"), msg));
    }

    public static void addWarningMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage("warning", new FacesMessage(FacesMessage.SEVERITY_WARN, JsfUtil.getMessage("WARNING.PROCESS"), msg));
    }

    public static void addSuccessMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage("success", new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtil.getMessage("SUCCESS.PROCESS"), msg));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public static void addInfoMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage("info", new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtil.getMessage("INFO.PROCESS"), msg));
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static void redirect(String path) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(path);
    }

    public static String getIpAddress() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String[] splitString = request.getHeader("X-FORWARDED-FOR") != null ? request.getHeader("X-FORWARDED-FOR").split(",") : null;
        String ipAddress = splitString != null ? splitString[0] : null;
        return ipAddress == null ? request.getRemoteAddr() : ipAddress;
    }

    public static void executeRequest(String name) {
        RequestContext.getCurrentInstance().execute("PF('" + name + "').hide();");
    }
    
    public static void startRequest(String name) {
        RequestContext.getCurrentInstance().execute("PF('" + name + "').start();");
    }

    public static void openDialog(String name) {
        RequestContext.getCurrentInstance().execute("PF('" + name + "').show();");
    }

    public static void updateComponent(String name) {
        RequestContext.getCurrentInstance().update(name);
    }

    public static void updateCellDatatable(String name) {
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(name);
    }

    public static String getPath() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public static String getContextPath() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getContextPath().replace("/", "");
    }

    public static String getPrefixServiceName() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getServerName().contains("localhost")) {
            return "localhost";
        } else {
            return request.getServerName().substring(0, request.getServerName().indexOf("."));
        }
    }

    public static String getQueryString() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getQueryString();
    }

    public static String getServletPath() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getServletPath();
    }

    public static String getApplicationName() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getContextPath();
    }

    public static String getMessage(String key) {
        ResourceBundle messages = ResourceBundle.getBundle(MESSAGES, Faces.getContext().getViewRoot().getLocale());
        return messages.getString(key);
    }

    public static ResourceBundle getMessageBundle(String language) {
        ResourceBundle messages;
        switch (language) {
            case "English":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("en"));
                break;
            case "Español":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("es"));
                break;
            case "Français":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("fr"));
                break;
            case "Italiano":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("it"));
                break;
            case "Deutsch":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("de"));
                break;
            case "Nederlands":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("nl"));
                break;
            case "Português":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("pt"));
                break;
            case "Pусский":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("ru"));
                break;
            case "中文":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("zh"));
                break;
            case "Norsk":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("no"));
                break;
            case "עִברִית":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("iw"));
                break;
            default:
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("en"));
                break;
        }
        return messages;
    }

    public static String getResponse(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        return response.toString();
    }

    public static TimeZone getTimeZone(String companyTimezone) {
        return TimeZone.getTimeZone(companyTimezone);
    }

    public static String[] getCountries() {
        return Locale.getISOCountries();
    }

    public static String getCutWord(String text, Integer size) {
        if (text == null) {
            return "";
        }

        if (text.length() > size) {
            text = text.substring(0, size - 1);
        }

        return text;
    }

    public static String removeNamespace(String start, String end, String text) {
        String result = text.replaceAll("(" + start + ")" + "(.*?)" + "(" + end + ")", start + end);
        return result;
    }

    public static void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
