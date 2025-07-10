package org.wikipedia.helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.wikipedia.config.Project;

import java.util.Map;

public class DriverSettings {
    public static void configure() {
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (Project.isRemoteWebDriver()) {
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", Project.config.enableVideo(),
                    "enableLog", true));
            Configuration.remote = Project.config.remoteDriverUrl();
        }
        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 60000;
    }

    public static void proxyEnabled() {
        Configuration.proxyEnabled = true;
        Configuration.proxyHost = Project.config.proxyHost();
    }
}
