import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;


public class ExampleLessonTest {

    /*
        1. Make request to https://selenoid.autotests.cloud/status
        2. Get response { total: 20, used: 0, queued: 0, pending: 0, browsers: {
                android: { 8.1: { } }, chrome: { 100.0: { }, 99.0: { } },
                chrome-mobile: { 86.0: { } }, firefox: { 97.0: { }, 98.0: { } }, opera: { 84.0: { }, 85.0: { } } } }
        3. Check total is 20
     */

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }
}
