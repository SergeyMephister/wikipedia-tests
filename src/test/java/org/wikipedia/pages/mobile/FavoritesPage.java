package org.wikipedia.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class FavoritesPage {
    private final SelenideElement favoritesList = $(id("org.wikipedia.alpha:id/item_title_container"))
            .$(byAttribute("text","Сохранённое"));

    @Step("Нажать на список \"Сохранённое\"")
    public FavoritesPage clickFavoritesList() {
        favoritesList.click();
        return this;
    }
}
