package org.wikipedia.tests.mobile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikipedia.pages.mobile.FeedPage;
import org.wikipedia.pages.mobile.OnboardingPage;
import org.wikipedia.pages.mobile.components.NavigationBar;

import static com.codeborne.selenide.Condition.selected;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class FeedPageTests extends TestBase {

    @Test
    @DisplayName("Переход на страницу \"Лента\" после нажатия на кнопку \"Пропустить\" на странице онбординга")
    public void successfulOpenFeedPageAfterSkipOnboarding() {
        OnboardingPage onboardingPage = new OnboardingPage();
        FeedPage feedPage = new FeedPage();
        NavigationBar navigationBar = new NavigationBar();

        onboardingPage.clickSkipBtn();
        feedPage.verifyFeedView();
        step("Проверить, что в навигационной панели отображена выбранной вкладка \"Лента\"", () -> {
            navigationBar.getFeedTab().shouldBe(selected);
        });
    }
}
