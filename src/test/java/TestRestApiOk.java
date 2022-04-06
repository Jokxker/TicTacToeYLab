import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestRestApiOk {
    @Test
    public void testStartGameApiPostOk() { // Игроки создались
        RestAssured.baseURI = "http://localhost:8080/gameplay";
        given().
                param("nameX", "aleks").
                param("name0", "loki")
                .when().request("POST").then().statusCode(200);
    }
}