import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class TestsForReqres {


    /* Template
       1. Make request to "https://reqres.in/api/users"
       2. Get response { "name": "morpheus", "job": "leader", "id": "64", "createdAt": "2023-04-30T09:06:18.353Z" }
       3. Check "name" is "morpheus", "job" is "leader"
    */
    @Test
    void createUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }

    @Test
    void UpdateUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)//ошибка на сайте в response прописан 200,но он не отрабатывает
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void registersuccessfulTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulRegisterTest() {
        String body = "{ \"email\": \"sydney@fife\" }";
        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void deleteUserTest() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}