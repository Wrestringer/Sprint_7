package ru.praktikum.services.qa.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.services.qa.scooter.models.Order;

import java.util.Arrays;
import java.util.List;

import static ru.praktikum.services.qa.scooter.constants.Colors.COLOR_BLACK;
import static ru.praktikum.services.qa.scooter.constants.Colors.COLOR_GREY;
import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;
import static ru.praktikum.services.qa.scooter.steps.StepsCourier.*;
import static ru.praktikum.services.qa.scooter.steps.StepsOrder.*;
import static ru.praktikum.services.qa.scooter.utils.Utils.randomString;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private static final String RANDOM_FIRSTNAME_OF_CUSTOMER = randomString();
    private static final String RANDOM_LASTNAME_OF_CUSTOMER = randomString();
    private static final String RANDOM_ADDRESS_OF_CUSTOMER = randomString();
    private static final String RANDOM_METRO_STATION_OF_CUSTOMER = randomString();
    private static final String RANDOM_PHONE_OF_CUSTOMER = randomString();
    private static final int RENT_TIME_OF_CUSTOMER = 1;
    private static final String DELIVERY_DATE_OF_CUSTOMER = "2020-06-06";
    private static final String RANDOM_COMMENT_OF_CUSTOMER = randomString();

    Order order;


    @Parameterized.Parameter
    public List <String> color;

    @Parameterized.Parameters(name = "{index} Сценарий заказа самоката с параметрами с цветом: {0}")
    public static Object[][] data() {
        return new Object[][] {
                { Arrays.asList(COLOR_BLACK) },
                { Arrays.asList(COLOR_GREY) },
                { null },
                { Arrays.asList(COLOR_GREY, COLOR_BLACK) },
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI= QA_SQOOTER_PRACTICUM_PROD;
    }


    @Test
    @DisplayName("Создание заказа /api/v1/orders") // имя теста
    @Description("Создаение заказа на основе данных") // описание теста
    public void postCreateOrder() {
        order = new Order(
                RANDOM_FIRSTNAME_OF_CUSTOMER,
                RANDOM_LASTNAME_OF_CUSTOMER,
                RANDOM_ADDRESS_OF_CUSTOMER,
                RANDOM_METRO_STATION_OF_CUSTOMER,
                RANDOM_PHONE_OF_CUSTOMER,
                RENT_TIME_OF_CUSTOMER,
                DELIVERY_DATE_OF_CUSTOMER,
                RANDOM_COMMENT_OF_CUSTOMER,
                color);

        Response responseFromCreateOrder = sendPostRequestToCreateOrder(order);
        compareMessageFromResponse(responseFromCreateOrder, 201, "{track=");

        order.setTrack(getTrackOrderFromResponseCreate(responseFromCreateOrder));

    }

}
