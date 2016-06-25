package co.uk.dragosolutions.resources;

import com.jayway.restassured.http.ContentType;
import co.uk.dragosolutions.MicroserviceBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MicroserviceBootApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class EmployeeResourceIT {


    @Test
    public void findAll_shouldReturnAllExistingEmployees() {

        given()
                .log().ifValidationFails()
                .port(8080)
                .accept(ContentType.JSON)
                .expect()
                .response().statusCode(200)
                .when().get("/employees");
    }
}