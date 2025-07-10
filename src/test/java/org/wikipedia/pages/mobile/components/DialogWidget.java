package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class DialogWidget {
    private final SelenideElement closeBtn = $(id("org.wikipedia.alpha:id/closeButton"));

    @Step("Нажать на кнопку \"Закрыть\" в отображенном виджете")
    public DialogWidget clickCloseBtn() {
        closeBtn.click();
        return this;
    }
}
