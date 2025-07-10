package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class DesignBottomSheet {
    private final SelenideElement selectBtn = $(id("org.wikipedia.alpha:id/reading_list_item_select"));
}
