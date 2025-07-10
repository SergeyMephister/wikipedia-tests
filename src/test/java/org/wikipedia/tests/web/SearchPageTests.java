package org.wikipedia.tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.wikipedia.config.Project;
import org.wikipedia.pages.web.MainPage;
import org.wikipedia.pages.web.SearchPage;
import org.wikipedia.pages.web.components.NavigationPanel;

@Epic("Web")
@Feature("Страница поиска")
@Tag("web")
public class SearchPageTests extends TestBase {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    NavigationPanel navigationPanel = new NavigationPanel();

    @ParameterizedTest(name = "Отображение сообщения \"Есть страница\" в результатах поиска при выполнении поиска " +
            "по существующему значению = \"{0}\"")
    @ValueSource(strings = {"Java", "Python"})
    @Owner("Сергей Зубенко")
    public void displayMessageAboutExistPage(String value) {
        mainPage.openPage(Project.config.baseUrl());
        navigationPanel.clickSearchBtn();
        searchPage.fillSearchField(value)
                .clickSearchBtn()
                .verifyDisplayMessageAboutExistPage();
    }

    @Test
    @DisplayName("Отображение сообщения \"Создать страницу\" в результатах поиска при выполнении поиска " +
            "по несуществующему значению")
    @Owner("Сергей Зубенко")
    public void displayMessageAboutCreatePage() {
        mainPage.openPage(Project.config.baseUrl());
        navigationPanel.clickSearchBtn();
        searchPage.fillSearchField("Tryr^%E")
                .clickSearchBtn()
                .verifyDisplayMessageAboutCreatePage();
    }
}
