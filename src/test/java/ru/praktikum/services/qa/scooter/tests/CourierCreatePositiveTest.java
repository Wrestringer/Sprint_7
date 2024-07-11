package ru.praktikum.services.qa.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.services.qa.scooter.BaseTest;
import ru.praktikum.services.qa.scooter.models.Courier;

import static ru.praktikum.services.qa.scooter.constants.Messages.*;
import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.*;
import static ru.praktikum.services.qa.scooter.utils.Utils.randomString;

public class CourierCreatePositiveTest extends BaseTest {

    private static final String RANDOM_LOGIN_OF_COURIER = randomString();
    private static final String RANDOM_PASSWORD_OF_COURIER = randomString();
    Courier courier;


    @After
    public void tearDown() {
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        courier.setId(getCourierIdFromResponseLogin(responseFromLoginCourier));
        //Удаление курьера по его id
        sendDeleteCourierRequest(courier.getId());
    }


    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение курьера со всеми полями без дублирования") // описание теста
    public void postCreateCourierFirstTime() {
        courier = new Courier(RANDOM_LOGIN_OF_COURIER, RANDOM_PASSWORD_OF_COURIER, "ИмяКурьера");

        Response responseFromCreateCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateCourier, 201, OK_MESSAGE_RESPONSE);

    }

    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение двух одинаковых курьеров") // описание теста
    public void postCreateTwoSimilarCourier() {
        courier = new Courier(RANDOM_LOGIN_OF_COURIER, RANDOM_PASSWORD_OF_COURIER, "ИмяКурьера");

        Response responseFromCreateFirstCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateFirstCourier, 201, OK_MESSAGE_RESPONSE);

        Response responseFromCreateSecondCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateSecondCourier, 409, THIS_LOGIN_ALREADY_EXIST_MESSAGE_RESPONSE);

    }

    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение курьера с только обязательными полями (без имени)") // описание теста
    public void postCreateCourierWithoutName() {
        courier = new Courier(RANDOM_LOGIN_OF_COURIER, RANDOM_PASSWORD_OF_COURIER, null);

        Response responseFromCreateCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateCourier, 201, OK_MESSAGE_RESPONSE);

    }


}
