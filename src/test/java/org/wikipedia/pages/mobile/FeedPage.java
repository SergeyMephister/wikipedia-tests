package org.wikipedia.pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class FeedPage {
    private final SelenideElement feedView = $(id("org.wikipedia.alpha:id/feed_view"));
    private final SelenideElement searchField = $(id("org.wikipedia.alpha:id/search_container"));

    @Step("Нажать на поле \"Поиск по Википедии\"")
    public FeedPage clickSearchField() {
        searchField.click();
        return this;
    }

    @Step("Проверить, что отображена страница \"Лента\"")
    public FeedPage verifyFeedView() {
        feedView.shouldBe(Condition.visible);
        return this;
    }
}
