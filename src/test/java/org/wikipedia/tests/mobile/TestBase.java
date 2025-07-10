package org.wikipedia.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.wikipedia.helpers.AllureAttachments;
import org.wikipedia.helpers.EmulatorDriverSettings;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    @BeforeAll
    static void setupAndroid() {
        Configuration.browser = EmulatorDriverSettings.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void tearDown() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        closeWebDriver();
    }
}