package com.javaprolearner.bddcucumberbase.util;

import com.javaprolearner.bddcucumberbase.consts.FrameWorkConstants;
import com.javaprolearner.bddcucumberbase.exceptions.KeyNotFoundExceptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ReadConfig {

    private ReadConfig() {}

    private static Properties prop = new Properties();
    private static final Map<String , String> configMap= new HashMap<>();

    static {
        try {
            FileInputStream fis = new FileInputStream(FrameWorkConstants.getCONFIGFILEPATH());
            prop.load(fis);
//            for (Object key : prop.keySet()) {
//                configMap.put(key.toString(), prop.getProperty(key.toString()));
//            }
            prop.entrySet().forEach(entry -> configMap.put(entry.getKey().toString() , entry.getValue().toString().trim()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //New eager loading
    public static String getConfig(String key) {
        if(Objects.isNull(configMap.get(key))){
            throw new KeyNotFoundExceptions("Property not found: "+key);
        }
        return configMap.get(key);
    }

    public static String getValue (String key) throws IOException {
        String value = prop.getProperty(key);
        if(Objects.isNull(value) || key.isEmpty()) {
            throw new KeyNotFoundExceptions("Property not found: "+key);
        }
        return value;
    }

}
