package org.wikipedia.tests.mobile;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikipedia.helpers.AdditionalMethods;
import org.wikipedia.pages.mobile.*;
import org.wikipedia.pages.mobile.components.*;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class FavoritesListPageTests extends TestBase {

    @Test
    @DisplayName("Удаление статьи из избранного после нажатия на кнопку = \"Удалить выбранные элементы\" " +
            "в панели действий на странице списка \"Сохраненное\"")
    public void deleteArticleFromFavorites() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        SearchPage searchPage = new SearchPage();
        PageActionsBar pageActionsBar = new PageActionsBar();
        DialogWidget dialogWidget = new DialogWidget();
        SnackBar snackBar = new SnackBar();
        NavigationBar navigationBar = new NavigationBar();
        FavoritesPage favoritesPage = new FavoritesPage();
        FavoritesListPage favoritesListPage = new FavoritesListPage();
        AdditionalMethods additionalMethods = new AdditionalMethods();
        DesignBottomSheet designBottomSheet = new DesignBottomSheet();
        ActionModeBar actionModeBar = new ActionModeBar();
        String searchText = "Java";

        step("Добавить статью \"" + searchText + "\" в избранное", () -> {
            onboardingPage.clickSkipBtn();
            feedPage.clickSearchField();
            searchPage.fillSearchField(searchText)
                    .clickFirstSuggestElementTitle();
            dialogWidget.clickCloseBtn();
            pageActionsBar.clickSaveBtn();
        });
        step("Вернуться на страницу \"Лента\"", () -> {
            Selenide.back();
            Selenide.back();
            Selenide.back();
        });
        navigationBar.clickFavoritesTab();
        step("Проверить, что в навигационной панели отображена выбранной вкладка \"Сохранённые\"", () -> {
            navigationBar.getFavoritesTab().shouldBe(selected);
        });
        favoritesPage.clickFavoritesList();
        step("Нажать на кнопку \"Понятно\" в отображенном уведомлении", () ->
                favoritesListPage.getCloseBalloonBtn().click()
        );
        step("Произвести долгое нажатие на ранее добавленную в избранное статью", () ->
            additionalMethods.performLongPress(favoritesListPage.getFirstElementFromFavoritesList(), 3)
        );
        step("Нажать на \"Выбрать\" в меню элемента", () ->
                designBottomSheet.getSelectBtn().click()
        );
        step("Нажать на кнопку \"Удалить выбранные элементы\" в панели действий", () ->
                actionModeBar.getDeleteSelectedBtn().click()
        );
        step("Проверить, что в отображенном уведомлении указано названия статьи = \"" + searchText + "\"", () -> {
            snackBar.getSnackBarText().shouldHave(attribute("text", searchText +
                    " удалена из Сохранённое"));
        });
        step("Проверить, что в отображенном уведомлении присутствует кнопка \"Отменить\"", () -> {
            snackBar.getSnackBarAction().shouldHave(attribute("text", "Отменить"));
        });
        step("Проверить, что в списке избранного отсутствуют добавленные статьи", () -> {
            favoritesListPage.getFavoritesList().shouldBe(empty);
        });
    }
}
