package edu.kata.ponomarev;

import edu.kata.ponomarev.models.UserKata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private List<String> cookies = new ArrayList<>();
    private String answer = "";

    public List<UserKata> userKataList() {
        ResponseEntity<List<UserKata>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<UserKata>>() {});
        List<UserKata> userKataList = responseEntity.getBody();
        responseEntity.getHeaders().get("Set-Cookie").stream().forEach(cookies::add);
        System.out.println(cookies + "             AAAAAAAAAAAAAAAAAAAAA");
        return userKataList;
    }
    public void saveUser(UserKata userKata){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        System.out.println(cookies);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        System.out.println(headers + "                BBBBBBBBBBBBBB");
        HttpEntity<UserKata> entity = new HttpEntity<UserKata>(userKata, headers);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, entity, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getBody());
        answer += responseEntity.getBody();
//        restTemplate.exchange(URL, HttpMethod.POST, responseEntity, String.class);

//        ResponseEntity<String> responseEntity1 = restTemplate.exchange(URL, HttpMethod.POST, userKata, Class.class);
    }
    public void editUser(UserKata userKata){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        System.out.println(cookies);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        System.out.println(headers + "                BBBBBBBBBBBBBB");
        HttpEntity<UserKata> entity = new HttpEntity<UserKata>(userKata, headers);
        //ResponseEntity<UserKata> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, UserKata.class, 3);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity.getBody());
        answer += responseEntity.getBody();
    }
    public void deleteUser(int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        System.out.println(cookies);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        System.out.println(headers + "                BBBBBBBBBBBBBB");
        Map<String, Integer> uriVariables = new HashMap<String, Integer>() {{
            put("id", id);
        }};
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, entity, String.class, uriVariables);
        System.out.println(responseEntity.getBody());
        answer += responseEntity.getBody();
        System.out.println(answer);
    }
}
