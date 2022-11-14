package courier;

import common.Client;
import common.Credentials;
import io.qameta.allure.Step;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import response.LoginResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    private static final String COURIER_CREATE_ENDPOINT = "api/v1/courier";
    private static final String COURIER_LOGIN_ENDPOINT = "api/v1/courier/login";
    private static final String COURIER_DELETE_ENDPOINT = "api/v1/courier/%d";

    private final Filter requestFilter =  new RequestLoggingFilter();
    private final Filter responseFiler =  new ResponseLoggingFilter();

    @Step("Создаем курьера в Яндекс самокате")
    public ValidatableResponse create(Courier courier){
        return given()
                .filters(requestFilter,responseFiler)
                .spec(getSpec())
                .body(courier)
                .when().
                post(COURIER_CREATE_ENDPOINT)
                .then();
    }

@Step("Логинимся курьером в Яндекс самокате")
    public ValidatableResponse login(Credentials credentials){
        return given()
                .filters(requestFilter,responseFiler)
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(COURIER_LOGIN_ENDPOINT)
                .then();
    }

    @Step("Удаляем курьера в Яндекс самокате")
    public ValidatableResponse delete(ValidatableResponse responseLogin){
        int id = responseLogin.extract().path("id");
        String deleteEndPointWithCourierId = String.format(COURIER_DELETE_ENDPOINT,id);
        LoginResponse loginResponse = new LoginResponse(id);
        return given()
                .filters(requestFilter,responseFiler)
                .spec(getSpec())
                .body(loginResponse)
                .when()
                .delete(deleteEndPointWithCourierId)
                .then();
    }
}
