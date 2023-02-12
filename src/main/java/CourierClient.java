import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient {

    public CourierClient() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Step("Метод для шага Создать курьера")
    @Description("POST на ручку /api/v1/courier")
    public Response createCourier(Courier courier) {
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");


    }


    @Step("Авторизация курьера в системе")
    @Description("POST на ручку /api/v1/courier/login")
    public Response loginCourier(Courier courier) {

        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");


    }

    @Step("Удалить курьера из системы")
    @Description("DELETE на ручку /api/v1/courier/:id")
    public void deleteCourier(Courier courier) {
        try {
            int id = loginCourier(courier).then().extract().path("id");



            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(courier)
                    .when()
                    .delete("/api/v1/courier/"+id);

        }
        catch (NullPointerException e){
            System.out.println("Nothing to delete after test");
        }

    }
}