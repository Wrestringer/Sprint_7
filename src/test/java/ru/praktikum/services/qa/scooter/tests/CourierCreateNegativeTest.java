package ru.praktikum.services.qa.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.services.qa.scooter.BaseTest;
import ru.praktikum.services.qa.scooter.models.Courier;

import static ru.praktikum.services.qa.scooter.constants.Messages.INSUFFICIENT_DATA_TO_CREATE;
import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.compareMessageFromResponse;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.sendPostRequestToCreateCourier;
import static ru.praktikum.services.qa.scooter.utils.Utils.randomString;

public class CourierCreateNegativeTest extends BaseTest {

    private static final String RANDOM_LOGIN_OF_COURIER = randomString();
    private static final String RANDOM_PASSWORD_OF_COURIER = randomString();
    Courier courier;


    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение курьера без логина") // описание теста
    public void postCreateCourierWithoutLogin() {
        courier = new Courier(null, RANDOM_PASSWORD_OF_COURIER, "ИмяКурьера");

        Response responseFromCreateCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateCourier, 400, INSUFFICIENT_DATA_TO_CREATE);

    }

    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение курьера без пароля") // описание теста
    public void postCreateCourierWithoutPassword() {
        courier = new Courier(RANDOM_LOGIN_OF_COURIER, null, "ИмяКурьера");

        Response responseFromCreateCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateCourier, 400, INSUFFICIENT_DATA_TO_CREATE);

    }

    @Test
    @DisplayName("Создание курьера /api/v1/courier") // имя теста
    @Description("Создаение курьера без логина и пароля") // описание теста
    public void postCreateCourierWithoutLoginAndPassword() {
        courier = new Courier(null, null, "ИмяКурьера");

        Response responseFromCreateCourier = sendPostRequestToCreateCourier(courier);
        compareMessageFromResponse(responseFromCreateCourier, 400, INSUFFICIENT_DATA_TO_CREATE);

    }
}
