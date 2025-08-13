package com.automationexercise;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import com.automationexercise.listeners.MinimalConsoleListener;

public class RunWithDisplayNameListener {

    public static void main(String[] args) {
        LauncherDiscoveryRequest request;
        if (args.length == 2 && args[0].equals("--select-method")) {
            request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectMethod(args[1]))
                    .build();
        } else if (args.length == 2 && args[0].equals("--select-class")) {
            request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(args[1]))
                    .build();
        } else {
            request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectPackage("com.automationexercise.tests"))
                    .build();
        }
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(new MinimalConsoleListener());
        launcher.execute(request);
    }
}