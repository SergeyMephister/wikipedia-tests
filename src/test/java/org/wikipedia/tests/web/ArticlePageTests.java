package org.wikipedia.tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.wikipedia.config.Project;
import org.wikipedia.pages.web.ArticlePage;
import org.wikipedia.pages.web.MainPage;
import org.wikipedia.pages.web.components.NavigationPanel;

@Epic("Web")
@Feature("Страница статьи")
@Tag("web")
public class ArticlePageTests extends TestBase {
    MainPage mainPage = new MainPage();
    ArticlePage articlePage = new ArticlePage();
    NavigationPanel navigationPanel = new NavigationPanel();

    @ParameterizedTest(name = "Отображение в заголовке статьи значения = \"{0}\" после перехода в карточку статьи")
    @ValueSource(strings = {"Java", "Python"})
    @Owner("Сергей Зубенко")
    public void displayRightHeadingOnArticlePage(String value) {
        mainPage.openPage(Project.config.baseUrl())
                .fillSearchField(value);
        navigationPanel.clickSearchBtn();
        articlePage.verifyPageTitle(value);
    }
}
