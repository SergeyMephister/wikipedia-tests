package org.wikipedia.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class SnackBar {
    @Getter
    private final SelenideElement snackBarText = $(id("org.wikipedia.alpha:id/snackbar_text")),
            snackBarAction = $(id("org.wikipedia.alpha:id/snackbar_action"));
}
