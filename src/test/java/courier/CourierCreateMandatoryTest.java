package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CourierCreateMandatoryTest {

    private final Courier courier;
    private CourierClient courierClient;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }


    public CourierCreateMandatoryTest(Courier courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierWithoutMandatoryFields() {
        Courier courier1 = CourierGenerator.generateRandom();
        courier1.setLogin(null);
        Courier courier2 = CourierGenerator.generateRandom();
        courier2.setPassword(null);

        return new Object[][]{
                {courier1},
                {courier2},
        };
    }

    @Test
    @DisplayName("Check Courier Mandatory fields - login/password")
    @Description("Negative tests of Courier's mandatory fields")
    public void checkCourierMandatoryFields() {
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
