package orders;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.YearMonth;
import java.util.Random;

public class OrderGenerator {
    private static final String[] colors = new String[] { "BLACK", "GREY" };
    private static final int randomColor = new Random().nextInt(colors.length);
//    private static YearMonth = n



    private static final String FIRSTNAME = RandomStringUtils.random(10,true,false);
    private static final String LASTNAME = RandomStringUtils.random(10,true,false);
    private static final String ADDRESS=RandomStringUtils.random(10,true,false);
    private static final int METROSTATION= new Random().nextInt(99);
    private static final String PHONE="+"+RandomStringUtils.random(10,false,true);
    private static final int RENTTIME= new Random().nextInt(365);
    private static final String DELIVERYDATE= YearMonth.now().toString();// "2022-"+"11-11";//+new Random().nextInt(1)+new Random().nextInt(2)+"-"+new Random().nextInt(2)+new Random().nextInt(8);
    private static final String COMMENT=RandomStringUtils.random(20,true,false);
    private static final String[] COLOR=new String[]{colors[randomColor]};

@Step("Наполняем объект Order необходимыми значениями")
    public static Order generateRandom(){
        return new Order(
                FIRSTNAME,
                LASTNAME,
                ADDRESS,
                METROSTATION,
                PHONE,
                RENTTIME,
                DELIVERYDATE,
                COMMENT,
                COLOR
                );
    }
}
