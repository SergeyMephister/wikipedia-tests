package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class NavigationBar {
    @Getter
    private final SelenideElement feedTab = $(id("org.wikipedia.alpha:id/nav_tab_explore")),
            favoritesTab = $(id("org.wikipedia.alpha:id/nav_tab_reading_lists"));

    @Step("Нажать на кнопку \"Сохранённые\" в навигационной панели")
    public NavigationBar clickFavoritesTab() {
        favoritesTab.click();
        return this;
    }
}
