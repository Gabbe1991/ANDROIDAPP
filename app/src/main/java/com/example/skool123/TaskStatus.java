package com.example.skool123;

public enum TaskStatus {
    Open("Open"),
    InProgress("In Progress"),
    Complete("Complete");
    String displayName;
    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
}
