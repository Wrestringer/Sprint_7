package ru.praktikum.services.qa.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import ru.praktikum.services.qa.scooter.models.Courier;

import static io.restassured.RestAssured.given;

public class StepsCourier {

    @Step("Send POST request to /api/v1/courier")
    public static Response sendPostRequestToCreateCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Compare Message from response")
    public static void compareMessageFromResponse(Response response, int statusCode, String message){
        String messageFromResponse = response.then().statusCode(statusCode).assertThat().extract().path("").toString();
        Assert.assertTrue(messageFromResponse.contains(message));
    }

    @Step("Send POST request to /api/v1/courier/login")
    public static Response sendPostRequestLoginCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Get id courier")
    public static int getCourierIdFromResponseLogin(Response response){
        return response.then().assertThat().extract().path("id");
    }


    @Step("Send DELETE request to /api/v1/courier/:id")
    public static void sendDeleteCourierRequest(int idCourier){
        given()
                .pathParam("id", idCourier)
                .when()
                .delete("/api/v1/courier/{id}")
                .then().statusCode(200);
    }
}
