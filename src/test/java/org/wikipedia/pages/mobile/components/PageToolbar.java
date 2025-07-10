package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PageToolbar {
    private final SelenideElement goUpBtn =
            $x("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]");

    @Step("Нажать на кнопку \"Перейти вверх\"")
    public PageToolbar clickGoUpBtn() {
        goUpBtn.click();
        return this;
    }
}
