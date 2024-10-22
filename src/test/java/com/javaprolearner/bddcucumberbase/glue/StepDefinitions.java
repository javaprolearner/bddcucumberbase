package com.javaprolearner.bddcucumberbase.glue;

import com.javaprolearner.bddcucumberbase.configs.DriverManager;
import com.javaprolearner.bddcucumberbase.runner.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Map;
public class StepDefinitions {

    private WebDriver driver = DriverManager.initDriver();

    @Given("I perform some action for {string}")
    public void iPerformSomeAction(String testCaseName) throws InterruptedException {
        // Fetch test data for the scenario
        Map<String, String> testData = TestContext.getInstance().getTestData();

        // Use test data in the steps
        System.out.println("Data for " + testCaseName + ": " + testData);
        //ExtentReport.createTest("Test Case 1", "This test case has passed");
        driver.get("https://www.google.com/");
        Thread.sleep(Duration.ofSeconds(20).toMillis());

    }

    @Then("I validate the outcome")
    public void i_validate_the_outcome() {
        // Write code here that turns the phrase above into concrete actions
    }

}
