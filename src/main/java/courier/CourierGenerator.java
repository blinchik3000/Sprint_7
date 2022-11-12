package courier;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class CourierGenerator {
    private final static String RANDOM_LOGIN = "courierNik"+new Random().nextInt(100000);
    private final static String RANDOM_PASSWORD = ""+new Random().nextInt(9999);
    private final static String RANDOM_FIRSTNAME = RandomStringUtils.random(10,true,false);

    @Step("Наполняем объект Courier необходимыми значениями")
    public static Courier generateRandom(){
        return new Courier(RANDOM_LOGIN, RANDOM_PASSWORD, RANDOM_FIRSTNAME);
    }
}
