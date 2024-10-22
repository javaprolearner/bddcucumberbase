package com.javaprolearner.bddcucumberbase.runner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.javaprolearner.bddcucumberbase.configs.DriverManager;
import com.javaprolearner.bddcucumberbase.util.ExcelUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.util.Map;
import static com.javaprolearner.bddcucumberbase.configs.DriverConfig.driver;

public class Hooks {

    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        String testCaseName = scenario.getName();
        ExcelUtils excelUtils = new ExcelUtils(System.getProperty("user.dir")+"/src/test/java/com/javaprolearner/bddcucumberbase/resources/excel/DataSheet.xlsx");
        Map<String, String> testData = excelUtils.getDataSheet(testCaseName);
        // Set the data for the current test case in a shared location (ThreadLocal, etc.)
        TestContext.getInstance().setTestData(testData);
    }

    @Before(order = 2)
    public void setup(Scenario scenario) {
        if (extent == null) {
            setupExtentReport();  // Initialize ExtentReport only once
        }

        // Create a test case for each scenario in Extent Reports
        scenarioTest = extent.createTest(scenario.getName());
    }

    @After
    public void teardown(Scenario scenario) {

        if (scenario.isFailed()) {
            // Capture screenshot in case of failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "path_to_screenshot/" + scenario.getName() + ".png";

            // Add screenshot to Extent Report
            scenarioTest.addScreenCaptureFromPath(screenshotPath);
        }

        // Flush the ExtentReports to write results to the report
        extent.flush();

        System.out.println("Closing browser after scenario");
        DriverManager.quitDriver();
    }


    private void setupExtentReport() {
        String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("SDET", "Abhishek Yadav");
        extent.setSystemInfo("Environment", "QA");
    }


}
