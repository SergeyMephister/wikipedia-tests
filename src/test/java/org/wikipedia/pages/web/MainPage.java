package org.wikipedia.pages.web;

import io.qameta.allure.Step;
import org.wikipedia.pages.web.components.NavigationPanel;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    NavigationPanel navigationPanel = new NavigationPanel();

    @Step("Открыть главную страницу")
    public MainPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Проверить отображение поля поиска в навигационной панели")
    public MainPage verifySearchField(String placeholderValue) {
        navigationPanel.verifySearchFieldVisible();
        navigationPanel.verifySearchFieldContainsRequiredPlaceholderValue(placeholderValue);
        return this;
    }

    @Step("Заполнить поле поиска в навигационной панели значением = \"{searchText}\"")
    public MainPage fillSearchField(String searchText) {
        navigationPanel.fillSearchField(searchText);
        return this;
    }
}
