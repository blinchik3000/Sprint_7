package courier;

import common.Credentials;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.generateRandom();
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        courierClient.delete(responseLogin);

    }


    @Test
    @DisplayName("Check Courier can be created")
    @Description("Basic test for Courier creation - 200/ok:true")
    public void checkCourierIsCreated() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        responseCreate.assertThat().statusCode(201).and().body("ok",equalTo(true));
    }


    @Test
    @DisplayName("Check system reaction to double Courier")
    @Description("Negative tests of Courier's double")
    @Issue("баг - сообщение об ошибке не соответствует требованиям")
    public void checkDoubleCourierIsNotCreated() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse responseDouble = courierClient.create(courier);
        response.assertThat().statusCode(201);
        responseDouble.assertThat().statusCode(409).and().body("message",equalTo("Этот логин уже используется"));
    }



    @Test
    @DisplayName("Check Courier Mandatory fields - firstName")
    @Description("Positive tests of Courier's mandatory fields")
    public void checkCourierWithoutFirstNameIsCreated() {
        courier.setFirstName(null);
        ValidatableResponse responseCreate = courierClient.create(courier);
        responseCreate.assertThat().statusCode(201).and().body("ok",equalTo(true));
    }

}
