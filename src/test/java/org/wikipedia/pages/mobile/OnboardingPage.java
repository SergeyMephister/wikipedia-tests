package org.wikipedia.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class OnboardingPage {
    private final SelenideElement skipBtn = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));

    @Step("Нажать на кнопку \"Пропустить\"")
    public OnboardingPage clickSkipBtn() {
        skipBtn.click();
        return this;
    }
}
