package org.wikipedia.pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ArticlePage {
    private final SelenideElement pageTitle = $(".mw-page-title-main");

    @Step("Проверить, что в заголовке статьи указано значение = \"{pageTitleValue}\"")
    public ArticlePage verifyPageTitle(String pageTitleValue) {
        pageTitle.shouldHave(Condition.text(pageTitleValue));
        return this;
    }
}
