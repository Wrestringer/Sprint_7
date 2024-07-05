package ru.praktikum.services.qa.scooter;

import io.restassured.RestAssured;
import org.junit.Before;

import static ru.praktikum.services.qa.scooter.constants.Uri.QA_SQOOTER_PRACTICUM_PROD;

public class BaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI= QA_SQOOTER_PRACTICUM_PROD;
    }

}
