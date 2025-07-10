package org.wikipedia.api.model.check.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.Data;

import java.util.Locale;

@Data
public class CheckRequestModel {
    @JsonIgnore
    Faker faker = new Faker(Locale.ENGLISH);

    @JsonProperty("q")
    private String query = faker.name().firstName();
}
