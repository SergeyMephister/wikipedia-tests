package org.wikipedia.tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.wikipedia.config.Project;
import org.wikipedia.pages.web.LoginPage;
import org.wikipedia.pages.web.MainPage;
import org.wikipedia.pages.web.components.NavigationPanel;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Epic("Web")
@Feature("Страница авторизации на портала")
@Tag("web")
public class LoginPageTests extends TestBase {
    MainPage mainPage = new MainPage();
    NavigationPanel navigationPanel = new NavigationPanel();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Успешная авторизация на портале")
    @Owner("Сергей Зубенко")
    public void successfulLogin() {
        mainPage.openPage(Project.config.baseUrl());
        navigationPanel.clickLoginBtn();
        loginPage.fillUserNameField(Project.config.accountLogin())
                .fillPasswordField(Project.config.accountPassword())
                .clickLoginBtn();
        step("Проверить, что в навигационной панели отображена кнопка \"Выйти\"", () -> {
            navigationPanel.getLogoutBtn().shouldBe(visible);
        });
        step("Проверить, что в навигационной панели не отображена кнопка \"Войти\"", () -> {
            navigationPanel.getLoginBtn().shouldBe(not(visible));
        });
        navigationPanel.verifyUserNameValue(Project.config.accountLogin());
    }

    @Disabled
    @ParameterizedTest(name = "Отображение сообщения об ошибке при попытке авторизации с {2}")
    @MethodSource("authData")
    @Owner("Сергей Зубенко")
    public void displayErrorMessageAfterUnsuccessfulLogin(String userName, String password, String result) {
        mainPage.openPage(Project.config.baseUrl());
        navigationPanel.clickLoginBtn();
        loginPage.fillUserNameField(userName)
                .fillPasswordField(password)
                .clickLoginBtn()
                .verifyAuthorizationErrorMessage();
    }

    static Stream<String[]> authData() {
        return Stream.of(
                new String[]{Project.config.accountLogin(), "Test123", "неверным паролем"},
                new String[]{"Test", Project.config.accountPassword(), "неверным логином"},
                new String[]{"Test", "Test123", "неверными логином и паролем"}
        );
    }
}
