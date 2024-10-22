package com.javaprolearner.bddcucumberbase.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

import static com.javaprolearner.bddcucumberbase.configs.DriverManager.*;

public final class DriverConfig {

    private DriverConfig() {}

    public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER_PROPERTY_PATH = "webdriver.chrome.driver";
    public static WebDriver driver;

//    public static ChromeOptions getChromeOptions() {
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--remote-allow-origins=*");
//        return options;
//    }
//
//    public static void initDriver(ChromeOptions options) {
//        System.out.println("Initializing Chrome driver...");
//        if (Objects.isNull(getDriver())) {
//            driver = new ChromeDriver(options);
//            driver.manage().deleteAllCookies();
//            driver.manage().window().maximize();
//            setDriver(driver);
//        }
//        System.out.println(Thread.currentThread().getId() + ":" +getDriver());
//    }
//
//    public static void quitDriver() {
//        if(Objects.nonNull(getDriver())) {
//            getDriver().quit();
//            unloadDriver();
//        }
//    }

}
