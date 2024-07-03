package ru.praktikum.services.qa.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.services.qa.scooter.models.Order;

import static io.restassured.RestAssured.given;

public class StepsOrder {

    @Step("Send PUT request to /api/v1/orders")
    public static Response sendGetAvailableOrdersForCouriers(int limit, int page){
        return given()
                .pathParam("limit", limit)
                .pathParam("page", page)
                .when()
                .get("/api/v1/orders?limit={limit}&page={page}");
    }

    @Step("Send POST request to /api/v1/orders")
    public static Response sendPostRequestToCreateOrder(Order order){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Get track order")
    public static int getTrackOrderFromResponseCreate(Response response){
        return response.then().assertThat().extract().path("track");
    }


    @Step("Send GET request to /api/v1/orders/track:t")
    public static Response sendGetOrderByTrack(int trackOrder){
        return given()
                .pathParam("t", trackOrder)
                .when()
                .get("/api/v1/orders/track?t={t}");
    }


    @Step("Get id order")
    public static int getIdOrderFromResponse(Response response){
        return response.then().assertThat().extract().path("order.id");
    }


    @Step("Send PUT request to /api/v1/orders/finish/:id")
    public static void sendPutToEndOrderRequest(int idOrder){
        given()
                .pathParam("id", idOrder)
                .when()
                .put("/api/v1/orders/finish/{id}")
                .then().statusCode(200);
    }

}
