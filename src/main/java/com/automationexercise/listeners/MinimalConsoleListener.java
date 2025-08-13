package com.automationexercise.listeners;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

public class MinimalConsoleListener implements TestExecutionListener {

    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isContainer() && testIdentifier.getSource().isPresent()) {
            if (testIdentifier.getSource().get().toString().contains("class")) {
                System.out.println("\n=== " + testIdentifier.getDisplayName() + " ===");
            }
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
        if (testIdentifier.isTest()) {
            switch (result.getStatus()) {
                case SUCCESSFUL ->
                    System.out.println(GREEN + "✔" + RESET + " " + testIdentifier.getDisplayName());
                case FAILED -> {
                    System.out.println(RED + "✘" + RESET + " " + testIdentifier.getDisplayName());
                    result.getThrowable().ifPresent(t -> {
                        System.out.println();
                        System.out.println("    " + t.getClass().getSimpleName() + ":");
                        String msg = t.getMessage();
                        if (msg != null) {
                            boolean printed = false;
                            for (String line : msg.split("\n")) {
                                if (line.contains("Timeout") || line.contains("waiting for locator") || line.contains("Error {") || line.contains("name='TimeoutError")) {
                                    System.out.println("    " + line.trim());
                                    printed = true;
                                }
                            }
                            // If it is not a UI/Playwright error, print only the first line of the message (as in UI)
                            if (!printed) {
                                String firstLine = msg.split("\n")[0];
                                System.out.println("    " + firstLine.trim());
                            }
                        }
                    });
                }
                default -> {
                }
            }
        }
    }
}