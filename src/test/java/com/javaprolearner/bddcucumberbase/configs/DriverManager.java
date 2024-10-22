package com.javaprolearner.bddcucumberbase.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverManager {

    private DriverManager() {}

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }


    public static WebDriver initDriver() {
        if (threadLocalDriver.get() == null) {
            threadLocalDriver.set(new ChromeDriver(getChromeOptions()));
        }
        return threadLocalDriver.get();
    }


    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();  // Ensure WebDriver is properly closed and removed
        }
    }
}
