package org.wikipedia.tests.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikipedia.api.model.check.request.CheckRequestModel;
import org.wikipedia.api.model.check.response.CheckResponseModel;
import org.wikipedia.api.model.formula.response.FormulaResponseModel;
import org.wikipedia.helpers.AdditionalMethods;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.wikipedia.api.specs.CheckSpec.checkRequestSpec;
import static org.wikipedia.api.specs.CheckSpec.checkResponseSpec;
import static org.wikipedia.api.specs.FormulaSpec.formulaRequestSpec;
import static org.wikipedia.api.specs.FormulaSpec.formulaResponseSpec;
import static org.wikipedia.api.specs.RenderSpec.renderRequestSpec;
import static org.wikipedia.api.specs.RenderSpec.renderResponseSpec;

@Tag("api")
public class MathTests {
    AdditionalMethods additionalMethods = new AdditionalMethods();

    @Test
    @DisplayName("Валидация переданной TeX формулы и получение ее нормализованного значения через api с " +
            "помощью метода /media/math/check/{type}")
    public void checkTexFormulaAndGetNormalizeValue() {
        CheckRequestModel requestModel = new CheckRequestModel();

        CheckResponseModel responseModel = step("Отправить запрос на проверку корректности " +
                "переданной TeX формулы и получение нормализованного значения " +
                "через api с помощью метода /media/math/check/{type}", () ->
                given(checkRequestSpec)
                        .body(requestModel)
                        .when()
                        .post("/check/tex")
                        .then()
                        .spec(checkResponseSpec)
                        .extract().response().as(CheckResponseModel.class));

        step("Проверить, что в ответе метода отображаются следующие значения в указанных ключах:", () -> {
            assertTrue(responseModel.isSuccess());
            assertEquals(requestModel.getQuery(), responseModel.getChecked());
            assertFalse(responseModel.isEndsWithDot());
        });
        step("Проверить, что в ответе метода отображается пустой массив \"requiredPackages\"", () -> {
            assertTrue(responseModel.getRequiredPackages().isEmpty());
        });
        step("Проверить, что в ответе метода в массиве \"identifiers\" содержатся значения = значению " +
                "переданного в ключе \"q\" запроса, но разбитые посимвольно", () -> {
            assertTrue(additionalMethods.compareData(responseModel.getIdentifiers(), requestModel.getQuery()));
        });
    }

    @Test
    @DisplayName("Получение ранее сохраненной формулы по переданному значению hash через api с " +
            "помощью метода /media/math/formula/{hash}")
    public void getPreviouslyStoredFormula() {
        CheckRequestModel requestModel = new CheckRequestModel();

        Response response = step("Отправить запрос на проверку корректности " +
                "переданной TeX формулы и получение нормализованного значения " +
                "через api с помощью метода /media/math/check/{type}", () ->
                given(checkRequestSpec)
                        .body(requestModel)
                        .when()
                        .post("/check/tex")
                        .then()
                        .spec(checkResponseSpec)
                        .extract().response());

        String xResourceLocation = response.getHeader("x-resource-location");
        FormulaResponseModel responseModel = step("Отправить запрос на получение ранее сохраненной формулы по " +
                "переданному значению hash через api с помощью метода /media/math/formula/{hash}", () ->
                given(formulaRequestSpec)
                        .when()
                        .get("/formula/" + xResourceLocation)
                        .then()
                        .spec(formulaResponseSpec)
                        .extract().response().as(FormulaResponseModel.class));

        step("Проверить, что в ответе метода отображаются следующие значения в указанных ключах:", () -> {
            assertEquals(requestModel.getQuery(), responseModel.getQ());
            assertEquals("tex", responseModel.getType());
        });
    }

    @Test
    @DisplayName("Получение отрисованной формулы по переданному значению hash через api с " +
            "помощью метода /media/math/render/{format}/{hash}")
    public void getRenderedFormulaInTheGivenFormat() {
        CheckRequestModel requestModel = new CheckRequestModel();

        Response checkResponse = step("Отправить запрос на проверку корректности " +
                "переданной TeX формулы и получение нормализованного значения " +
                "через api с помощью метода /media/math/check/{type}", () ->
                given(checkRequestSpec)
                        .body(requestModel)
                        .when()
                        .post("/check/tex")
                        .then()
                        .spec(checkResponseSpec)
                        .extract().response());

        String xResourceLocation = checkResponse.getHeader("x-resource-location");
        Response renderResponse = step("Отправить запрос на получение отрисованной формулы по " +
                "переданному значению hash через api с помощью метода /media/math/render/{format}/{hash}", () ->
                given(renderRequestSpec)
                        .when()
                        .get("/render/svg/" + xResourceLocation)
                        .then()
                        .spec(renderResponseSpec)
                        .extract().response());
        step("Проверить, что в ответе метода отображается верный текст по пути \"svg.title\"", () -> {
            assertEquals(requestModel.getQuery(), renderResponse.xmlPath().getString("svg.title"));
        });
    }
}
