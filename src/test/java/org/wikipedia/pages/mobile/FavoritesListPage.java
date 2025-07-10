package org.wikipedia.pages.mobile;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class FavoritesListPage {
    private final ElementsCollection favoritesList = $$(id("org.wikipedia.alpha:id/page_list_item_container"));
    private final SelenideElement firstElementFromFavoritesList =
            $$(id("org.wikipedia.alpha:id/page_list_item_container")).first(),
            closeBalloonBtn = $(id("org.wikipedia.alpha:id/buttonView"));
}
