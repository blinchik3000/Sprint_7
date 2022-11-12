package orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private Order order;
    private OrderClient orderClient;

    @Before
    public void setUp(){
        order = OrderGenerator.generateRandom();
        orderClient = new OrderClient();
    }

    public OrderCreateTest(Order order){this.order=order;}

    @Parameterized.Parameters
    public static Object[][] getOrderWithDifferentColors(){

        String[] colors1=new String[]{"BLACK"};
        String[] colors2=new String[]{"GREY"};
        String[] colors3=new String[]{"BLACK","GREY"};
        String[] colors4=new String[]{"GREY","BLACK"};
        String[] colors5=new String[]{""};

        Order order1 = OrderGenerator.generateRandom();
        order1.setColor(colors1);
        Order order2 = OrderGenerator.generateRandom();
        order2.setColor(colors2);
        Order order3 = OrderGenerator.generateRandom();
        order3.setColor(colors3);
        Order order4 = OrderGenerator.generateRandom();
        order4.setColor(colors4);
        Order order5 = OrderGenerator.generateRandom();
        order5.setColor(colors5);
        Order order6 = OrderGenerator.generateRandom();
        order5.setColor(null);

        return new Object[][]{
                {order1},
                {order2},
                {order3},
                {order4},
                {order5},
                {order6},
        };
    }

    @Test
    @DisplayName("Check Order can be created with different colors")
    @Description("test creating order with possible colors of without them")
    public void checkOrderIsCreated(){
        ValidatableResponse response = orderClient.createOrder(order);
        response.assertThat().statusCode(201).and().body("track", notNullValue());
    }
}
