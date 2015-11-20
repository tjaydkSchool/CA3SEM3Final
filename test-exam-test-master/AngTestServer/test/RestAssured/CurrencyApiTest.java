/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.parsing.Parser;
import static com.jayway.restassured.path.json.JsonPath.from;
import entity.User;
import javax.ws.rs.core.MediaType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import rest.ApplicationConfig;

/**
 *
 * @author Dennis
 */
public class CurrencyApiTest {

    static Server server;

    public CurrencyApiTest() {
        baseURI = "http://localhost:8082";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = new Server(8082);
        ServletHolder servletHolder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(contextHandler);
        server.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        server.stop();
        //waiting for all the server threads to terminate so we can exit gracefully
        server.join();
    }

    @Test
    public void testCurrencyCalculator() {
        //LOGIN AND GET TOKEN
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + from(json).get("token"))
                .when()
                .get("/currency/calculator/100/GBP/DK")
                .then()
                .statusCode(200);
    }

}
