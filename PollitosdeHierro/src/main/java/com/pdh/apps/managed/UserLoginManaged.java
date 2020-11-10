/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhUsersDAO;
import com.pdh.apps.model.PdhUser;
import com.pdh.apps.util.JsfUtil;
import com.pdh.apps.util.PasswordStorage;
import com.pdh.apps.util.Utils;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.apache.log4j.Logger;
import org.primefaces.model.StreamedContent;
import com.pdh.apps.util.TimeFunctions;
import java.util.concurrent.TimeUnit;
import javax.faces.context.ExternalContext;

/**
 *
 * @author leaca
 */
@Named
@SessionScoped
public class UserLoginManaged implements Serializable, HttpSessionBindingListener {
    
    private static final String STATIC = "static";
    private static final String ACCENT = "accent";
    private static final String LIGHT = "light";
    private static final String GROUPED_MENU = "groupedMenu";
    private static final String MEDIUM = "medium";
    
    private String menuMode = STATIC;
    private String theme = "pink-accent";
    private String layout = "pink";
    private String layoutColor = ACCENT;
    private String colorMenu = LIGHT;
    private String groupedMenu = GROUPED_MENU;
    private String fontSize = MEDIUM;
    
    private static final Logger LOGGER = Logger.getLogger(UserLoginManaged.class);
    private static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";
    
    private static final String ENGLISH = "English";
    private static final String ESPANOL = "Español";
    private static final String FRANCAIS = "Français";
    private static final String DEUTSCH = "Deutsch";
    private static final String DUTCH = "Nederlands";
    private static final String PORTUGUES = "Português";
    private static final String RUSSIAN = "Русский";
    private static final String ITALIAN = "Italiano";
    private static final String CHINESE = "中文";
    private static final String NORWEGIAN = "Norsk";
    private static final String HEBREW = "עִברִית";
    
    private static final String FULL_DATE_FORMAT = "dd-MMM-yyyy h:mm a";
    private static final String COMPLETE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    private String userName;
    private String password;
    private Date fechaActual;
    private int remainingTrialDays;
    
    @EJB
    private PdhUsersDAO userDao;
    
    PdhUser user;
    private boolean validateTrue;
    
    private String userLocale;
    
    private final String googleApiURL = "http://maps.google.com/maps/api/js?sensor=true";
    
    @Transient
    private static final Map<PdhUser, HttpSession> LOGINS = new HashMap<>();
    
    @PreDestroy
    public void destroy() {
        logOut();
    }
    
    public UserLoginManaged() {
        /**
         * Creates a new instance of UserManaged
         */
    }
    
    public void login(ActionEvent event) {
        try {
            try {
                user = userDao.findByUsername(userName);
//                validateTrue = PasswordStorage.verifyPassword(password, user.getPdhUserUsername());
                if (true) {
                    if (user.getPdhUserActive()) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usr", user);
//                        localeBean.changeLanguage(userLocale);
                        JsfUtil.redirect("Pollitos-De-Hierro-Inicio/");
                        
                    } else {
                        JsfUtil.addErrorMessage("Usuario inactivo");
                    }
                } else {
                    JsfUtil.addErrorMessage("Credenciales invalidas");
                }
            } catch (Exception e) {
                LOGGER.error(e);
                JsfUtil.addErrorMessage("Credenciales invalidas");
            }
        } catch (NullPointerException e) {
            LOGGER.error(e);
            JsfUtil.addWarningMessage("Este usuario ya esta conectado, por favor intente de nuevo.");
        }
    }
    
    public void logOut() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usr");
            JsfUtil.destroySession();
            JsfUtil.redirect("https://pollitosdehierro.org/");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("Error saliendo del sistema");
        }
    }
    
    /**
     * @return the userCompany
     */
    public PdhUser getUser() {
        return user;
    }
    
    /**
     * @param user
     */
    public void setUser(PdhUser user) {
        this.user = user;
    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getFechaActual() {
        fechaActual = getCurrentDate();
        return fechaActual;
    }
    
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = LOGINS.remove(this.getUser());
        if (session != null) {
            session.invalidate();
        }
        LOGINS.put(this.getUser(), event.getSession());
    }
    
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        LOGINS.remove(this.getUser());
    }
    
    public String getLogo() {
        return "/images/pollitosDeHierro-logo.png";
    }
    
    public Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }
    
    public boolean isValidateTrue() {
        return validateTrue;
    }
    
    public void setValidateTrue(boolean validateTrue) {
        this.validateTrue = validateTrue;
    }
    
