package runtheshow.frontend.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;
import runtheshow.frontend.config.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class BootTest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate template = new TestRestTemplate();

    @Test
    public void homePageLoads() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"
                + port + "/", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void userEndpointProtected() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"
                + port + "/user", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void resourceEndpointProtected() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"
                + port + "/resource/user/current", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void loginSucceeds() {
        RestTemplate template = new TestRestTemplate("admin@admin.fr", "admin");
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/user", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void artisteLoginTest() {
        RestTemplate template = new TestRestTemplate("artiste@artiste.fr", "artiste");

        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/resource/artiste/current", String.class);
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void artisteProfileTest() throws IOException {

        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/resource/artiste/4", String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(isValidJSON(response.getBody()), true);
    }
    
    @Test
    public void artisteSearchTest() throws IOException {

        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/resource/artiste/search/1,2/1,2", String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(isValidJSON(response.getBody()), true);
    }
    
    @Test
    public void typesRegionsTest() throws IOException {

        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
                + "/resource/artiste/types", String.class);
        
        ResponseEntity<String> response2 = template.getForEntity("http://localhost:" + port
                + "/resource/artiste/types", String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(isValidJSON(response.getBody()), true);
        
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(isValidJSON(response2.getBody()), true);
    }

    public static boolean isValidJSON(final String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean valid = true;
        try {
            objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            valid = false;
        }
        return valid;
    }

}
