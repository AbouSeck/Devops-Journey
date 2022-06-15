package com.abou.app.rest.Controller;

import com.abou.app.rest.Models.User;
import com.abou.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPages(){
       return "Welcome" ;
    }

    /**
     * Create - Add a new user
     * @param user An object user
     * @return The user object saved
     */
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }


    /**
     * Read - Get one user 
     * @param id The id of the user
     * @return An User object full filled
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") final Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    /**
     * Read - Get all users
     * @return - An Iterable object of User full filled
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    /**
     * Update - Update an existing user
     * @param id - The id of the user to update
     * @param user - The user object updated
     * @return the updated user
     */
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> u = userRepo.findById(id);
        if(u.isPresent()) {
            User currentUser = u.get();

            String firstName = user.getFirstName();
            if(firstName != null) {
                currentUser.setFirstName(firstName);
            }
            String lastName = user.getLastName();
            if(lastName != null) {
                currentUser.setLastName(lastName);
            }
            String occupation = user.getOccupation();
            if(occupation != null) {
                currentUser.setOccupation(occupation);
            }
            String password = user.getPassword();
            if(password != null) {
                currentUser.setPassword(password);
            }
            userRepo.save(currentUser);
            System.out.println("User with id "+id+" has been updated successfully...") ;
            return currentUser;
        } else {
            System.out.println ("User with id "+id+" does not exist !");
            return null;
        }
    }


    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") final Long id) {
        Optional<User> u = userRepo.findById(id);
        if(u.isPresent()) {
            userRepo.deleteById(id);
            return ("User with id "+id+" has been deleted successfully...");
        } else {
            return ("User with id "+id+" does not exist !");
        }


    }

}
