package org.wikipedia.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.id;

public class ArticlePage {
    @Getter
    private final SelenideElement pageWebView = $(id("org.wikipedia.alpha:id/page_web_view"));
    public SelenideElement articleTitle(String title) {
        return $x("//android.webkit.WebView[@text=\"" +  title + "\"]");
    }
}
