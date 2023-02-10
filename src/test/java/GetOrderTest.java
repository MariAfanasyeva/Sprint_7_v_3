import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrderTest {

    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Проверяем тело в случае валидного запроса")
    @Description("Ожидаемый результат: тело ответа возвращает список заказов")
    public void getOrderReturnAllOrders() {

        orderClient.getOrder()
                .then()
                .body("orders", notNullValue());
    }
}