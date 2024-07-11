package ru.praktikum.services.qa.scooter.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.services.qa.scooter.BaseTest;
import ru.praktikum.services.qa.scooter.models.Order;

import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.compareMessageFromResponse;
import static ru.praktikum.services.qa.scooter.steps.StepsOrder.*;
import static ru.praktikum.services.qa.scooter.utils.Utils.randomString;

public class OrdersGetTest extends BaseTest {

    private static final String RANDOM_FIRSTNAME_OF_CUSTOMER = randomString();
    private static final String RANDOM_LASTNAME_OF_CUSTOMER = randomString();
    private static final String RANDOM_ADDRESS_OF_CUSTOMER = randomString();
    private static final String RANDOM_METRO_STATION_OF_CUSTOMER = randomString();
    private static final String RANDOM_PHONE_OF_CUSTOMER = randomString();
    private static final int RENT_TIME_OF_CUSTOMER = 1;
    private static final String DELIVERY_DATE_OF_CUSTOMER = "2020-06-06";
    private static final String RANDOM_COMMENT_OF_CUSTOMER = randomString();

    Order order;

    @Before
    public void setUp() {
        RestAssured.baseURI= QA_SQOOTER_PRACTICUM_PROD;
        order = new Order(
                RANDOM_FIRSTNAME_OF_CUSTOMER,
                RANDOM_LASTNAME_OF_CUSTOMER,
                RANDOM_ADDRESS_OF_CUSTOMER,
                RANDOM_METRO_STATION_OF_CUSTOMER,
                RANDOM_PHONE_OF_CUSTOMER,
                RENT_TIME_OF_CUSTOMER,
                DELIVERY_DATE_OF_CUSTOMER,
                RANDOM_COMMENT_OF_CUSTOMER,
                null);
        sendPostRequestToCreateOrder(order);
        sendPostRequestToCreateOrder(order);
    }

    @Test
    @DisplayName("Получение доступных заказов /api/v1/orders") // имя теста
    @Description("Получение двух заказов, доступных для взятия") // описание теста
    public void getAvailableOrders() {

        Response responseFromGetAvailableOrders = sendGetAvailableOrdersForCouriers(2, 0);
        compareMessageFromResponse(responseFromGetAvailableOrders, 200, "{orders=");

    }
}
