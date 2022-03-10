package ir.man.spring.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppInitializerTests {

    TestRestTemplate restTemplate;
    URL baseUrl;
    @LocalServerPort
    int port;

    @Before
    public void setUp() throws MalformedURLException {

    }

    @Test
    void whenLoggedUserRequestsHomePage_ThenSuccess() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user1", "pass1");
        baseUrl = new URL("http://localhost:" + port);
        System.out.println("************************************* " + baseUrl.toString());
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl.toString(), String.class);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
