import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {


    String randomLogin = RandomStringUtils.random(10, true, false);

    String randomPassword = RandomStringUtils.random(10, true, true);

    String randomName = RandomStringUtils.random(10, true, false);
    CourierClient courierClient = new CourierClient();
    Courier courier = new RandomCourier();

    @Test
    @DisplayName("Проверяем код ответа в случае валидного запроса")
    @Description("Ожидаемый результат: код 201, курьер создан")
    public void checkStatusForCreateValidCourier() {
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(201);

    }

    @Test
    @DisplayName("Проверяем тело ответа")
    @Description("Ожидаемый результат: соответсвие тела (ok, true)")
    public void checkBodyForCreateValidCourier() {
        courierClient.createCourier(courier)
                .then().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Проверяем код ответа в случае невалидного запроса без логина")
    @Description("Ожидаемый результат: запрос без логина ведет к коду 400")
    public void checkStatusForCreateInvalidCourierWithoutLogin() {
        courier = new Courier("", randomPassword,randomName);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(400);

    }
    @Test
    @DisplayName("Проверяем message в случае невалидного запроса без логина")
    @Description("Ожидаемый результат: выводится сообщение \"Недостаточно данных для создания учетной записи\"")
    public void checkBodyForCreateInvalidCourierWithoutLogin() {
        courier = new Courier("",randomPassword, randomName);
        courierClient.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("Проверяем код ответа в случае невалидного запроса без пароля")
    @Description("Ожидаемый результат: запрос без пароля ведет к коду 400")
    public void checkStatusForCreateInvalidCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(400);

    }



    @Test
    @DisplayName("Проверяем message в случае невалидного запроса без логина")
    @Description("Ожидаемый результат: выводится сообщение \"Недостаточно данных для создания учетной записи\"")
    public void checkBodyForCreateInvalidCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then().assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));

    }




    @Test
    @DisplayName("Проверяем код ответа в случае запроса с повторяющимся логином")
    @Description("Ожидаемый результат: запрос с дублированием уже существующего логина ведет к коду 409.")
    public void checkStatusForDuplicationCreateCourier(){
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then().assertThat().statusCode(409);
    }


    @Test
    @DisplayName("Проверяем message в случае невалидного запроса с повторяющимся логином")
    @Description("Ожидаемый результат: выводится сообщение \"Этот логин уже используется. Попробуйте другой.\"")
    public void checkBodyForDuplicationCreateCourier(){
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then().assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @After
    public void deleteCourier(){
        courierClient.deleteCourier(courier);
    }
}
