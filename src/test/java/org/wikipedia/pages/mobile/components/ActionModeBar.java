package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class ActionModeBar {
    private final SelenideElement deleteSelectedBtn = $(id("org.wikipedia.alpha:id/menu_delete_selected"));
}
