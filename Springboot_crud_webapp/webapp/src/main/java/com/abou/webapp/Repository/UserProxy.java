package com.abou.webapp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.abou.webapp.CustomProperties;
import com.abou.webapp.Model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all users
     * @return An iterable of all users
     */

    public Iterable<User> getUsers() {
        String baseApiUrl = props.getApiUrl();
        String getUsersUrl = baseApiUrl + "/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<User>> response = restTemplate.exchange(
                getUsersUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        log.debug("Get Users call " + response.getStatusCode());

        return response.getBody();
    }

    public User getUser(int id) {
        String baseApiUrl = props.getApiUrl();
        String getUserUrl = baseApiUrl + "/user/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(
                getUserUrl,
                HttpMethod.GET,
                null,
                User.class
        );

        log.debug("Get User call " + response.getStatusCode());

        return response.getBody();
    }

    /**
     * Add a new user 
     * @param u A new user (without an id)
     * @return The user fulfilled (with an id)
     */
    public User createUser(User u) {
        String baseApiUrl = props.getApiUrl();
        String createUserUrl = baseApiUrl + "/create";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(u);
        ResponseEntity<User> response = restTemplate.exchange(
                createUserUrl,
                HttpMethod.POST,
                request,
                User.class);

        log.debug("Create User call " + response.getStatusCode());

        return response.getBody();
    }

    /**
     * Update a user - using the PUT HTTP Method.
     * @param u Existing user to update
     */
    public User updateUser(User u) {
        String baseApiUrl = props.getApiUrl();
        String updateUserUrl = baseApiUrl + "/update/" + u.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(u);
        ResponseEntity<User> response = restTemplate.exchange(
                updateUserUrl,
                HttpMethod.PUT,
                request,
                User.class);

        log.debug("Update User call " + response.getStatusCode());

        return response.getBody();
    }

    /**
     * Delete an user using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param id The id of the user to delete
     */
    public void deleteUser(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteUserUrl = baseApiUrl + "/delete/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteUserUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete User call " + response.getStatusCode());
    }

}