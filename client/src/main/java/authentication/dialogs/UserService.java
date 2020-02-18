package authentication.dialogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class UserService {

    private transient String baseurl = "http://localhost:8080";

    @Autowired
    private transient RestTemplate restTemplate = new RestTemplate();

    private RestTemplate getRestTemplate() {
        return restTemplate;
    }

    /**
     * This method will send a user object to the server.
     * @param user the user to be authenticated or registered.
     * @param uri on which URL to send and what to do with the user..
     * @return true if the user is authenticated or successfully registered - false if not.
     */
    public Boolean sendUserToServer(User user, String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Boolean> response = this.getRestTemplate().exchange(baseurl + uri,
                HttpMethod.POST, entity, Boolean.class);

        return response.getBody();
    }

    /**
     * This method will send a user object to the server.
     * @param user the user to be checked.
     * @return double of user's highscore.
     */
    public Double userHighscore(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Double> response = this.getRestTemplate().exchange(baseurl
                        + "/user/highscore",
                HttpMethod.POST, entity, Double.class);

        return response.getBody();
    }



    /**
     * Retrieve a list of the top 5 users by highscore.
     * @return a list of the 5 user objects with the highest highscores.
     */
    public String highscores() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String uri = "/user/all";
        ResponseEntity<String> response = this.getRestTemplate().exchange(baseurl + uri,
                HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    /**
     * Retrieve a list of the top 5 users by highscore.
     * @return a list of the 5 user objects with the highest highscores.
     */
    public List<User> listHighscores() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String uri = "/user/all/list";
        ResponseEntity<List> response = this.getRestTemplate().exchange(baseurl + uri,
                HttpMethod.GET, entity, List.class);

        //System.out.println(response.getBody());

        return response.getBody();
    }




}

