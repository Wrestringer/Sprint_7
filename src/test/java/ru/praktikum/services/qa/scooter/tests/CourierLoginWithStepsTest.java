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

import static ru.praktikum.services.qa.scooter.constants.Messages.INSUFFICIENT_DATA_TO_LOG_IN;
import static ru.praktikum.services.qa.scooter.constants.Messages.USER_NOT_FOUND_TO_LOG_IN;
import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.*;
import static ru.praktikum.services.qa.scooter.utils.Utils.randomString;

public class CourierLoginWithStepsTest extends BaseTest {

    private static final String RANDOM_LOGIN_OF_COURIER = randomString();
    private static final String RANDOM_PASSWORD_OF_COURIER = randomString();
    Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI= QA_SQOOTER_PRACTICUM_PROD;
        courier = new Courier(RANDOM_LOGIN_OF_COURIER, RANDOM_PASSWORD_OF_COURIER, null);
        sendPostRequestToCreateCourier(courier);

        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        courier.setId(getCourierIdFromResponseLogin(responseFromLoginCourier));
    }

    @After
    public void tearDown() {
        sendDeleteCourierRequest(courier.getId());
    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера по корректным данным") // описание теста
    public void postLoginCourierWithCorrectData() {
        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        //Проверка тела ответа
        compareMessageFromResponse(responseFromLoginCourier, 200, "{id=");

    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера без логина") // описание теста
    public void postLoginCourierWithoutLogin() {

        courier.setLogin(null);
        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        //Проверка тела ответа
        compareMessageFromResponse(responseFromLoginCourier, 400, INSUFFICIENT_DATA_TO_LOG_IN);

    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера без пароля") // описание теста
    public void postLoginCourierWithoutPassword() {

        courier.setPassword(null);

        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        compareMessageFromResponse(responseFromLoginCourier, 400, INSUFFICIENT_DATA_TO_LOG_IN);

    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера без логина и пароля") // описание теста
    public void postLoginCourierWithoutLoginAndPassword() {

        courier.setLogin(null);
        courier.setPassword(null);

        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        compareMessageFromResponse(responseFromLoginCourier, 400, INSUFFICIENT_DATA_TO_LOG_IN);

    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера с несуществующим логином") // описание теста
    public void postLoginCourierWithIncorrectLogin() {

        courier.setLogin(randomString());

        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        compareMessageFromResponse(responseFromLoginCourier, 404, USER_NOT_FOUND_TO_LOG_IN);

    }

    @Test
    @DisplayName("Логин курьера /api/v1/courier/login") // имя теста
    @Description("Логин курьера с несуществующим паролем") // описание теста
    public void postLoginCourierWithIncorrectPassword() {
        courier.setPassword(randomString());

        //Проверка логина
        Response responseFromLoginCourier = sendPostRequestLoginCourier(courier);
        compareMessageFromResponse(responseFromLoginCourier, 404, USER_NOT_FOUND_TO_LOG_IN);


    }
}
