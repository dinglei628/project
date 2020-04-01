package com.demo.utils;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * properties utils
 */
public class PropertiesUtils {

    private static Properties props;

    /**
     * load properties file
     * @param fileName
     */
    private static void loadPropertiesFile(String fileName) {
        try {
            props = new Properties();
            InputStreamReader inputStream = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
            props.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get property
     * @param fileName
     * @param key
     * @return
     */
    public static String getProperty(String fileName,String key) {
        loadPropertiesFile(fileName);
        return props.getProperty(key);
    }
}
