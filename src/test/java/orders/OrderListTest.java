package orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    private Order order;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        order = OrderGenerator.generateRandom();
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check Orders List is returned")
    @Description("Basic test for getting order list")
    public void checkOrderListIsReturned() {
        ValidatableResponse response = orderClient.getOrderList();
        response.assertThat().statusCode(200).and().body("orders[0].id", notNullValue());

    }
}
