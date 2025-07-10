package org.wikipedia.pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement userNameField = $("input[name=wpName]"),
            passwordField = $("input[name=wpPassword]"),
            loginBtn = $("button[name=wploginattempt]"),
            authorizationErrorMessage = $(".cdx-message__content");

    @Step("Заполнить поле \"Имя учётной записи\"")
    public LoginPage fillUserNameField(String userName) {
        userNameField.setValue(userName);
        return this;
    }

    @Step("Заполнить поле \"Пароль\"")
    public LoginPage fillPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Нажать на кнопку \"Войти\"")
    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

    @Step("Проверить отображение ошибки о введении неверных данных")
    public LoginPage verifyAuthorizationErrorMessage() {
        authorizationErrorMessage.shouldHave(Condition.text("Введены неверные имя участника или пароль.\n" +
                "Попробуйте ещё раз."));
        return this;
    }
}
