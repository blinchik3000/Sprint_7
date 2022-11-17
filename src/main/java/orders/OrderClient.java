package orders;

import common.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    private static final String ORDER_GET_AND_POST_ENDPOINT ="api/v1/orders";

    @Step("Создаем заказ в Яндекс Самокате")
    public ValidatableResponse createOrder(Order order){
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_GET_AND_POST_ENDPOINT)
                .then();
    }

    @Step("Получаем список всех заказов в Яндекс самокате")
    public ValidatableResponse getOrderList(){
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_GET_AND_POST_ENDPOINT)
                .then();
    }


}
