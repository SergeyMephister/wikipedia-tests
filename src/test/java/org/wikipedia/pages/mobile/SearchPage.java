package org.wikipedia.pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class SearchPage {
    @Getter
    private final SelenideElement searchField = $(id("org.wikipedia.alpha:id/search_src_text")),
            searchResultsDisplay = $(id("org.wikipedia.alpha:id/search_results_display"));
    private final SelenideElement firstSuggestElement = $$(id("org.wikipedia.alpha:id/page_list_item_title"))
            .first();

    @Step("Заполнить поле \"Поиск по Википедии\" значением = \"{searchText}\"")
    public SearchPage fillSearchField(String searchText) {
        searchField.sendKeys(searchText);
        return this;
    }

    @Step("Проверить, что первый элемент из предлагаемых результатов содержит в себе значение " +
            "заголовка = \"{titleValue}\"")
    public SearchPage verifyFirstSuggestElementTitle(String titleValue) {
        firstSuggestElement.shouldHave(Condition.attribute("text", titleValue));
        return this;
    }

    @Step("Нажать на первый элемент из предлагаемых результатов поиска")
    public SearchPage clickFirstSuggestElementTitle() {
        firstSuggestElement.click();
        return this;
    }
}
