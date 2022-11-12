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
import static org.hamcrest.Matchers.notNullValue;


public class CourierLoginTest {
    private Courier courier;
    private CourierClient courierClient;


    @Before
    public void setUp() {
        courier = CourierGenerator.generateRandom();
        courierClient = new CourierClient();
        courierClient.create(courier);
    }

    @After
    public void cleanUp(){
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        courierClient.delete(responseLogin);
    }

    @Test
    @DisplayName("Check Courier can login")
    @Description("Basic test for Courier logining - return 200/id")
    public void checkCourierIsLogin() {
        ValidatableResponse response = courierClient.login(Credentials.from(courier));
        response.assertThat().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    @DisplayName("Check Courier can't login with wrong login")
    @Description("Negative test for Courier login - wrong login")
    public void checkErrorWhenLogWrong(){
        String tempLogin = courier.getLogin();
        courier.setLogin(courier.getLogin()+"wrongLogin");
        ValidatableResponse response = courierClient.login(Credentials.from(courier));
        response.assertThat().statusCode(404).and().body("message",equalTo("Учетная запись не найдена"));
        courier.setLogin(tempLogin);

    }

    @Test
    @DisplayName("Check Courier can't login with wrong password")
    @Description("Negative test for Courier login - wrong password")
    public void checkErrorWhenPassWrong(){
        String tempPass = courier.getPassword();
        courier.setPassword(courier.getPassword()+"wrongPassword");
        ValidatableResponse response = courierClient.login(Credentials.from(courier));
        response.assertThat().statusCode(404).and().body("message",equalTo("Учетная запись не найдена"));
        courier.setPassword(tempPass);
    }


    @Test
    @DisplayName("Check Courier can't login with null login")
    @Description("Negative test for Courier login - null login")
    public void checkErrorWhenLoginNull(){
        String tempLogin = courier.getLogin();
        courier.setLogin(null);
        ValidatableResponse response = courierClient.login(Credentials.from(courier));
        response.assertThat().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
        courier.setLogin(tempLogin);
    }


    @Test
    @DisplayName("Check Courier can't login with null password")
    @Description("Negative test for Courier login - null password")
    @Issue("баг - таймаут(504) вместо ошибки 400")
    public void checkErrorWhenPassNull(){
        String tempPass = courier.getPassword();
        courier.setPassword(null);
        ValidatableResponse response = courierClient.login(Credentials.from(courier));
        response.assertThat().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
        courier.setPassword(tempPass);

    }



}
