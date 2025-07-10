package org.wikipedia.tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.wikipedia.config.Project;
import org.wikipedia.pages.web.MainPage;
import org.wikipedia.pages.web.components.NavigationPanel;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Web")
@Feature("Главная страница портала")
@Tag("web")
public class MainPageTests extends TestBase {
    MainPage mainPage = new MainPage();
    NavigationPanel navigationPanel = new NavigationPanel();

    @Test
    @DisplayName("Отображение заголовка страницы = \"Википедия — свободная энциклопедия\" при открытии главной " +
            "страницы портала")
    @Owner("Сергей Зубенко")
    public void displayPageTitleAfterOpenPage() {
        mainPage.openPage(Project.config.baseUrl());
        assertEquals("Википедия — свободная энциклопедия", title());

    }

    @Test
    @DisplayName("Отображение поля \"Искать в Википедии\" в навигационной панели после открытия главной страницы")
    @Owner("Сергей Зубенко")
    public void displaySearchFieldInNavigationPanel() {
        mainPage.openPage(Project.config.baseUrl())
                .verifySearchField("Искать в Википедии");

    }

    @ParameterizedTest(name = "Отображение значения = \"{0}\" в предложенных результатах после заполнения поля " +
            "\"Искать в Википедии\"")
    @CsvFileSource(resources = "/testData/searchData.csv")
    @Owner("Сергей Зубенко")
    public void verifySuggestionsResults(String searchValue) {
        mainPage.openPage(Project.config.baseUrl())
                .fillSearchField(searchValue);
        step("Проверить, что список предложенных результатов больше 0", () -> {
            navigationPanel.verifySuggestionResultsSizeGreaterThanZero();
        });
        step("Проверить, что предложенные результаты содержат в себе значение = \"" + searchValue + "\"", () -> {
            assertTrue(navigationPanel.verifySuggestionResultsContainsRequiredText(searchValue));
        });
    }

    @Test
    @DisplayName("Отсутствие предложенных результатов после заполнения поля \"Искать в Википедии\" " +
            "несуществующим значением")
    @Owner("Сергей Зубенко")
    public void verifySuggestionResultsListIsEmpty() {
        mainPage.openPage(Project.config.baseUrl())
                .fillSearchField("lkgF^%46t");
        step("Проверить, что список предложенных результатов отсутствует", () -> {
            navigationPanel.verifySuggestionResultsListIsEmpty();
        });
    }
}
