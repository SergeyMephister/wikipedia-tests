package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class PageActionsBar {
    @Getter
    private final SelenideElement saveBtn = $(id("org.wikipedia.alpha:id/page_save")),
            deleteFromFavorites = $$(id("org.wikipedia.alpha:id/title")).last();

    @Step("Нажать на кнопку \"Сохранить\" в панели действий страницы")
    public PageActionsBar clickSaveBtn() {
        saveBtn.click();
        return this;
    }

    @Step("Нажать на \"Удалить из Сохранённое\" в выпадающем списке кнопки \"Сохранить\"")
    public PageActionsBar clickDeleteFromFavorites() {
        deleteFromFavorites.click();
        return this;
    }
}
