package org.wikipedia.pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    private final SelenideElement searchField = $("#searchText input"),
            searchBtn = $("button[type=submit]"),
            messageAboutExistPage = $(".mw-search-exists b"),
            messageAboutCreatePage = $(".mw-search-createlink b");

    @Step("Заполнить поле поиска указанным значением = \"{searchText}\"")
    public SearchPage fillSearchField(String searchText) {
        searchField.setValue(searchText);
        return this;
    }

    @Step("Нажать на кнопку \"Найти\"")
    public SearchPage clickSearchBtn() {
        searchBtn.click();
        return this;
    }

    @Step("Проверить, что в результатах поиска отображено сообщение \"Есть страница\"")
    public SearchPage verifyDisplayMessageAboutExistPage() {
        messageAboutExistPage.shouldHave(Condition.text("Есть страница"));
        return this;
    }

    @Step("Проверить, что в результатах поиска отображено сообщение \"Создать страницу\"")
    public SearchPage verifyDisplayMessageAboutCreatePage() {
        messageAboutCreatePage.shouldHave(Condition.text("Создать страницу"));
        return this;
    }
}
