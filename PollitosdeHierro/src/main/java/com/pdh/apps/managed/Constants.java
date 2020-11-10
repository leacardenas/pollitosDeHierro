/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.util.JsfUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

/**
 *
 * @author leaca
 */
public final class Constants {

    public static final String UTF_8 = "UTF-8";
    public static final String IMAGES = "Images";
    public static final String FILES = "Files";
    public static final String APPLICATION = "application";
    public static final String WINDOWS = "C:";
    public static final String URE_INDEX = "WEB PAGES";
    public static final String S3_BUCKET = "pdh";
    public static final String JDBC = "jdbc/pollitosdehierro";
    public static final Boolean S3 = true;

    private static final String TIME_FORMAT = "h:mm a";
    protected static final List<String> languages = Arrays.asList(
            "Deutsch",
            "English",
            "Español",
            "Français",
            "Italiano",
            "Nederlands",
            "Português",
            "Русский",
            "中文",
            "Norsk",
            "עִברִית"
    );

    protected static final Map<String, String> languagesList;

    private Constants() {
    }

    static {
        languagesList = new HashMap<>();
        languagesList.put("Deutsch", "de");
        languagesList.put("English", "en");
        languagesList.put("Español", "es");
        languagesList.put("Français", "fr");
        languagesList.put("Italiano", "it");
        languagesList.put("Nederlands", "nl");
        languagesList.put("Português", "pt");
        languagesList.put("Русский", "ru");
        languagesList.put("中文", "zh");
        languagesList.put("Norsk", "no");
        languagesList.put("עִברִית", "he");
    }

    private Calendar calendar = Calendar.getInstance();

    protected static final List<SelectItem> getHours12OfTheDay() throws ParseException {
        return Arrays.asList(
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("12:00 AM"), "12:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("1:00 AM"), "1:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("2:00 AM"), "2:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("3:00 AM"), "3:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("4:00 AM"), "4:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("5:00 AM"), "5:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("6:00 AM"), "6:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("7:00 AM"), "7:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("8:00 AM"), "8:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("9:00 AM"), "9:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("10:00 AM"), "10:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("11:00 AM"), "11:00 AM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("12:00 PM"), "12:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("1:00 PM"), "1:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("2:00 PM"), "2:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("3:00 PM"), "3:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("4:00 PM"), "4:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("5:00 PM"), "5:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("6:00 PM"), "6:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("7:00 PM"), "7:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("8:00 PM"), "8:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("9:00 PM"), "9:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("10:00 PM"), "10:00 PM"),
                new SelectItem(new SimpleDateFormat(TIME_FORMAT).parse("11:00 PM"), "11:00 PM"));
    }

    protected static final List<SelectItem> getHours24OfTheDay() throws ParseException {
        return Arrays.asList(
                new SelectItem(new SimpleDateFormat("k:mm").parse("1:00"), "1:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("2:00"), "2:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("3:00"), "3:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("4:00"), "4:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("5:00"), "5:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("6:00"), "6:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("7:00"), "7:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("8:00"), "8:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("9:00"), "9:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("10:00"), "10:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("11:00"), "11:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("12:00"), "12:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("13:00"), "13:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("14:00"), "14:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("15:00"), "15:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("16:00"), "16:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("17:00"), "17:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("18:00"), "18:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("19:00"), "19:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("20:00"), "20:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("21:00"), "21:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("22:00"), "22:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("23:00"), "23:00"),
                new SelectItem(new SimpleDateFormat("k:mm").parse("24:00"), "24:00"));
    }

}
