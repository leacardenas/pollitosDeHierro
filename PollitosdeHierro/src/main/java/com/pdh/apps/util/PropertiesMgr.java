/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author leaca
 */
public enum PropertiesMgr {

    INSTANCE;
    private static final Logger LOGGER = Logger.getLogger("PropertiesMgr");
    private final Map<String, Properties> propertyMap = new HashMap<>();

    public Properties getProperties(String baseName) {
        Properties returnVal = new Properties();
        if (propertyMap.containsKey(baseName)) {
            returnVal = propertyMap.get(baseName);
        } else {
            try {
                returnVal = convertResourceBundleToProperties(ResourceBundle.getBundle(baseName));
                propertyMap.put(baseName, returnVal);
            } catch (MissingResourceException e) {
                LOGGER.error(baseName + ".properties file was not able to be loaded.", e);
            }
        }
        return returnVal;
    }

    private static Properties convertResourceBundleToProperties(ResourceBundle resource) {
        Properties properties = new Properties();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resource.getString(key));
        }

        return properties;
    }

}
