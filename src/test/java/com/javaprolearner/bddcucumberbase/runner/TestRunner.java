package com.javaprolearner.bddcucumberbase.runner;

import com.javaprolearner.bddcucumberbase.configs.DriverManager;
import com.javaprolearner.bddcucumberbase.util.ExcelUtils;
import com.javaprolearner.bddcucumberbase.util.ReadConfig;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.javaprolearner.bddcucumberbase.configs.DriverConfig.*;
import static com.javaprolearner.bddcucumberbase.configs.DriverManager.*;

@CucumberOptions(
        features = "src/test/java/com/javaprolearner/bddcucumberbase/resources/feature",
        glue = {"com.javaprolearner.bddcucumberbase.glue",
                "com.javaprolearner.bddcucumberbase.runner"
        },
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setup() {
        ExcelUtils excelUtils = new ExcelUtils(System.getProperty("user.dir")+"/src/test/java/com/javaprolearner/bddcucumberbase/RunManager.xlsx");
        Map<String, Boolean> runManagerData = excelUtils.getRunManagerData();

        // Filter out test cases that are not marked for execution in runmanager.xlsx
        System.setProperty("runCases", runManagerData.toString());
        // Convert the map to a string (key=true/false,key=true/false,...)
        StringBuilder runCasesBuilder = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : runManagerData.entrySet()) {
            runCasesBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
        }
        // Remove the last comma
        String runCasesString = runCasesBuilder.toString().replaceAll(",$", "");

        // Store it as a system property
        System.setProperty("runCases", runCasesString);

    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
        initDriver().manage().window().maximize();
        initDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        Object[][] scenarios = super.scenarios();
        String runCasesString = System.getProperty("runCases");

        // Convert the string back to a Map
        Map<String, Boolean> runCases = new HashMap<>();
        for (String entry : runCasesString.split(",")) {
            String[] pair = entry.split("=");
            runCases.put(pair[0], Boolean.parseBoolean(pair[1]));
        }

        //Filter scenarios based on the map
        return Arrays.stream(scenarios)
                .filter(scenario -> runCases.getOrDefault(scenario[0].toString().replace("\"" , ""), false)) // Run only the selected test cases
                .toArray(Object[][]::new);
    }

    @AfterSuite()
    public void afterSuite() {
        System.out.println("After Suite");
        DriverManager.quitDriver();
    }

}