//    public String getTypeImage() {
//        String type = user.getCompaniesCompanyId().getCompanyImageType();
//        return type.replace("/", "").replace("(", "").replace(")", "").replace("$", "").replace("|", ",");
//    }
//    
//    public String getTypeFile() {
//        String type = user.getCompaniesCompanyId().getCompanyFileType();
//        return type.replace("/", "").replace("(", "").replace(")", "").replace("$", "").replace("|", ",");
//    }
//    
//    public StreamedContent download(String fileName) {
//        StreamedContent file = null;
//        try {
//            file = Utils.getImage(fileName, user.getCompaniesCompanyId().getCompanyName());
//        } catch (IOException e) {
//            LOGGER.error(e);
//            JsfUtil.addErrorMessage(JsfUtil.getMessage("ERROR.DOWNLOADING.SUPPLIER.FILE"));
//        }
//        return file;
//    }
//    
//    public StreamedContent downloadFile(String fileName) {
//        StreamedContent file = null;
//        try {
//            file = Utils.getFile(fileName, user.getCompaniesCompanyId().getCompanyName());
//        } catch (IOException e) {
//            LOGGER.error(e);
//            JsfUtil.addErrorMessage(JsfUtil.getMessage("ERROR.DOWNLOADING.SUPPLIER.FILE"));
//        }
//        return file;
//    }
    
    public String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_DATE_FORMAT, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatSimpleDate(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatSimpleDateTimezone(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            if (user != null) {
                simpleDateFormat.setTimeZone(JsfUtil.getTimeZone(user.getPdhUserTimezone()));
            }
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatSimpleDateFullTimezone(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COMPLETE_DATE_FORMAT, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            if (user != null) {
                simpleDateFormat.setTimeZone(JsfUtil.getTimeZone(user.getPdhUserTimezone()));
            }
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatTime(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatFullTime(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            simpleDateFormat.setTimeZone(JsfUtil.getTimeZone(user.getPdhUserTimezone()));
            
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public void setLocale(String userLanguage) {
        if (userLanguage != null) {
            switch (userLanguage) {
                case ENGLISH:
                    setUserLocale("en");
                    break;
                case ESPANOL:
                    setUserLocale("es");
                    break;
                case FRANCAIS:
                    setUserLocale("fr");
                    break;
                case DEUTSCH:
                    setUserLocale("de");
                    break;
                case DUTCH:
                    setUserLocale("nl");
                    break;
                case PORTUGUES:
                    setUserLocale("pt");
                    break;
                case RUSSIAN:
                    setUserLocale("ru");
                    break;
                case ITALIAN:
                    setUserLocale("it");
                    break;
                case CHINESE:
                    setUserLocale("zh");
                    break;
                case NORWEGIAN:
                    setUserLocale("no");
                    break;
                case HEBREW:
                    setUserLocale("iw");
                    break;
                default:
                    setUserLocale("en");
                    break;
            }
        }
    }
    
    public String getUserLocale() {
        return userLocale;
    }
    
    public void setUserLocale(String userLocale) {
        this.userLocale = userLocale;
    }
    
    public String formatDateWithHour(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_DATE_FORMAT, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            
            if (user != null) {
                simpleDateFormat.setTimeZone(JsfUtil.getTimeZone(user.getPdhUserTimezone()));
            }
            
            return simpleDateFormat.format(date);
        }
        return "";
    }
    
    public String formatAmountToCurrency(Double amount, String mask) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("en"));
        DecimalFormat decimalFormat = new DecimalFormat(mask, decimalFormatSymbols);
        return decimalFormat.format(amount);
    }
    
    public String getCutWord(String text, Integer number) {
        return JsfUtil.getCutWord(text, number);
    }
    
    public void editUserInformation() throws IOException {
        try {
            user.setPdhUserUpdatedBy(getUser());
            user.setPdhUserUpdatedDate(getCurrentDate());
            userDao.edit(user);
            user = userDao.findByUsername(userName);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            JsfUtil.addSuccessMessage("Usuario editado");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage("Error al editar el usuario");
        }
    }
    
    public String getGoogleApiURL() {
        return googleApiURL;
    }

    public String getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayoutColor() {
        return layoutColor;
    }

    public void setLayoutColor(String layoutColor) {
        this.layoutColor = layoutColor;
    }

    public String getColorMenu() {
        return colorMenu;
    }

    public void setColorMenu(String colorMenu) {
        this.colorMenu = colorMenu;
    }

    public String getGroupedMenu() {
        return groupedMenu;
    }

    public void setGroupedMenu(String groupedMenu) {
        this.groupedMenu = groupedMenu;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public int getRemainingTrialDays() {
        return remainingTrialDays;
    }

    public void setRemainingTrialDays(int remainingTrialDays) {
        this.remainingTrialDays = remainingTrialDays;
    }

    public PdhUsersDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(PdhUsersDAO userDao) {
        this.userDao = userDao;
    }
    
}
