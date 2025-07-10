package org.wikipedia.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.wikipedia.config.Project;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static org.hamcrest.Matchers.*;
import static org.wikipedia.helpers.CustomAllureListener.withCustomTemplates;

public class CheckSpec {
    public static RequestSpecification checkRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType("application/json")
            .baseUri(Project.config.baseUrl())
            .basePath("/api/rest_v1/media/math");

    public static ResponseSpecification checkResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .expectStatusCode(200)
            .expectHeader("x-resource-location", not(emptyString()))
            .build();
}
