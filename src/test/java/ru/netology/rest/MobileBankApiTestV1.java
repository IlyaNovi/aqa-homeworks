package ru.netology.rest;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
        .when()
               .get("/demo/accounts")
        .then()
               .statusCode(200)
                // static import для JsonSchemaValidator.matchesJsonSchemaInClasspath
               .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
        ;
    }

    @Test
    void shouldReturnCorrectCurrency() {
       given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].currency", equalTo("RUR"))
                .body("[1].currency", equalTo("USU"))
                ;
    }
}
