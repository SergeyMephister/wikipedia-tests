package org.wikipedia.tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikipedia.pages.mobile.ArticlePage;
import org.wikipedia.pages.mobile.FeedPage;
import org.wikipedia.pages.mobile.OnboardingPage;
import org.wikipedia.pages.mobile.SearchPage;
import org.wikipedia.pages.mobile.components.DialogWidget;
import org.wikipedia.pages.mobile.components.PageActionsBar;
import org.wikipedia.pages.mobile.components.SnackBar;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@Epic("Android")
@Feature("Страница статьи")
@Tag("android")
public class ArticlePageTests extends TestBase {
    private final String searchText = "Java";

    @Test
    @DisplayName("Добавление статьи в избранное после нажатия на кнопку \"Сохранить\" на странице статьи")
    @Owner("Сергей Зубенко")
    public void addArticleToFavorites() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        SearchPage searchPage = new SearchPage();
        PageActionsBar pageActionsBar = new PageActionsBar();
        ArticlePage articlePage = new ArticlePage();
        DialogWidget dialogWidget = new DialogWidget();
        SnackBar snackBar = new SnackBar();

        onboardingPage.clickSkipBtn();
        feedPage.clickSearchField();
        searchPage.fillSearchField(searchText)
                .clickFirstSuggestElementTitle();
        dialogWidget.clickCloseBtn();
        step("Проверить, что отображена страница статьи с заголовком = \"" + searchText + "\"", () -> {
            articlePage.articleTitle(searchText).shouldBe(visible);
        });
        pageActionsBar.clickSaveBtn();
        step("Проверить, что в отображенном уведомлении указано значение названия статьи", () -> {
            snackBar.getSnackBarText().shouldHave(attribute("text", searchText +
                    " сохранено. Хотите добавить в список?"));
        });
        step("Проверить, что в отображенном уведомлении отображена кнопка \"Добавить в список\"", () -> {
            snackBar.getSnackBarAction().shouldHave(attribute("text", "Добавить в список"));
        });
    }
}
