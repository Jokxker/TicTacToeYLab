import com.aleks.repository.PlayerRepository;
import com.aleks.repository.StorageH2;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class TestRestApiGet {
    @Test
    public void testStartGameApiGet() { // Тестируем ответ, читаем файл рейтинга, который должен быть в ответе
        RestAssured.baseURI = "http://localhost:8080/gameplay";
        StorageH2 h2 = new PlayerRepository();
        h2.show();
        Map<String, Integer> res = h2.getRating();
        when().request("GET").then().assertThat().statusCode(200).and()
                .body("data", is(res));
    }
}
