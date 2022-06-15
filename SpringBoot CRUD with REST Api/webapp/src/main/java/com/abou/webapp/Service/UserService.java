package com.abou.webapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abou.webapp.Model.User;
import com.abou.webapp.Repository.UserProxy;

import lombok.Data;

@Data
@Service
public class UserService {

    @Autowired
    private UserProxy userProxy;

    public User getUser(final int id) {
        return userProxy.getUser(id);
    }

    public Iterable<User> getUsers() {
        return userProxy.getUsers();
    }

    public void deleteUser(final int id) {
        userProxy.deleteUser(id);
    }

    public User saveUser(User user) {
        User savedUser;

        // Functional rule : Last name must be capitalized.
        user.setLastName(user.getLastName().toUpperCase());

        if(user.getId() == null) {
            // If "id" is null, then it's a new user.
            savedUser = userProxy.createUser(user);
        } else {
            savedUser = userProxy.updateUser(user);
        }

        return savedUser;
    }

}
