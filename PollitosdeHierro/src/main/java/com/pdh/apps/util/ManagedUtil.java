/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;

/**
 *
 * @author JonathanA
 */
public final class ManagedUtil {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ManagedUtil.class);

    private static final String NUMBERS = "123456789";

    private static final String UPPERCASE = "ABCDEFGHIJKLMNPQRSTUVWXYZ";

    private static final String LOWERCASE = "abcdefghijklmnpqrstuvwxyz";

    private static final String MESSAGES = "messages";

    private ManagedUtil() {
    }

    public static Integer getPinNumber(Integer length) {
        return Integer.parseInt(getPassword(NUMBERS, length));
    }

    public static String getPassword(int length) {
        return getPassword(NUMBERS + UPPERCASE + LOWERCASE, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }

//    public static Users findUserBySession() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//        return (Users) httpServletRequest.getSession().getAttribute("usr");
//    }

    public static String getLanguage(String languaje) {
        switch (languaje) {
            case "en":
                return "English";
            case "it":
                return "Italiano";
            case "es":
                return "Español";
            case "de":
                return "Deutsch";
            case "nl":
                return "Nederlands";
            case "fr":
                return "Français";
            case "pt":
                return "Português";
            case "nb":
                return "Norwegian";
            case "sv":
                return "Swedish";
            case "da":
                return "Danish";
            case "pl":
                return "Polish";
            case "ru":
                return "Русский";
            case "zh":
                return "中文";
            case "no":
                return "Norsk";
            case "iw":
                return "עִברִית";
            default:
                return "null";
        }
    }

    public static ResourceBundle getMessage(String language) {
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
            case "Nederlandse":
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
            case "Norwegian":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("nb"));
                break;
            case "Swedish":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("sv"));
                break;
            case "Danish":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("da"));
                break;
            case "Polish":
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("pl"));
                break;
            default:
                messages = ResourceBundle.getBundle(MESSAGES, Locale.forLanguageTag("en"));
                break;
        }
        return messages;
    }

    public static String getLanguageIOS(String languaje) {
        switch (languaje) {
            case "English":
                return "en";
            case "Italian":
                return "it";
            case "Spanish":
                return "es";
            case "German":
                return "de";
            case "Dutch":
                return "nl";
            case "French":
                return "fr";
            case "Portuguese":
                return "pt";
            case "Russian":
                return "ru";
            case "Hebrew":
                return "he";
            case "Norwegian":
                return "nb";
            case "Swedish":
                return "sv";
            case "Danish":
                return "da";
            case "Polish":
                return "pl";
            default:
                return "null";
        }
    }

    public static String formatAmount(Double amount, String currencyMask) {
        return new DecimalFormat(currencyMask).format(amount);
    }

    public static String getNumbers(String text) {
        String numbers = "";
        if (text != null) {
            char[] charString = text.toCharArray();
            for (int i = 0; i < charString.length; i++) {
                if (Character.isDigit(charString[i])) {
                    numbers += charString[i];
                }
            }
        }
        return numbers;
    }

    public static String getInitials(String name) {
        StringTokenizer st = new StringTokenizer(name);
        name = "";
        while (st.hasMoreTokens()) {
            name += st.nextToken().charAt(0);
        }
        return name;
    }

    public static String formatTime(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            return simpleDateFormat.format(date);
        }
        return "";
    }

    public static String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }
}
