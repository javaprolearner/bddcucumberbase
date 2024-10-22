package com.javaprolearner.bddcucumberbase.runner;

import java.util.Map;

public class TestContext {

    private static ThreadLocal<TestContext> instance = new ThreadLocal<>();
    private Map<String, String> testData;

    private TestContext() {}

    public static synchronized TestContext getInstance() {
        if (instance.get() == null) {
            instance.set(new TestContext());
        }
        return instance.get();
    }

    public Map<String, String> getTestData() {
        return testData;
    }

    public void setTestData(Map<String, String> testData) {
        this.testData = testData;
    }

}
