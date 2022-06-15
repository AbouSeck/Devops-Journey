package com.abou.app.rest.Models;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String occupation;
    @Column
    private String password;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPassword() { return password; }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) { this.password = password; }
}
