package com.javaprolearner.bddcucumberbase.consts;

public class FrameWorkConstants {

    private FrameWorkConstants() {}

    private static final String CONFIGFILEPATH = System.getProperty("user.dir")+"/src/test/java/com/javaprolearner/bddcucumberbase/config.properties";

    public static String getCONFIGFILEPATH() {
        return CONFIGFILEPATH;
    }

}
