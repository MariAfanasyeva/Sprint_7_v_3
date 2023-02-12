import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/*генерируем рандомные значения для:
 - Имени, Фамилии, Адреса, Телефона заказчика
 - Ближайшей к заказчику станции метро
 - Количеству дней аренды
 - Даты доставки
 - Комментария от заказчика
 - Предпочитаемого цвета самоката
 */
public class RandomOrders extends Orders  {

    private static final String RANDOM_FIRSTNAME = RandomStringUtils.random(10, true, false);
    private static  final String RANDOM_LASTNAME = RandomStringUtils.random(10, true, false);
    private static final String RANDOM_ADDRESS = RandomStringUtils.random(10, true, false);
    private static final int RANDOM_METRO_STATION =  ThreadLocalRandom.current().nextInt(1, 11);
    private static final String RANDOM_PHONE = RandomStringUtils.random(10, false, true);
    private static final int RANDOM_RENT_TIME= ThreadLocalRandom.current().nextInt(1, 11);
    private static final String RANDOM_DELIVERY_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private static final String RANDOM_COMMENT = RandomStringUtils.random(10, true, false);
    private  String[] newColor;


    public RandomOrders(String[] newColour) {
        super(
                RANDOM_FIRSTNAME,
                RANDOM_LASTNAME,
                RANDOM_ADDRESS,
                RANDOM_METRO_STATION,
                RANDOM_PHONE,
                RANDOM_RENT_TIME,
                RANDOM_DELIVERY_DATE,
                RANDOM_COMMENT,
                newColour);
    }
}
