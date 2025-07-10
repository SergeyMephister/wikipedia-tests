package org.wikipedia.pages.web.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class NavigationPanel {
    @Getter
    private final SelenideElement searchField = $("#searchInput"),
            suggestionResults = $(".suggestions-results"),
            searchBtn = $("#searchButton"),
            loginBtn = $("#pt-login a"),
            logoutBtn = $("#pt-logout a"),
            userName = $("#pt-userpage span");
    private final ElementsCollection suggestionResultsElements = $$(".suggestions-results a");

    public void verifySearchFieldVisible() {
        searchField.shouldBe(Condition.visible);
    }

    public void verifySearchFieldContainsRequiredPlaceholderValue(String placeholderValue) {
        searchField.shouldHave(Condition.attribute("placeholder", placeholderValue));
    }

    public void fillSearchField(String searchText) {
        searchField.setValue(searchText);
    }

    public void verifySuggestionResultsSizeGreaterThanZero() {
        suggestionResultsElements.shouldBe(sizeGreaterThan(0));
    }

    public void verifySuggestionResultsListIsEmpty() {
        suggestionResults.shouldBe(Condition.visible);
        suggestionResultsElements.shouldBe(empty);
    }

    public boolean verifySuggestionResultsContainsRequiredText(String value) {
        List<String> titles = new ArrayList<>();
        suggestionResultsElements.asFixedIterable().forEach(x->titles.add(x.getAttribute("title")));
        for (String title : titles) {
            if (!StringUtils.containsIgnoreCase(title, value)) {
                return false;
            }
        }
        return true;
    }

    @Step("Нажать на кнопку \"Перейти к странице, имеющей в точности такое название\"")
    public void clickSearchBtn() {
        searchBtn.click();
    }

    @Step("Нажать на кнопку \"Войти\" в навигационной панели")
    public void clickLoginBtn() {
        loginBtn.click();
    }

    @Step("Нажать на кнопку \"Выйти\" в навигационной панели")
    public void clickLogoutBtn() {
        logoutBtn.click();
    }

    @Step("Проверить, что в навигационной панели отображено указанное имя пользователя = \"{userNameValue}\"")
    public NavigationPanel verifyUserNameValue(String userNameValue) {
        userName.shouldHave(Condition.text(userNameValue));
        return this;
    }

}
