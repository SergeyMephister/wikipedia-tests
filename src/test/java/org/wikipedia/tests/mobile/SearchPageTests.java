package org.wikipedia.tests.mobile;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikipedia.pages.mobile.ArticlePage;
import org.wikipedia.pages.mobile.FeedPage;
import org.wikipedia.pages.mobile.OnboardingPage;
import org.wikipedia.pages.mobile.SearchPage;
import org.wikipedia.pages.mobile.components.DialogWidget;
import org.wikipedia.pages.mobile.components.PageToolbar;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchPageTests extends TestBase {
    private final String searchText = "Java";

    @Test
    @DisplayName("Отображение в заголовке первого элемента списка предлагаемых результатов указанного значения " +
            "после заполнения поля \"Поиск по Википедии\"")
    public void verifyFirstSuggestElementTitle() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        SearchPage searchPage = new SearchPage();

        onboardingPage.clickSkipBtn();
        feedPage.clickSearchField();
        searchPage.fillSearchField(searchText)
                .verifyFirstSuggestElementTitle(searchText);
    }

    @Test
    @DisplayName("Возврат на страницу поиска после нажатия на кнопку \"Перейти вверх\" на странице статьи")
    public void returnToSearchPageFromArticlePageAfterClickOnGoUpBtn() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        SearchPage searchPage = new SearchPage();
        PageToolbar pageToolbar = new PageToolbar();
        ArticlePage articlePage = new ArticlePage();
        DialogWidget dialogWidget = new DialogWidget();

        onboardingPage.clickSkipBtn();
        feedPage.clickSearchField();
        searchPage.fillSearchField(searchText)
                .clickFirstSuggestElementTitle();
        dialogWidget.clickCloseBtn();
        step("Проверить, что отображена страница статьи с заголовком = \"" + searchText + "\"", () -> {
            articlePage.articleTitle(searchText).shouldBe(visible);
        });
        pageToolbar.clickGoUpBtn();
        step("Проверить, что на странице отображены результаты поиска", () -> {
            searchPage.getSearchResultsDisplay().shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Возврат на страницу поиска после нажатия на системную кнопку устройства \"Назад\" на странице статьи")
    public void returnToSearchPageFromArticlePageAfterClickOnBackBtn() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        SearchPage searchPage = new SearchPage();
        ArticlePage articlePage = new ArticlePage();
        DialogWidget dialogWidget = new DialogWidget();

        onboardingPage.clickSkipBtn();
        feedPage.clickSearchField();
        searchPage.fillSearchField(searchText)
                .clickFirstSuggestElementTitle();
        dialogWidget.clickCloseBtn();
        step("Проверить, что отображена страница статьи с заголовком = \"" + searchText + "\"", () -> {
            articlePage.articleTitle(searchText).shouldBe(visible);
        });
        step("Нажать на системную кнопку устройства \"Назад\"", Selenide::back);
        step("Проверить, что на странице отображены результаты поиска", () -> {
            searchPage.getSearchResultsDisplay().shouldBe(visible);
        });
    }
}
