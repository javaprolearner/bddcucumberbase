package com.javaprolearner.bddcucumberbase.enums;

public enum Severity {

    Critical(1),
    High(2),
    Medium(3),
    Low(4);

    private int severityValue;

    Severity(int severityValue) {
        this.severityValue = severityValue;
    }

    public int getSeverityValue() {
        return severityValue;
    }

}
